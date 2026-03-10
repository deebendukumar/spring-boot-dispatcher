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

package com.dispatcher.adapters.odoo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { WarehouseJsonMapper.class })
public interface WarehouseJsonMapper {

    WarehouseJsonMapper INSTANCE = Mappers.getMapper(WarehouseJsonMapper.class);

//    @Mapping(target = "__last_update", ignore = true)
//    @Mapping(target = "resupply_route_ids", ignore = true)
//    @Mapping(target = "resupply_wh_ids", ignore = true)
//    @Mapping(target = "route_ids", ignore = true)
//    @Mapping(target = "write_date", ignore = true)
//    @Mapping(source = "lot_stock_id", target = "lotStockId")
//    @Mapping(source = "wh_pack_stock_loc_id", target = "whPackStockLocId")
//    @Mapping(source = "crossdock_route_id", target = "crossdockRouteId")
//    @Mapping(source = "delivery_route_id", target = "deliveryRouteId")
//    @Mapping(source = "pack_type_id", target = "packTypeId")
//    @Mapping(source = "partner_id", target = "partnerId")
//    @Mapping(source = "in_type_id", target = "inTypeId")
//    @Mapping(source = "create_date", target = "createDate")
//    @Mapping(source = "pick_type_id", target = "pickTypeId")
//    @Mapping(source = "reception_steps", target = "receptionSteps")
//    @Mapping(source = "company_id", target = "companyId")
//    @Mapping(source = "reception_route_id", target = "receptionRouteId")
//    @Mapping(source = "wh_output_stock_loc_id", target = "whOutputStockLocId")
//    @Mapping(source = "display_name", target = "displayName")
//    @Mapping(source = "view_location_id", target = "viewLocationId")
//    @Mapping(source = "delivery_steps", target = "deliverySteps")
//    @Mapping(source = "wh_qc_stock_loc_id", target = "whQcStockLocId")
//    @Mapping(source = "wh_input_stock_loc_id", target = "whInputStockLocId")
//    @Mapping(source = "out_type_id", target = "outTypeId")
//    @Mapping(source = "return_type_id", target = "returnTypeId")
//    Warehouse toWarehouse(OdooWarehouseObject.FieldsOdoo fieldsOdoo);
}
