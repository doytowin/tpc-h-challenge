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

package win.doyto.tpchchallenge.q22;

import lombok.Getter;
import lombok.Setter;
import win.doyto.query.annotation.View;
import win.doyto.tpchchallenge.domain.customer.CustomerEntity;

import javax.persistence.Column;

/**
 * CustsaleView
 *
 * @author f0rb on 2023/7/13
 * @since 1.0.2
 */
@Getter
@Setter
@View(CustomerEntity.class)
public class CustsaleView {
   @Column(name = "substring(c_phone from 1 for 2)")
   private String cntrycode;
   private Long c_acctbal;
}
