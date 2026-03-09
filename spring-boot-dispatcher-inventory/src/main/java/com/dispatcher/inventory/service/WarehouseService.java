package com.dispatcher.inventory.service;

import com.dispatcher.inventory.contract.WarehouseResponse;
import com.dispatcher.inventory.contract.ZoneResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Service contract for warehouse-related operations used by WarehouseController.
 */
public interface WarehouseService {

    WarehouseResponse find(String acceptLanguage,
                           HttpServletRequest request,
                           HttpServletResponse response);

    WarehouseResponse findById(String id,
                               String acceptLanguage,
                               HttpServletRequest request,
                               HttpServletResponse response);

    ZoneResponse findZonesByWarehouseId(String id,
                                        String acceptLanguage,
                                        HttpServletRequest request,
                                        HttpServletResponse response);
}