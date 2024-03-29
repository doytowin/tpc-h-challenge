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

package win.doyto.tpchchallenge.q11;

import lombok.*;
import win.doyto.query.annotation.Subquery;
import win.doyto.query.core.Having;
import win.doyto.tpchchallenge.domain.nation.NationEntity;
import win.doyto.tpchchallenge.domain.partsupp.PartSuppEntity;
import win.doyto.tpchchallenge.domain.supplier.SupplierEntity;

/**
 * ValueHaving
 *
 * @author f0rb on 2023/2/18
 * @since 1.0.1
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValueHaving implements Having {
    @Subquery(select = "SUM(ps_supplycost * ps_availqty) * 0.0001000000e-2",
            from = {PartSuppEntity.class, SupplierEntity.class, NationEntity.class})
    private ValueQuery valGt;
}
