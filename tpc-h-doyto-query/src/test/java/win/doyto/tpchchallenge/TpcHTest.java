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

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;

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
    void queryForPricingSummaryReport() {
        PricingSummaryQuery query = PricingSummaryQuery
                .builder()
                .shipdateDelta(90)
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
    void queryForMinimumCostSupplier() {
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
}
