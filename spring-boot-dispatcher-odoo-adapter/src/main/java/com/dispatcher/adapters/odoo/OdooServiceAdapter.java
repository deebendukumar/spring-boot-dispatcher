package com.dispatcher.adapters.odoo;

import com.dispatcher.adapters.odoo.service.LocationServiceInterface;
import com.dispatcher.adapters.odoo.service.WarehouseServiceInterface;
import com.dispatcher.adapters.odoo.service.ServiceAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("odoo")
public class OdooServiceAdapter implements ServiceAdapter {

    private final WarehouseServiceInterface warehouseServiceInterface;
    private final LocationServiceInterface locationServiceInterface;

    public OdooServiceAdapter(@Qualifier("OdooWarehouseServiceInterfaceImpl") WarehouseServiceInterface warehouseServiceInterface,
                              @Qualifier("OdooLocationServiceInterfaceImpl") LocationServiceInterface locationServiceInterface) {
        this.warehouseServiceInterface = warehouseServiceInterface;
        this.locationServiceInterface = locationServiceInterface;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public WarehouseServiceInterface getWarehouseServiceInterface() {
        return warehouseServiceInterface;
    }

    @Override
    public LocationServiceInterface getLocationServiceInterface() {
        return locationServiceInterface;
    }
}
