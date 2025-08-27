/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dispatcher.common.model.product;

import com.dispatcher.common.base.Auditable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonPropertyOrder({
        "id"
})
@Data
@Builder
public class Dimension implements Auditable {

    //height
    //width
    //depth
    //volume
    //grams
    //weight
    //weight_unit

    //inventory_item_id
    //inventory_management
    //inventory_policy
    //inventory_quantity

    //price
    //requires_shipping
    //sku
    //tags
    //batch_no_flag this flag indicates if the product required lot tracking
    //bin_loc_flag  this flag indicates if the product is stored in bin location
    //fifo
    //active

    //standard_cost
    //standard_currency
    //vat_code
    //gst_code
    //ean

    //fragile
    //hazmat - Whether a hazardous material or not
    //hazmat_id
    //max_stack maximum stack size
    //obsolete_product disable further receiving of the product

    //packed_depth
    //packed_height
    //packed_volume
    //packed_weight
    //packed_width

    //user_def_1
    //user_def_2
    //user_def_3
    //user_def_4
    //user_def_5
    //user_def_6
    //user_def_7
    //user_def_8
}
