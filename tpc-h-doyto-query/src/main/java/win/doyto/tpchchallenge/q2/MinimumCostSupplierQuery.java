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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import win.doyto.query.annotation.NoLabel;
import win.doyto.query.annotation.SubqueryV2;
import win.doyto.query.annotation.View;
import win.doyto.query.core.PageQuery;
import win.doyto.tpchchallenge.domain.nation.NationEntity;
import win.doyto.tpchchallenge.domain.part.PartEntity;
import win.doyto.tpchchallenge.domain.partsupp.PartSuppEntity;
import win.doyto.tpchchallenge.domain.region.RegionEntity;
import win.doyto.tpchchallenge.domain.supplier.SupplierEntity;

/**
 * MinimumCostSupplierQuery
 *
 * @author f0rb on 2023/2/16
 * @since 1.0.1
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MinimumCostSupplierQuery extends PageQuery {
    private Integer p_size;
    private String p_typeEnd;
    private String r_name;
    @SubqueryV2(MinSupplyCostView.class)
    private SupplyCostQuery ps_supplycost;

    @View(value = PartEntity.class, context = true)
    @View(PartSuppEntity.class)
    @View(SupplierEntity.class)
    @View(NationEntity.class)
    @View(RegionEntity.class)
    private static class MinSupplyCostView {
        @NoLabel
        private Integer minPs_supplycost;
    }
}
