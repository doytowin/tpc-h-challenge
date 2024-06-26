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

package win.doyto.tpchchallenge.q14;

import lombok.Getter;
import lombok.Setter;
import win.doyto.query.annotation.View;
import win.doyto.tpchchallenge.domain.lineitem.LineItemEntity;
import win.doyto.tpchchallenge.domain.part.PartEntity;

import javax.persistence.Column;

/**
 * PromotionEffectView
 *
 * @author f0rb on 2023/2/19
 * @since 1.0.1
 */
@Getter
@Setter
@View(LineItemEntity.class)
@View(PartEntity.class)
public class PromotionEffectView {
    @Column(name = "100.00 * SUM(CASE WHEN #{pTypeStart} THEN l_extendedprice * (1 - l_discount) ELSE 0 END) " +
            "/ SUM(l_extendedprice * (1 - l_discount))")
    private Integer promo_revenue;
}
