/*
 * Copyright © 2019-2023 Forb Yuan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package win.doyto.tpchchallenge.q10;

import lombok.Getter;
import lombok.Setter;
import win.doyto.query.annotation.GroupBy;
import win.doyto.query.annotation.View;
import win.doyto.tpchchallenge.domain.customer.CustomerEntity;
import win.doyto.tpchchallenge.domain.lineitem.LineItemEntity;
import win.doyto.tpchchallenge.domain.nation.NationEntity;
import win.doyto.tpchchallenge.domain.orders.OrdersEntity;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * ReturnedItemReportingView
 *
 * @author f0rb on 2023/2/18
 * @since 1.0.1
 */
@Getter
@Setter
@View(CustomerEntity.class)
@View(OrdersEntity.class)
@View(LineItemEntity.class)
@View(NationEntity.class)
public class ReturnedItemReportingView {
    @GroupBy
    private Integer c_custkey;
    @GroupBy
    private String c_name;
    @Column(name = "SUM(l_extendedprice * (1 - l_discount))")
    private BigDecimal revenue;
    @GroupBy
    private BigDecimal c_acctbal;
    @GroupBy
    private String n_name;
    @GroupBy
    private String c_address;
    @GroupBy
    private String c_phone;
    @GroupBy
    private String c_comment;
}
