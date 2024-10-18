package win.doyto.tpchchallenge.benchmark;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
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

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TpcHBenchmark
 *
 * @author f0rb on 2024/8/6
 */
@SpringBootTest(classes = TpcHBenchmarkApp.class)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms256M", "-Xmx2G"})
@Threads(4)
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 3, time = 10) // 正式测试3轮，每轮100秒
public class TpcHBenchmark {

    static final RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration
            .builder().withComparatorForType(BigDecimal::compareTo, BigDecimal.class).build();

    @Resource
    private DataQueryClient dataQueryClient;
    @Resource
    private JdbcTpcHService jdbcTpcHService;
    private boolean benchMark;

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder().include(Benchmark.class.getSimpleName()).build();
        new Runner(opt).run();
    }

    private ConfigurableApplicationContext context;

    @Setup(Level.Iteration)
    public void init() {
        benchMark = true;
        // 这里的WebApplication.class是项目里的spring boot启动类
        context = SpringApplication.run(TpcHBenchmarkApp.class, "--spring.profiles.active=mysql8");
        dataQueryClient = context.getBean(DataQueryClient.class);
        jdbcTpcHService = context.getBean(JdbcTpcHService.class);
    }

    @TearDown
    public void down() {
        context.close();
        context = null;
    }

    @Benchmark
    @Test
    public void q1PricingSummaryReportQuery() {
        Date date = Date.valueOf(LocalDate.of(1998, 9, 1));
        PricingSummaryQuery query = PricingSummaryQuery
                .builder()
                .lShipdateLe(date)
                .sort("l_returnflag;l_linestatus")
                .build();

        List<PricingSummaryView> list = dataQueryClient.aggregate(query, PricingSummaryView.class);

        if (benchMark) return;
        assertThat(list).hasSize(3)
                        .extracting("l_returnflag", "l_linestatus", "avg_disc")
                        .usingRecursiveFieldByFieldElementComparator(configuration)
                        .containsExactly(
                                Tuple.tuple("A", "F", BigDecimal.valueOf(0.048)),
                                Tuple.tuple("N", "O", BigDecimal.valueOf(0.051)),
                                Tuple.tuple("R", "F", BigDecimal.valueOf(0.046667))
                        );
    }

    @Benchmark
    @Test
    public void q1PricingSummaryReportQueryJdbc() {
        Date date = Date.valueOf(LocalDate.of(1998, 9, 1));
        PricingSummaryQuery query = PricingSummaryQuery
                .builder()
                .lShipdateLe(date)
                .sort("l_returnflag;l_linestatus")
                .build();

        List<PricingSummaryView> list = jdbcTpcHService.aggregate(query, PricingSummaryView.class);

        if (benchMark) return;
        assertThat(list).hasSize(3)
                        .extracting("l_returnflag", "l_linestatus", "avg_disc")
                        .usingRecursiveFieldByFieldElementComparator(configuration)
                        .containsExactly(
                                Tuple.tuple("A", "F", BigDecimal.valueOf(0.048)),
                                Tuple.tuple("N", "O", BigDecimal.valueOf(0.051)),
                                Tuple.tuple("R", "F", BigDecimal.valueOf(0.046667))
                        );
    }

    @Benchmark
    public void q2MinimumCostSupplierQuery() {
        MinimumCostSupplierQuery query = MinimumCostSupplierQuery
                .builder()
                .p_size(45)
                .p_typeEnd("BRASS")
                .r_name("EUROPE")
                .ps_supplycost(SupplyCostQuery.builder().r_name("EUROPE").build())
                .sort("s_acctbal,DESC;n_name;s_name;p_partkey")
                .build();

        List<MinimumCostSupplierView> list = dataQueryClient.aggregate(query, MinimumCostSupplierView.class);

        if (benchMark) return;
        assertThat(list).hasSize(2)
                        .extracting("s_acctbal", "s_name", "n_name")
                        .containsExactly(
                                Tuple.tuple(BigDecimal.valueOf(2972.26), "Supplier#000000016", "RUSSIA"),
                                Tuple.tuple(BigDecimal.valueOf(1381.97), "Supplier#000000104", "FRANCE")
                        );
    }

    @Test
    @Benchmark
    public void q2MinimumCostSupplierQueryJdbc() {
        MinimumCostSupplierQuery query = MinimumCostSupplierQuery
                .builder()
                .p_size(45)
                .p_typeEnd("BRASS")
                .r_name("EUROPE")
                .ps_supplycost(SupplyCostQuery.builder().r_name("EUROPE").build())
                .sort("s_acctbal,DESC;n_name;s_name;p_partkey")
                .build();

        List<MinimumCostSupplierView> list = jdbcTpcHService.aggregate(query, MinimumCostSupplierView.class);

        if (benchMark) return;
        assertThat(list).hasSize(2)
                        .extracting("s_acctbal", "s_name", "n_name")
                        .containsExactly(
                                Tuple.tuple(BigDecimal.valueOf(2972.26), "Supplier#000000016", "RUSSIA"),
                                Tuple.tuple(BigDecimal.valueOf(1381.97), "Supplier#000000104", "FRANCE")
                        );
    }

    @Benchmark
    public void q3ShippingPriorityQuery() {
        Date date = Date.valueOf(LocalDate.of(1995, 3, 15));
        ShippingPriorityQuery query = ShippingPriorityQuery
                .builder()
                .c_mktsegment("BUILDING")
                .o_orderdateLt(date)
                .l_shipdateGt(date)
                .sort("revenue,DESC;o_orderdate")
                .build();

        List<ShippingPriorityView> list = dataQueryClient.aggregate(query, ShippingPriorityView.class);

        if (benchMark) return;
        assertThat(list).extracting("l_orderkey", "revenue", "o_shippriority")
                        .containsExactly(
                                Tuple.tuple("3934949", 16103.5, "0")
                        );
    }

    @Test
    @Benchmark
    public void q3ShippingPriorityQueryJdbc() {
        Date date = Date.valueOf(LocalDate.of(1995, 3, 15));
        ShippingPriorityQuery query = ShippingPriorityQuery
                .builder()
                .c_mktsegment("BUILDING")
                .o_orderdateLt(date)
                .l_shipdateGt(date)
                .sort("revenue,DESC;o_orderdate")
                .build();

        List<ShippingPriorityView> list = jdbcTpcHService.aggregate(query, ShippingPriorityView.class);

        if (benchMark) return;
        assertThat(list).extracting("l_orderkey", "revenue", "o_shippriority")
                        .containsExactly(
                                Tuple.tuple("3934949", 16103.5, "0")
                        );
    }

    @Test
    @Benchmark
    public void q4OrderPriorityCheckingQuery() {
        LocalDate date = LocalDate.of(1993, 7, 1);
        Date orderDateGe = Date.valueOf(date);
        Date orderDateLt = Date.valueOf(date.plusMonths(3));
        OrderPriorityCheckingQuery query = OrderPriorityCheckingQuery
                .builder()
                .o_orderdateGe(orderDateGe)
                .o_orderdateLt(orderDateLt)
                .orderExists(new LineItemReceiptQuery())
                .sort("o_orderpriority")
                .build();

        List<OrderPriorityCheckingView> list = dataQueryClient.aggregate(query, OrderPriorityCheckingView.class);

        if (benchMark) return;
        assertThat(list).extracting("o_orderpriority", "order_count")
                        .containsExactly(
                                Tuple.tuple("1-URGENT", 1),
                                Tuple.tuple("3-MEDIUM", 1)
                        );
    }

    @Test
    @Benchmark
    public void q4OrderPriorityCheckingQueryJdbc() {
        LocalDate date = LocalDate.of(1993, 7, 1);
        Date orderDateGe = Date.valueOf(date);
        Date orderDateLt = Date.valueOf(date.plusMonths(3));
        OrderPriorityCheckingQuery query = OrderPriorityCheckingQuery
                .builder()
                .o_orderdateGe(orderDateGe)
                .o_orderdateLt(orderDateLt)
                .orderExists(new LineItemReceiptQuery())
                .sort("o_orderpriority")
                .build();

        List<OrderPriorityCheckingView> list = jdbcTpcHService.aggregate(query, OrderPriorityCheckingView.class);

        if (benchMark) return;
        assertThat(list).extracting("o_orderpriority", "order_count")
                        .containsExactly(
                                Tuple.tuple("1-URGENT", 1),
                                Tuple.tuple("3-MEDIUM", 1)
                        );
    }

    @Test
    @Benchmark
    public void q5LocalSupplierVolumeQuery() {
        LocalDate date = LocalDate.of(1994, 1, 1);
        Date orderDateGe = Date.valueOf(date);
        Date orderDateLt = Date.valueOf(date.plusYears(1));
        LocalSupplierVolumeQuery query = LocalSupplierVolumeQuery
                .builder()
                .r_name("ASIA")
                .o_orderdateGe(orderDateGe)
                .o_orderdateLt(orderDateLt)
                .sort("revenue,DESC")
                .build();

        List<LocalSupplierVolumeView> list = dataQueryClient.aggregate(query, LocalSupplierVolumeView.class);

        if (benchMark) return;
        assertThat(list).isEmpty();
    }

    @Test
    @Benchmark
    public void q5LocalSupplierVolumeQueryJdbc() {
        LocalDate date = LocalDate.of(1994, 1, 1);
        Date orderDateGe = Date.valueOf(date);
        Date orderDateLt = Date.valueOf(date.plusYears(1));
        LocalSupplierVolumeQuery query = LocalSupplierVolumeQuery
                .builder()
                .r_name("ASIA")
                .o_orderdateGe(orderDateGe)
                .o_orderdateLt(orderDateLt)
                .sort("revenue,DESC")
                .build();

        List<ShippingPriorityView> list = jdbcTpcHService.aggregate(query, ShippingPriorityView.class);

        if (benchMark) return;
        assertThat(list).isEmpty();
    }
}