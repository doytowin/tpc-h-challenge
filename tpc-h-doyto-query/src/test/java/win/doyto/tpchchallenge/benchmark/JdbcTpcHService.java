package win.doyto.tpchchallenge.benchmark;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import win.doyto.tpchchallenge.q1.PricingSummaryQuery;
import win.doyto.tpchchallenge.q1.PricingSummaryView;
import win.doyto.tpchchallenge.q2.MinimumCostSupplierQuery;
import win.doyto.tpchchallenge.q2.MinimumCostSupplierView;
import win.doyto.tpchchallenge.q3.ShippingPriorityQuery;
import win.doyto.tpchchallenge.q3.ShippingPriorityView;
import win.doyto.tpchchallenge.q4.OrderPriorityCheckingQuery;
import win.doyto.tpchchallenge.q4.OrderPriorityCheckingView;

import javax.annotation.Resource;
import java.util.*;

/**
 * JdbcTpcHService
 *
 * @author f0rb on 2024/8/6
 */
@SuppressWarnings("unchecked")
@Service
public class JdbcTpcHService {
    private static final Map<Class<?>, RowMapper<?>> holder = new HashMap<>();
    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<PricingSummaryView> aggregate(PricingSummaryQuery query, Class<PricingSummaryView> viewClass) {
        // StringJoiner where = new StringJoiner(" AND ", " WHERE ", "");
        // List<Object> args = new ArrayList<>();
        // if (query.getLShipdateLe() != null) {
        //     where.add("l_shipdate <= ?");
        //     args.add(query.getLShipdateLe());
        // }
        String sql = "SELECT l_returnflag, l_linestatus, sum(l_quantity) AS sum_qty, " +
                "sum(l_extendedprice) AS sum_base_price, sum(l_extendedprice*(1-l_discount)) AS sum_disc_price, " +
                "sum(l_extendedprice*(1-l_discount)*(1+l_tax)) AS sum_charge, avg(l_quantity) AS avg_qty, " +
                "avg(l_extendedprice) AS avg_price, avg(l_discount) AS avg_disc, count(*) AS count_order " +
                "FROM lineitem WHERE l_shipdate <= ? " +
                "GROUP BY l_returnflag, l_linestatus " +
                "ORDER BY l_returnflag, l_linestatus";
        RowMapper<PricingSummaryView> rowMapper = (RowMapper<PricingSummaryView>)
                holder.computeIfAbsent(viewClass, BeanPropertyRowMapper::new);
        return jdbcTemplate.query(sql, rowMapper, query.getLShipdateLe());
    }

    public List<MinimumCostSupplierView> aggregate(MinimumCostSupplierQuery query, Class<MinimumCostSupplierView> viewClass) {
        StringJoiner where = new StringJoiner(" AND ", " AND ", "");
        List<Object> args = new ArrayList<>();
        if (query.getP_size() != null) {
            where.add("p_size = ?");
            args.add(query.getP_size());
        }
        if (query.getP_typeEnd() != null) {
            where.add("p_type LIKE ?");
            args.add("%" + query.getP_typeEnd());
        }
        if (query.getR_name() != null) {
            where.add("r_name = ?");
            args.add(query.getR_name());
        }
        if (query.getPs_supplycost() != null) {
            where.add("ps_supplycost = (" +
                    "SELECT min(ps_supplycost)" +
                    " FROM partsupp, supplier, nation, region" +
                    " WHERE ps_partkey = p_partkey" +
                    " AND ps_suppkey = s_suppkey" +
                    " AND s_nationkey = n_nationkey" +
                    " AND n_regionkey = r_regionkey");
            if (query.getPs_supplycost().getR_name() != null) {
                where.add("r_name = ?)");
                args.add(query.getPs_supplycost().getR_name());
            }
        }
        String sql = "SELECT s_acctbal, s_name, n_name, p_partkey, p_mfgr, s_address, s_phone, s_comment" +
                " FROM part, supplier, partsupp, nation, region" +
                " WHERE s_nationkey = n_nationkey" +
                " AND ps_partkey = p_partkey" +
                " AND ps_suppkey = s_suppkey" +
                " AND n_regionkey = r_regionkey" +
                where +
                " ORDER BY s_acctbal DESC, n_name, s_name, p_partkey";

        RowMapper<MinimumCostSupplierView> rowMapper = (RowMapper<MinimumCostSupplierView>)
                holder.computeIfAbsent(viewClass, BeanPropertyRowMapper::new);
        return jdbcTemplate.query(sql, rowMapper, args.toArray());
    }

    public List<ShippingPriorityView> aggregate(ShippingPriorityQuery query, Class<ShippingPriorityView> viewClass) {
        String sql = "SELECT l_orderkey, " +
                "SUM(l_extendedprice * (1 - l_discount)) AS revenue, " +
                "o_orderdate, " +
                "o_shippriority" +
                " FROM customer, orders, lineitem" +
                " WHERE o_custkey = c_custkey" +
                " AND l_orderkey = o_orderkey" +
                " AND c_mktsegment = ?" +
                " AND o_orderdate < ?" +
                " AND l_shipdate > ?" +
                " GROUP BY l_orderkey, o_orderdate, o_shippriority" +
                " ORDER BY revenue DESC, o_orderdate";

        RowMapper<ShippingPriorityView> rowMapper = (RowMapper<ShippingPriorityView>)
                holder.computeIfAbsent(viewClass, BeanPropertyRowMapper::new);
        return jdbcTemplate.query(sql, rowMapper, query.getC_mktsegment(), query.getL_shipdateGt(), query.getO_orderdateLt());
    }

    public List<OrderPriorityCheckingView> aggregate(OrderPriorityCheckingQuery query, Class<OrderPriorityCheckingView> viewClass) {
        RowMapper<OrderPriorityCheckingView> rowMapper = (RowMapper<OrderPriorityCheckingView>)
                holder.computeIfAbsent(viewClass, BeanPropertyRowMapper::new);
        String sql = "SELECT o_orderpriority, count(*) AS order_count" +
                " FROM orders t" +
                " WHERE o_orderdate >= ?" +
                " AND o_orderdate < ?" +
                " AND EXISTS(SELECT * FROM lineitem t1" +
                " WHERE t.o_orderkey = t1.l_orderkey" +
                " AND t1.l_commitdate < t1.l_receiptdate" +
                ")" +
                " GROUP BY o_orderpriority" +
                " ORDER BY o_orderpriority";
        return jdbcTemplate.query(sql, rowMapper, query.getO_orderdateGe(), query.getO_orderdateLt());
    }
}
