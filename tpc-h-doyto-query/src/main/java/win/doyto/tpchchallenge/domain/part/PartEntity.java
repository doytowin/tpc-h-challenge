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

package win.doyto.tpchchallenge.domain.part;

import lombok.Getter;
import lombok.Setter;
import win.doyto.query.entity.Persistable;
import win.doyto.query.validation.PatchGroup;
import win.doyto.query.validation.UpdateGroup;

import java.math.BigDecimal;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * PartEntity
 *
 * @author f0rb on 2023/2/17l_orderkey
 */
@Getter
@Setter
public class PartEntity implements Persistable<Integer> {
    @Id
    @GeneratedValue
    private Integer p_partkey;
    private String p_name;
    private String p_mfgr;
    private String p_brand;
    private String p_type;
    private Integer p_size;
    private String p_container;
    private BigDecimal p_retailprice;
    private String p_comment;

    @Override
    @NotNull(groups = {UpdateGroup.class, PatchGroup.class})
    public Integer getId() {
        return this.p_partkey;
    }

    @Override
    public void setId(Integer id) {
        this.p_partkey = id;
    }
}
