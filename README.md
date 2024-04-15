[![License](https://img.shields.io/:license-apache-brightgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Sonar Stats](https://sonarcloud.io/api/project_badges/measure?project=win.doyto%3Atpc-h-challenge&metric=alert_status)](https://sonarcloud.io/dashboard?id=win.doyto%3Atpc-h-challenge)
[![Code Lines](https://sonarcloud.io/api/project_badges/measure?project=win.doyto%3Atpc-h-challenge&metric=ncloc)](https://sonarcloud.io/component_measures?id=win.doyto%3Atpc-h-challenge&metric=ncloc)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=win.doyto%3Atpc-h-challenge&metric=coverage)](https://sonarcloud.io/component_measures?id=win.doyto%3Atpc-h-challenge&metric=coverage)

TPC-H Challenge - Minimum Code for 22 TPC-H Queries.
---

## Introduction

DoytoQuery supports all 22 TPC-H queries by the release v1.0.2.

This repo aims to present a challenge to see if any other Java ORM frameworks
can perform the 22 TPC-H queries with less code than DoytoQuery.

<table>
    <tr>
        <td>SN.</td>
        <td>Query Name</td>
        <td>Classes</td>
        <td>Source Code Lines\tnote{1}</td>
        <td>Critical Code Lines\tnote{2}</td>
    </tr>
    <tr>
        <td rowspan="2">1</td>
        <td rowspan="2">Pricing Summary Report Query</td>
        <td>PricingSummaryQuery</td>
        <td>17</td>
        <td>4</td>
    </tr>
    <tr>
        <td>PricingSummaryView</td>
        <td>33</td>
        <td>23</td>
    </tr>
    <tr>
        <td rowspan="3">2</td>
        <td rowspan="3">Minimum Cost Supplier Query</td>
        <td>MinimumCostSupplierQuery</td>
        <td>28</td>
        <td>9</td>
    </tr>
    <tr>
        <td>MinimumCostSupplierView</td>
        <td>27</td>
        <td>15</td>
    </tr>
    <tr>
        <td>SupplyCostQuery</td>
        <td>15</td>
        <td>3</td>
    </tr>
    <tr>
        <td rowspan="2">3</td>
        <td rowspan="2">Shipping Priority Query</td>
        <td>ShippingPriorityQuery</td>
        <td>19</td>
        <td>5</td>
    </tr>
    <tr>
        <td>ShippingPriorityView</td>
        <td>25</td>
        <td>13</td>
    </tr>
    <tr>
        <td rowspan="3">4</td>
        <td rowspan="3">Order Priority Checking Query</td>
        <td>OrderPriorityCheckingQuery</td>
        <td>21</td>
        <td>6</td>
    </tr>
    <tr>
        <td>OrderPriorityCheckingView</td>
        <td>15</td>
        <td>7</td>
    </tr>
    <tr>
        <td>LineitemReceiptQuery</td>
        <td>15</td>
        <td>5</td>
    </tr>
    <tr>
        <td rowspan="2">5</td>
        <td rowspan="2">Local Supplier Volume Query</td>
        <td>LocalSupplierVolumeQuery</td>
        <td>19</td>
        <td>5</td>
    </tr>
    <tr>
        <td>LocalSupplierVolumeView</td>
        <td>27</td>
        <td>12</td>
    </tr>
    <tr>
        <td rowspan="2">6</td>
        <td rowspan="2">Forecasting Revenue Change Query</td>
        <td>ForecastingRevenueChangeQuery</td>
        <td>32</td>
        <td>15</td>
    </tr>
    <tr>
        <td>ForecastingRevenueChangeView</td>
        <td>14</td>
        <td>5</td>
    </tr>
    <tr>
        <td rowspan="5">7</td>
        <td rowspan="5">Volume Shipping Query</td>
        <td>NameComparison</td>
        <td>12</td>
        <td>4</td>
    </tr>
    <tr>
        <td>ShippingQuery</td>
        <td>19</td>
        <td>5</td>
    </tr>
    <tr>
        <td>ShippingView</td>
        <td>29</td>
        <td>16</td>
    </tr>
    <tr>
        <td>VolumeShippingQuery</td>
        <td>16</td>
        <td>3</td>
    </tr>
    <tr>
        <td>VolumeShippingView</td>
        <td>20</td>
        <td>11</td>
    </tr>
    <tr>
        <td rowspan="4">8</td>
        <td rowspan="4">National Market Share Query</td>
        <td>AllNationsQuery</td>
        <td>19</td>
        <td>6</td>
    </tr>
    <tr>
        <td>AllNationsView</td>
        <td>31</td>
        <td>16</td>
    </tr>
    <tr>
        <td>NationalMarketShareQuery</td>
        <td>19</td>
        <td>5</td>
    </tr>
    <tr>
        <td>NationalMarketShareView</td>
        <td>16</td>
        <td>7</td>
    </tr>
    <tr>
        <td rowspan="4">9</td>
        <td rowspan="4">Product Type Profit Measure Query</td>
        <td>ProductTypeProfitMeasureQuery</td>
        <td>16</td>
        <td>3</td>
    </tr>
    <tr>
        <td>ProductTypeProfitMeasureView</td>
        <td>18</td>
        <td>9</td>
    </tr>
    <tr>
        <td>ProfitQuery</td>
        <td>15</td>
        <td>3</td>
    </tr>
    <tr>
        <td>ProfitView</td>
        <td>28</td>
        <td>14</td>
    </tr>
    <tr>
        <td rowspan="2">10</td>
        <td rowspan="2">Returned Item Reporting Query</td>
        <td>ReturnedItemReportingQuery</td>
        <td>19</td>
        <td>5</td>
    </tr>
    <tr>
        <td>ReturnedItemReportingView</td>
        <td>35</td>
        <td>22</td>
    </tr>
    <tr>
        <td rowspan="4">11</td>
        <td rowspan="4">Important Stock Identification Query</td>
        <td>ImportantStockIdentificationQuery</td>
        <td>17</td>
        <td></td>
    </tr>
    <tr>
        <td>ImportantStockIdentificationView</td>
        <td>21</td>
        <td></td>
    </tr>
    <tr>
        <td>ValueHaving</td>
        <td>17</td>
        <td></td>
    </tr>
    <tr>
        <td>ValueQuery</td>
        <td>15</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="2">12</td>
        <td rowspan="2">Shipping Modes and Order Priority Query</td>
        <td>ShippingModesAndOrderPriorityQuery</td>
        <td>26</td>
        <td></td>
    </tr>
    <tr>
        <td>ShippingModesAndOrderPriorityView</td>
        <td>20</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="5">13</td>
        <td rowspan="5">Customer Distribution Query</td>
        <td>CustomerDistributionQuery</td>
        <td>16</td>
        <td></td>
    </tr>
    <tr>
        <td>CustomerDistributionView</td>
        <td>15</td>
        <td></td>
    </tr>
    <tr>
        <td>CustomerOrdersQuery</td>
        <td>21</td>
        <td></td>
    </tr>
    <tr>
        <td>CustomerOrdersView</td>
        <td>16</td>
        <td></td>
    </tr>
    <tr>
        <td>JoinOrders</td>
        <td>15</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="2">14</td>
        <td rowspan="2">Promotion Effect Query</td>
        <td>PromotionEffectQuery</td>
        <td>21</td>
        <td></td>
    </tr>
    <tr>
        <td>PromotionEffectView</td>
        <td>16</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="4">15</td>
        <td rowspan="4">Top Supplier Query</td>
        <td>LineItemRevenueView</td>
        <td>21</td>
        <td></td>
    </tr>
    <tr>
        <td>RevenueView</td>
        <td>3</td>
        <td></td>
    </tr>
    <tr>
        <td>TopSupplierQuery</td>
        <td>20</td>
        <td></td>
    </tr>
    <tr>
        <td>TopSupplierView</td>
        <td>18</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="2">16</td>
        <td rowspan="2">Parts/Supplier Relationship Query</td>
        <td>PartsSupplierRelationshipQuery</td>
        <td>24</td>
        <td></td>
    </tr>
    <tr>
        <td>PartsSupplierRelationshipView</td>
        <td>22</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="2">17</td>
        <td rowspan="2">Small-Quantity-Order Revenue Query</td>
        <td>SmallQuantityOrderRevenueQuery</td>
        <td>25</td>
        <td></td>
    </tr>
    <tr>
        <td>SmallQuantityOrderRevenueView</td>
        <td>16</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="4">18</td>
        <td rowspan="4">Large Volume Customer Query</td>
        <td>LargeVolumeCustomerQuery</td>
        <td>19</td>
        <td></td>
    </tr>
    <tr>
        <td>LargeVolumeCustomerView</td>
        <td>28</td>
        <td></td>
    </tr>
    <tr>
        <td>LineitemQuantityHaving</td>
        <td>15</td>
        <td></td>
    </tr>
    <tr>
        <td>LineitemQuantityQuery</td>
        <td>16</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="3">19</td>
        <td rowspan="3">Discounted Revenue Query</td>
        <td>DiscountedRevenueQuery</td>
        <td>18</td>
        <td></td>
    </tr>
    <tr>
        <td>DiscountedRevenueView</td>
        <td>15</td>
        <td></td>
    </tr>
    <tr>
        <td>LineitemFilter</td>
        <td>22</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="4">20</td>
        <td rowspan="4">Potential Part Promotion Query</td>
        <td>AvailableQtyQuery</td>
        <td>18</td>
        <td>8</td>
    </tr>
    <tr>
        <td>PotentialPartPromotionQuery</td>
        <td>20</td>
        <td>5</td>
    </tr>
    <tr>
        <td>PotentialPartPromotionView</td>
        <td>13</td>
        <td>5</td>
    </tr>
    <tr>
        <td>SuppkeyQuery</td>
        <td>22</td>
        <td>6</td>
    </tr>
    <tr>
        <td rowspan="3">21</td>
        <td rowspan="3">Suppliers Who Kept Orders Waiting Query</td>
        <td>LineItemExistsQuery</td>
        <td>14</td>
        <td>5</td>
    </tr>
    <tr>
        <td>SuppliersWhoKeptOrdersWaitingQuery</td>
        <td>23</td>
        <td>12</td>
    </tr>
    <tr>
        <td>SuppliersWhoKeptOrdersWaitingView</td>
        <td>22</td>
        <td>6</td>
    </tr>
    <tr>
        <td rowspan="4">22</td>
        <td rowspan="4">Global Sales Opportunity Query</td>
        <td>CustsaleQuery</td>
        <td>28</td>
        <td>11</td>
    </tr>
    <tr>
        <td>CustsaleView</td>
        <td>14</td>
        <td>5</td>
    </tr>
    <tr>
        <td>GlobalSalesOpportunityQuery</td>
        <td>16</td>
        <td>4</td>
    </tr>
    <tr>
        <td>GlobalSalesOpportunityView</td>
        <td>17</td>
        <td>8</td>
    </tr>
</table>
