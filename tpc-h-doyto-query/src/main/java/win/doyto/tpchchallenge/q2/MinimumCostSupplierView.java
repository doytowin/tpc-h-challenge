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

package win.doyto.tpchchallenge.q2;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import win.doyto.query.annotation.CompositeView;
import win.doyto.tpchchallenge.domain.nation.NationEntity;
import win.doyto.tpchchallenge.domain.part.PartEntity;
import win.doyto.tpchchallenge.domain.partsupp.PartSuppEntity;
import win.doyto.tpchchallenge.domain.region.RegionEntity;
import win.doyto.tpchchallenge.domain.supplier.SupplierEntity;

import java.math.BigDecimal;

/**
 * MinimumCostSupplierView
 *
 * @author f0rb on 2023/2/16
 * @since 1.0.1
 */
@Getter
@Setter
@CompositeView({PartEntity.class, SupplierEntity.class, PartSuppEntity.class, NationEntity.class, RegionEntity.class})
public class MinimumCostSupplierView {
    private BigDecimal s_acctbal;
    private String s_name;
    private String n_name;
    private Integer p_partkey;
    private String p_mfgr;
    private String s_address;
    private String s_phone;
    private String s_comment;

    public void setS_name(String s_name) {
        this.s_name = StringUtils.trim(s_name);
    }

    public void setN_name(String n_name) {
        this.n_name = StringUtils.trim(n_name);
    }
}
