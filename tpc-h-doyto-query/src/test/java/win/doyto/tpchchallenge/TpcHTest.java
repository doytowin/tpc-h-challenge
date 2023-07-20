package win.doyto.tpchchallenge;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import win.doyto.query.core.DataQueryClient;
import win.doyto.tpchchallenge.q1.PricingSummaryQuery;
import win.doyto.tpchchallenge.q1.PricingSummaryView;
import win.doyto.tpchchallenge.q2.MinimumCostSupplierQuery;
import win.doyto.tpchchallenge.q2.MinimumCostSupplierView;
import win.doyto.tpchchallenge.q2.SupplyCostQuery;
import win.doyto.tpchchallenge.q3.ShippingPriorityQuery;
import win.doyto.tpchchallenge.q3.ShippingPriorityView;
import win.doyto.tpchchallenge.q4.LineItemReceiptQuery;
import win.doyto.tpchchallenge.q4.OrderPriorityCheckingQuery;
import win.doyto.tpchchallenge.q4.OrderPriorityCheckingView;
import win.doyto.tpchchallenge.q5.LocalSupplierVolumeQuery;
import win.doyto.tpchchallenge.q5.LocalSupplierVolumeView;
import win.doyto.tpchchallenge.q6.ForecastingRevenueChangeQuery;
import win.doyto.tpchchallenge.q6.ForecastingRevenueChangeView;
import win.doyto.tpchchallenge.q7.NameComparison;
import win.doyto.tpchchallenge.q7.ShippingQuery;
import win.doyto.tpchchallenge.q7.VolumeShippingQuery;
import win.doyto.tpchchallenge.q7.VolumeShippingView;
import win.doyto.tpchchallenge.q8.AllNationsQuery;
import win.doyto.tpchchallenge.q8.NationalMarketShareQuery;
import win.doyto.tpchchallenge.q8.NationalMarketShareView;
import win.doyto.tpchchallenge.q9.ProductTypeProfitMeasureQuery;
import win.doyto.tpchchallenge.q9.ProductTypeProfitMeasureView;
import win.doyto.tpchchallenge.q9.ProfitQuery;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * TpcHTest
 *
 * @author f0rb on 2023/2/23
 */
@SpringBootTest(classes = TpcHApplication.class)
class TpcHTest {

    RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration
            .builder().withComparatorForType(BigDecimal::compareTo, BigDecimal.class).build();

    @Resource
    private DataQueryClient dataQueryClient;

    @Test
    void q1PricingSummaryReportQuery() {
        Date date = Date.valueOf(LocalDate.of(1998, 9, 1));
        PricingSummaryQuery query = PricingSummaryQuery
                .builder()
                .lShipdateLe(date)
                .sort("l_returnflag;l_linestatus")
                .build();

        List<PricingSummaryView> list = dataQueryClient.aggregate(query, PricingSummaryView.class);

        assertThat(list).hasSize(3);
        assertThat(list).extracting("l_returnflag", "l_linestatus", "avg_disc")
                        .usingRecursiveFieldByFieldElementComparator(configuration)
                        .containsExactly(
                                Tuple.tuple("A", "F", BigDecimal.valueOf(0.048)),
                                Tuple.tuple("N", "O", BigDecimal.valueOf(0.051)),
                                Tuple.tuple("R", "F", BigDecimal.valueOf(0.046666666667))
                        );
    }

    @Test
    void q2MinimumCostSupplierQuery() {
        MinimumCostSupplierQuery query = MinimumCostSupplierQuery
                .builder()
                .p_size(45)
                .p_typeEnd("BRASS")
                .r_name("EUROPE")
                .ps_supplycost(SupplyCostQuery.builder().r_name("EUROPE").build())
                .sort("s_acctbal,DESC;n_name;s_name;p_partkey")
                .build();

        List<MinimumCostSupplierView> list = dataQueryClient.aggregate(query, MinimumCostSupplierView.class);

        assertThat(list).hasSize(2);
        assertThat(list).extracting("s_acctbal", "s_name", "n_name")
                        .containsExactly(
                                Tuple.tuple(BigDecimal.valueOf(2972.26), "Supplier#000000016", "RUSSIA"),
                                Tuple.tuple(BigDecimal.valueOf(1381.97), "Supplier#000000104", "FRANCE")
                        );
    }

    @Test
    void q3ShippingPriorityQuery() {
        Date date = Date.valueOf(LocalDate.of(1995, 3, 15));
        ShippingPriorityQuery query = ShippingPriorityQuery
                .builder()
                .c_mktsegment("BUILDING")
                .o_orderdateLt(date)
                .l_shipdateGt(date)
                .sort("revenue,DESC;o_orderdate")
                .build();

        List<ShippingPriorityView> list = dataQueryClient.aggregate(query, ShippingPriorityView.class);

        assertThat(list).extracting("l_orderkey", "revenue", "o_shippriority")
                        .containsExactly(
                                Tuple.tuple("3934949", 16103.5, "0")
                        );
    }

    @Test
    void q4OrderPriorityCheckingQuery() {
        LocalDate date = LocalDate.of(1993, 7, 1);
        Date orderDateGe = Date.valueOf(date);
        Date orderDateLt = Date.valueOf(date.plus(3, MONTHS));
        OrderPriorityCheckingQuery query = OrderPriorityCheckingQuery
                .builder()
                .o_orderdateGe(orderDateGe)
                .o_orderdateLt(orderDateLt)
                .orderExists(new LineItemReceiptQuery())
                .sort("o_orderpriority")
                .build();

        List<OrderPriorityCheckingView> list = dataQueryClient.aggregate(query, OrderPriorityCheckingView.class);

        assertThat(list).extracting("o_orderpriority", "order_count")
                        .containsExactly(
                                Tuple.tuple("1-URGENT", 1),
                                Tuple.tuple("3-MEDIUM", 1)
                        );
    }

    @Test
    void q5LocalSupplierVolumeQuery() {
        LocalDate date = LocalDate.of(1994, 1, 1);
        Date orderDateGe = Date.valueOf(date);
        Date orderDateLt = Date.valueOf(date.plus(1, YEARS));
        LocalSupplierVolumeQuery query = LocalSupplierVolumeQuery
                .builder()
                .r_name("ASIA")
                .o_orderdateGe(orderDateGe)
                .o_orderdateLt(orderDateLt)
                .sort("revenue,DESC")
                .build();

        List<LocalSupplierVolumeView> list = dataQueryClient.aggregate(query, LocalSupplierVolumeView.class);

        assertThat(list).isEmpty();
    }

    @Test
    void q6ForecastingRevenueChangeQuery() {
        ForecastingRevenueChangeQuery query = new ForecastingRevenueChangeQuery();
        LocalDate date = LocalDate.of(1994, 1, 1);
        query.setBaseShipdate(date);
        query.setBaseDiscount(BigDecimal.valueOf(0.03));
        query.setL_quantityLt(31);

        List<ForecastingRevenueChangeView> list = dataQueryClient.aggregate(query, ForecastingRevenueChangeView.class);

        assertThat(list).extracting("revenue")
                        .containsExactly(BigDecimal.valueOf(745.6876));
    }

    @Test
    void q7VolumeShippingQuery() {
        Date startShipdate = Date.valueOf(LocalDate.of(1995, 1, 1));
        Date endShipdate = Date.valueOf(LocalDate.of(1996, 12, 31));

        ShippingQuery shippingQuery = ShippingQuery
                .builder()
                .nameOr(Arrays.asList(
                        new NameComparison("IRAN", "RUSSIA"),
                        new NameComparison("RUSSIA", "IRAN")
                ))
                .l_shipdateGe(startShipdate)
                .l_shipdateLe(endShipdate)
                .build();
        VolumeShippingQuery query = VolumeShippingQuery
                .builder()
                .shippingQuery(shippingQuery)
                .sort("supp_nation;cust_nation;l_year")
                .build();

        List<VolumeShippingView> list = dataQueryClient.aggregate(query, VolumeShippingView.class);

        assertThat(list).usingRecursiveFieldByFieldElementComparator(configuration)
                        .extracting("supp_nation", "cust_nation", "revenue")
                        .containsExactly(
                                Tuple.tuple("IRAN", "RUSSIA", BigDecimal.valueOf(8142.564))
                        );
    }

    @Test
    void q8NationalMarketShareQuery() {
        Date startShipdate = Date.valueOf(LocalDate.of(1995, 1, 1));
        Date endShipdate = Date.valueOf(LocalDate.of(1996, 12, 31));

        AllNationsQuery allNationsQuery = AllNationsQuery
                .builder()
                .r_name("AMERICA")
                .o_orderdateGe(startShipdate)
                .o_orderdateLe(endShipdate)
                .p_type("LARGE ANODIZED COPPER")
                .build();

        NationalMarketShareQuery query = NationalMarketShareQuery
                .builder()
                .nationEq("CANADA")
                .allNationsQuery(allNationsQuery).sort("o_year")
                .build();

        List<NationalMarketShareView> list = dataQueryClient.aggregate(query, NationalMarketShareView.class);

        assertThat(list).usingRecursiveFieldByFieldElementComparator(configuration)
                .extracting("o_year", "mkt_share")
                .containsExactly(Tuple.tuple("1995", BigDecimal.valueOf(1)));
    }

    @Test
    void q9ProductTypeProfitMeasureQuery() {
        ProfitQuery profitQuery = ProfitQuery.builder().p_nameLike("green").build();
        ProductTypeProfitMeasureQuery query = ProductTypeProfitMeasureQuery
                .builder()
                .profitQuery(profitQuery)
                .sort("nation;o_year,DESC")
                .build();

        List<ProductTypeProfitMeasureView> list = dataQueryClient.aggregate(query, ProductTypeProfitMeasureView.class);

        assertThat(list).usingRecursiveFieldByFieldElementComparator(configuration)
                        .extracting("nation", "o_year", "sum_profit")
                        .containsExactly(Tuple.tuple("GERMANY", "1994", BigDecimal.valueOf(1329820.6856)));
    }
}
