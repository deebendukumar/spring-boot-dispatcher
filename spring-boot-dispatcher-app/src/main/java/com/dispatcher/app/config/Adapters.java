package com.dispatcher.app.config;

import com.dispatcher.adapters.odoo.service.LocationServiceInterface;
import com.dispatcher.adapters.odoo.service.WarehouseServiceInterface;
import com.dispatcher.adapters.odoo.service.ServiceAdapter;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ServiceLoader;

@Data
@Component
public class Adapters {

    private ServiceLoader<WarehouseServiceInterface> warehouseServiceLoader;
    private ServiceLoader<LocationServiceInterface> locationServiceLoader;
    private ServiceLoader<ServiceAdapter> asnServiceLoader;
    private ServiceLoader<ServiceAdapter> barcodeServiceLoader;
    private ServiceLoader<ServiceAdapter> contactServiceLoader;
    private ServiceLoader<ServiceAdapter> inventoryServiceLoader;
    private ServiceLoader<ServiceAdapter> orderServiceLoader;
    private ServiceLoader<ServiceAdapter> purchaseOrderServiceLoader;
    private ServiceLoader<ServiceAdapter> putawayServiceLoader;
    private ServiceLoader<ServiceAdapter> skuServiceLoader;
    private ServiceLoader<ServiceAdapter> stockServiceLoader;
    private ServiceLoader<ServiceAdapter> transferServiceLoader;
}
