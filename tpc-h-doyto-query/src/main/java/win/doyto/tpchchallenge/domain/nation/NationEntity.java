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

package win.doyto.tpchchallenge.domain.nation;

import lombok.Getter;
import lombok.Setter;
import win.doyto.query.annotation.ForeignKey;
import win.doyto.query.entity.Persistable;
import win.doyto.query.validation.PatchGroup;
import win.doyto.query.validation.UpdateGroup;
import win.doyto.tpchchallenge.domain.region.RegionEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * NationEntity
 *
 * @author f0rb on 2023/2/17
 */
@Getter
@Setter
public class NationEntity implements Persistable<Integer> {
    @Id
    @GeneratedValue
    private Integer n_nationkey;
    private String n_name;
    @ForeignKey(entity = RegionEntity.class, field = "r_regionkey")
    private Integer n_regionkey;
    private String n_comment;

    @Override
    @NotNull(groups = {UpdateGroup.class, PatchGroup.class})
    public Integer getId() {
        return this.n_nationkey;
    }

    @Override
    public void setId(Integer id) {
        this.n_nationkey = id;
    }
}
