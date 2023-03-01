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

package win.doyto.tpchchallenge.domain.lineitem;

import lombok.Getter;
import lombok.Setter;
import win.doyto.query.annotation.ForeignKey;
import win.doyto.tpchchallenge.domain.part.PartEntity;
import win.doyto.tpchchallenge.domain.supplier.SupplierEntity;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Entity;

/**
 * LineItemEntity
 *
 * @author f0rb on 2023/2/16
 */
@Getter
@Setter
@Entity(name = "lineitem")
public class LineItemEntity extends LineItemKey {
    @ForeignKey(entity = SupplierEntity.class, field = "s_suppkey")
    private Integer l_suppkey;

    @ForeignKey(entity = PartEntity.class, field = "p_partkey")
    private Integer l_partkey;

    private Integer l_quantity;

    private BigDecimal l_extendedprice;

    private BigDecimal l_discount;

    private BigDecimal l_tax;

    private String l_returnflag;

    private String l_linestatus;

    private Date l_shipdate;

    private Date l_commitdate;

    private Date l_receiptdate;

    private String l_shipinstruct;

    private String l_shipmode;

    private String l_comment;

}
