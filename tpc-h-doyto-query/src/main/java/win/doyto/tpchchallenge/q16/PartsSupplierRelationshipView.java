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

package win.doyto.tpchchallenge.q16;

import lombok.Getter;
import lombok.Setter;
import win.doyto.query.annotation.GroupBy;
import win.doyto.query.annotation.View;
import win.doyto.tpchchallenge.domain.part.PartEntity;
import win.doyto.tpchchallenge.domain.partsupp.PartSuppEntity;

import javax.persistence.Column;

/**
 * PartsSupplierRelationshipView
 *
 * @author f0rb on 2023/2/19
 * @since 1.0.1
 */
@Getter
@Setter
@View(PartSuppEntity.class)
@View(PartEntity.class)
public class PartsSupplierRelationshipView {
    @GroupBy
    private String p_brand;
    @GroupBy
    private String p_type;
    @GroupBy
    private Integer p_size;
    @Column(name = "COUNT(DISTINCT ps_suppkey)")
    private Integer supplier_cnt;
}
