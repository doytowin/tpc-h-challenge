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

package win.doyto.tpchchallenge.domain.region;

import lombok.Getter;
import lombok.Setter;
import win.doyto.query.entity.Persistable;
import win.doyto.query.validation.PatchGroup;
import win.doyto.query.validation.UpdateGroup;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * RegionEntity
 *
 * @author f0rb on 2023/2/17l_orderkey
 */
@Getter
@Setter
public class RegionEntity implements Persistable<Integer> {
    @Id
    @GeneratedValue
    private Integer r_regionkey;
    private String r_name;
    private String r_comment;

    @Override
    @NotNull(groups = {UpdateGroup.class, PatchGroup.class})
    public Integer getId() {
        return this.r_regionkey;
    }

    @Override
    public void setId(Integer id) {
        this.r_regionkey = id;
    }

}
