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

package win.doyto.tpchchallenge.q9;

import lombok.Getter;
import lombok.Setter;
import win.doyto.query.annotation.View;
import win.doyto.tpchchallenge.domain.lineitem.LineItemEntity;
import win.doyto.tpchchallenge.domain.nation.NationEntity;
import win.doyto.tpchchallenge.domain.orders.OrdersEntity;
import win.doyto.tpchchallenge.domain.part.PartEntity;
import win.doyto.tpchchallenge.domain.partsupp.PartSuppEntity;
import win.doyto.tpchchallenge.domain.supplier.SupplierEntity;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * ProfitView
 *
 * @author f0rb on 2023/2/18
 * @since 1.0.1
 */
@Getter
@Setter
@View(PartEntity.class)
@View(SupplierEntity.class)
@View(LineItemEntity.class)
@View(PartSuppEntity.class)
@View(OrdersEntity.class)
@View(NationEntity.class)
public class ProfitView {
    @Column(name = "n_name")
    private String nation;
    @Column(name = "YEAR(o_orderdate)")
    private String o_year;
    @Column(name = "l_extendedprice * (1 - l_discount) - ps_supplycost * l_quantity")
    private BigDecimal amount;
}
