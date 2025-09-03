package com.dispatcher.adapters.odoo;

import com.dispatcher.spi.LocationServiceInterface;
import com.dispatcher.spi.WarehouseServiceInterface;
import com.dispatcher.spi.ServiceAdapter;
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
