package win.doyto.tpchchallenge;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import win.doyto.query.core.DataQueryClient;
import win.doyto.query.core.PageQuery;
import win.doyto.tpchchallenge.domain.lineitem.LineItemQuery;
import win.doyto.tpchchallenge.domain.part.PartQuery;
import win.doyto.tpchchallenge.domain.supplier.SupplierQuery;
import win.doyto.tpchchallenge.q1.PricingSummaryQuery;
import win.doyto.tpchchallenge.q1.PricingSummaryView;
import win.doyto.tpchchallenge.q10.ReturnedItemReportingQuery;
import win.doyto.tpchchallenge.q10.ReturnedItemReportingView;
import win.doyto.tpchchallenge.q11.ImportantStockIdentificationQuery;
import win.doyto.tpchchallenge.q11.ImportantStockIdentificationView;
import win.doyto.tpchchallenge.q11.ValueHaving;
import win.doyto.tpchchallenge.q11.ValueQuery;
import win.doyto.tpchchallenge.q12.ShippingModesAndOrderPriorityQuery;
import win.doyto.tpchchallenge.q12.ShippingModesAndOrderPriorityView;
import win.doyto.tpchchallenge.q13.CustomerDistributionQuery;
import win.doyto.tpchchallenge.q13.CustomerDistributionView;
import win.doyto.tpchchallenge.q13.CustomerOrdersQuery;
import win.doyto.tpchchallenge.q13.JoinOrders;
import win.doyto.tpchchallenge.q14.PromotionEffectQuery;
import win.doyto.tpchchallenge.q14.PromotionEffectView;
import win.doyto.tpchchallenge.q15.TopSupplierQuery;
import win.doyto.tpchchallenge.q15.TopSupplierView;
import win.doyto.tpchchallenge.q16.PartsSupplierRelationshipQuery;
import win.doyto.tpchchallenge.q16.PartsSupplierRelationshipView;
import win.doyto.tpchchallenge.q17.SmallQuantityOrderRevenueQuery;
import win.doyto.tpchchallenge.q17.SmallQuantityOrderRevenueView;
import win.doyto.tpchchallenge.q18.LargeVolumeCustomerQuery;
import win.doyto.tpchchallenge.q18.LargeVolumeCustomerView;
import win.doyto.tpchchallenge.q18.LineItemQuantityHaving;
import win.doyto.tpchchallenge.q18.LineItemQuantityQuery;
import win.doyto.tpchchallenge.q19.DiscountedRevenueQuery;
import win.doyto.tpchchallenge.q19.DiscountedRevenueView;
import win.doyto.tpchchallenge.q19.LineItemFilter;
import win.doyto.tpchchallenge.q2.MinimumCostSupplierQuery;
import win.doyto.tpchchallenge.q2.MinimumCostSupplierView;
import win.doyto.tpchchallenge.q2.SupplyCostQuery;
import win.doyto.tpchchallenge.q20.AvailableQtyQuery;
import win.doyto.tpchchallenge.q20.PotentialPartPromotionQuery;
import win.doyto.tpchchallenge.q20.PotentialPartPromotionView;
import win.doyto.tpchchallenge.q20.SuppkeyQuery;
import win.doyto.tpchchallenge.q21.LineItemExistsQuery;
import win.doyto.tpchchallenge.q21.SuppliersWhoKeptOrdersWaitingQuery;
import win.doyto.tpchchallenge.q21.SuppliersWhoKeptOrdersWaitingView;
import win.doyto.tpchchallenge.q22.CustsaleQuery;
import win.doyto.tpchchallenge.q22.GlobalSalesOpportunityQuery;
import win.doyto.tpchchallenge.q22.GlobalSalesOpportunityView;
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
import win.doyto.tpchchallenge.q9.ProductTypeProfitMeasureHaving;
import win.doyto.tpchchallenge.q9.ProductTypeProfitMeasureView;
import win.doyto.tpchchallenge.q9.ProfitQuery;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
        Date orderDateLt = Date.valueOf(date.plusMonths(3));
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
        Date orderDateLt = Date.valueOf(date.plusYears(1));
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
        ProductTypeProfitMeasureHaving having = ProductTypeProfitMeasureHaving
                .builder().profitQuery(profitQuery).sort("nation;o_year,DESC").build();

        List<ProductTypeProfitMeasureView> list = dataQueryClient.aggregate(having, ProductTypeProfitMeasureView.class);

        assertThat(list).usingRecursiveFieldByFieldElementComparator(configuration)
                        .extracting("nation", "o_year", "sum_profit")
                        .containsExactly(Tuple.tuple("GERMANY", "1994", BigDecimal.valueOf(1460.6744)));
    }

    @Test
    void q10ReturnedItemReportingQuery() {
        LocalDate date = LocalDate.of(1994, 1, 1);

        ReturnedItemReportingQuery query = ReturnedItemReportingQuery
                .builder()
                .o_orderdateGe(Date.valueOf(date))
                .o_orderdateLt(Date.valueOf(date.plusMonths(12)))
                .l_returnflag("R")
                .sort("revenue,DESC")
                .build();

        List<ReturnedItemReportingView> list = dataQueryClient.aggregate(query, ReturnedItemReportingView.class);

        assertThat(list).usingRecursiveFieldByFieldElementComparator(configuration)
                        .extracting("c_custkey", "c_name", "revenue", "n_name")
                        .containsExactly(
                                Tuple.tuple(137, "Customer#000000137", BigDecimal.valueOf(60072.1000), "MOZAMBIQUE"),
                                Tuple.tuple(232, "Customer#000000232", BigDecimal.valueOf(8142.5640), "RUSSIA"),
                                Tuple.tuple(64, "Customer#000000064", BigDecimal.valueOf(2356.4544), "CANADA")
                        );
    }


    @Test
    void q11ImportantStockIdentificationQuery() {
        ValueHaving having = ValueHaving
                .builder()
                .valGt(ValueQuery.builder().n_name("GERMANY").build())
                .build();
        ImportantStockIdentificationQuery query = ImportantStockIdentificationQuery
                .builder()
                .n_name("GERMANY")
                .having(having)
                .sort("val,DESC")
                .build();

        List<ImportantStockIdentificationView> list = dataQueryClient.aggregate(query, ImportantStockIdentificationView.class);

        assertThat(list).hasSize(50)
                        .usingRecursiveFieldByFieldElementComparator(configuration)
                        .first()
                        .extracting("ps_partkey", "val")
                        .containsExactly(325, BigDecimal.valueOf(8667741.28));
    }

    @Test
    void q12ShippingModesAndOrderPriorityQuery() {
        LocalDate date = LocalDate.of(1992, 1, 1);
        ShippingModesAndOrderPriorityQuery query = ShippingModesAndOrderPriorityQuery
                .builder()
                .o_orderpriority1("1-URGENT")
                .o_orderpriority2("2-HIGH")
                .l_shipmodeIn(Arrays.asList("RAIL", "SHIP"))
                .l_receiptdateGe(Date.valueOf(date))
                .l_receiptdateLt(Date.valueOf(date.plusYears(1)))
                .sort("l_shipmode")
                .build();

        List<ShippingModesAndOrderPriorityView> list = dataQueryClient.aggregate(query, ShippingModesAndOrderPriorityView.class);

        assertThat(list)
                .usingRecursiveFieldByFieldElementComparator(configuration)
                .extracting("l_shipmode", "high_line_count", "low_line_count")
                .containsExactly(Tuple.tuple("RAIL", 0, 1));
    }

    @Test
    void q13CustomerDistributionQuery() {
        JoinOrders joinOrders = JoinOrders.builder().oCommentNotLike("%special%packages%").build();
        CustomerOrdersQuery customerOrdersQuery = CustomerOrdersQuery.builder().joinOrders(joinOrders).build();
        CustomerDistributionQuery query = CustomerDistributionQuery
                .builder()
                .customerOrdersQuery(customerOrdersQuery)
                .sort("custdist,DESC;c_count,DESC")
                .build();

        List<CustomerDistributionView> list = dataQueryClient.aggregate(query, CustomerDistributionView.class);

        assertThat(list).hasSize(29)
                        .first()
                        .extracting("c_count", "custdist")
                        .containsExactly(0, 166);
    }

    @Test
    void q14PromotionEffectQuery() {
        Date startShipdate = Date.valueOf(LocalDate.of(1995, 10, 1));
        Date endShipdate = Date.valueOf(LocalDate.of(1995, 12, 31));
        PromotionEffectQuery query = PromotionEffectQuery
                .builder()
                .pTypeStart("PROMO")
                .l_shipdateGe(startShipdate)
                .l_shipdateLt(endShipdate)
                .build();

        List<PromotionEffectView> list = dataQueryClient.aggregate(query, PromotionEffectView.class);

        assertThat(list).extracting("promo_revenue")
                        .containsExactly(100);
    }

    @Test
    void q15TopSupplierQuery() {
        Date startShipdate = Date.valueOf(LocalDate.of(1995, 1, 1));
        Date endShipdate = Date.valueOf(LocalDate.of(1995, 4, 1));
        LineItemQuery lineitemQuery = LineItemQuery
                .builder()
                .l_shipdateGe(startShipdate)
                .l_shipdateLt(endShipdate)
                .build();
        TopSupplierQuery query = TopSupplierQuery
                .builder()
                .revenueQuery(lineitemQuery)
                .total_revenue(new PageQuery())
                .sort("s_suppkey")
                .build();

        List<TopSupplierView> list = dataQueryClient.aggregate(query, TopSupplierView.class);

        assertThat(list).extracting("s_suppkey", "s_name", "total_revenue")
                        .containsExactly(Tuple.tuple(508, "Supplier#000000508", 60072.1000));
    }

    @Test
    void q16PartsSupplierRelationshipQuery() {
        PartsSupplierRelationshipQuery query = PartsSupplierRelationshipQuery
                .builder()
                .p_brandNot("Brand#45")
                .p_typeNotStart("MEDIUM POLISHED")
                .p_sizeIn(Arrays.asList(9, 14, 23, 45, 19, 3, 36, 9))
                .ps_suppkeyNotIn(SupplierQuery.builder().s_commentLike("%Customer%Complaints%").build())
                .sort("supplier_cnt,DESC;p_brand;p_type;p_size")
                .build();

        List<PartsSupplierRelationshipView> list = dataQueryClient.aggregate(query, PartsSupplierRelationshipView.class);

        assertThat(list).hasSize(136)
                        .extracting("p_brand", "p_type", "p_size", "supplier_cnt")
                        .startsWith(
                                Tuple.tuple("Brand#14", "PROMO BRUSHED STEEL", 9, 2),
                                Tuple.tuple("Brand#35", "SMALL POLISHED COPPER", 14, 2)
                        ).endsWith(
                                Tuple.tuple("Brand#55", "STANDARD BRUSHED COPPER", 3, 1),
                                Tuple.tuple("Brand#55", "STANDARD BRUSHED STEEL", 19, 1)
                        );
    }

    @Test
    void q17SmallQuantityOrderRevenueQuery() {
        SmallQuantityOrderRevenueQuery query = SmallQuantityOrderRevenueQuery
                .builder()
                .p_brand("Brand#23")
                .p_container("MED BOX")
                .l_quantityLt(LineItemQuery.builder().build())
                .build();

        List<SmallQuantityOrderRevenueView> list = dataQueryClient.aggregate(query, SmallQuantityOrderRevenueView.class);

        assertThat(list).extracting("avg_yearly")
                        .first().isNull();
    }

    @Test
    void q18LargeVolumeCustomerQuery() {
        LineItemQuantityHaving lqHaving = LineItemQuantityHaving.builder()
                                                                .sumL_quantityGt(40)
                                                                .build();
        LineItemQuantityQuery lqQuery = LineItemQuantityQuery.builder().having(lqHaving).build();
        LargeVolumeCustomerQuery query = LargeVolumeCustomerQuery
                .builder()
                .o_orderkeyIn(lqQuery)
                .sort("o_totalprice,desc;o_orderdate")
                .build();

        List<LargeVolumeCustomerView> list = dataQueryClient.aggregate(query, LargeVolumeCustomerView.class);

        assertThat(list).extracting("c_name", "sumL_quantity")
                        .containsExactly(
                                Tuple.tuple("Customer#000000007", 43),
                                Tuple.tuple("Customer#000000472", 44),
                                Tuple.tuple("Customer#000000265", 45),
                                Tuple.tuple("Customer#000000137", 44),
                                Tuple.tuple("Customer#000000250", 46)
                        );
    }

    @Test
    void q19DiscountedRevenueQuery() {
        LineItemFilter lineitemFilter1 = LineItemFilter
                .builder()
                .p_brand("Brand#12")
                .p_containerIn(Arrays.asList("SM CASE", "SM BOX", "SM PACK", "SM PKG"))
                .l_quantityGe(0)
                .l_quantityLe(10)
                .p_sizeGe(1)
                .p_sizeLe(5)
                .l_shipmodeIn(Arrays.asList("AIR       ", "REG AIR   "))
                .build();
        LineItemFilter lineitemFilter2 = LineItemFilter
                .builder()
                .p_brand("Brand#23")
                .p_containerIn(Arrays.asList("MED BAG", "MED BOX", "MED PKG", "MED PACK"))
                .l_quantityGe(10)
                .l_quantityLe(20)
                .p_sizeGe(1)
                .p_sizeLe(10)
                .l_shipmodeIn(Arrays.asList("AIR       ", "REG AIR   "))
                .build();
        LineItemFilter lineitemFilter3 = LineItemFilter
                .builder()
                .p_brand("Brand#43")
                .p_containerIn(Arrays.asList("LG CASE", "LG BOX", "LG PACK", "LG JAR    "))
                .l_quantityGe(20)
                .l_quantityLe(30)
                .p_sizeGe(1)
                .p_sizeLe(20)
                .l_shipmodeIn(Arrays.asList("AIR       ", "REG AIR   "))
                .build();
        DiscountedRevenueQuery query = DiscountedRevenueQuery
                .builder()
                .lineitemOr(Arrays.asList(lineitemFilter1, lineitemFilter2, lineitemFilter3))
                .l_shipinstruct("DELIVER IN PERSON")
                .build();

        List<DiscountedRevenueView> list = dataQueryClient.aggregate(query, DiscountedRevenueView.class);

        assertThat(list).extracting("revenue")
                        .containsExactly(31727.598);
    }

    @Test
    void q20PotentialPartPromotionQuery() {

        PartQuery partQuery = PartQuery.builder().p_nameStart("metallic").build();

        LocalDate date = LocalDate.of(1994, 1, 1);
        AvailableQtyQuery availableQtyQuery = AvailableQtyQuery
                .builder()
                .l_shipdateGe(Date.valueOf(date))
                .l_shipdateLt(Date.valueOf(date.plusYears(1)))
                .build();

        SuppkeyQuery suppkeyQuery = SuppkeyQuery
                .builder()
                .ps_partkeyIn(partQuery)
                .ps_availqtyGt(availableQtyQuery)
                .build();
        PotentialPartPromotionQuery query = PotentialPartPromotionQuery
                .builder()
                .s_suppkeyIn(suppkeyQuery)
                .n_name("GERMANY")
                .sort("s_name")
                .build();

        List<PotentialPartPromotionView> list = dataQueryClient.aggregate(query, PotentialPartPromotionView.class);

        assertThat(list).extracting("s_name", "s_address")
                        .containsExactly(Tuple.tuple("Supplier#000000328", "SMm24d WG62"));
    }

    @Test
    void q21SuppliersWhoKeptOrdersWaitingQuery() {
        SuppliersWhoKeptOrdersWaitingQuery query = SuppliersWhoKeptOrdersWaitingQuery
                .builder()
                .o_orderstatus("F")
                .n_name("CANADA")
                .lineitemExists(new LineItemExistsQuery())
                .lineitemNotExists(LineItemExistsQuery.builder().alias$lReceiptdateGtAlias$lCommitdate(true).build())
                .sort("numwait,DESC;s_name")
                .build();

        List<SuppliersWhoKeptOrdersWaitingView> list = dataQueryClient.aggregate(query, SuppliersWhoKeptOrdersWaitingView.class);

        assertThat(list).isEmpty();
    }

    @Test
    void q22GlobalSalesOpportunityQuery() {
        CustsaleQuery anotherQuery = CustsaleQuery
                .builder()
                .c_acctbalGt(0)
                .cntrycodeIn(Arrays.asList("28", "30"))
                .build();
        CustsaleQuery custsaleQuery = CustsaleQuery
                .builder()
                .cntrycodeIn(Arrays.asList("28", "30"))
                .c_acctbalGt2(anotherQuery)
                .ordersNotExists(new PageQuery())
                .build();
        GlobalSalesOpportunityQuery query = GlobalSalesOpportunityQuery
                .builder()
                .custsaleQuery(custsaleQuery)
                .sort("cntrycode")
                .build();

        List<GlobalSalesOpportunityView> list = dataQueryClient.aggregate(query, GlobalSalesOpportunityView.class);

        assertThat(list).extracting("cntrycode", "numcust", "totacctbal")
                        .containsExactly(
                                Tuple.tuple("28", 5L, 39073L),
                                Tuple.tuple("30", 6L, 44997L)
                        );
    }
}
