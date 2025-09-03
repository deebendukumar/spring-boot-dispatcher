package com.dispatcher.adapters.zoho;

import com.dispatcher.spi.LocationServiceInterface;
import com.dispatcher.spi.ServiceAdapter;
import com.dispatcher.spi.WarehouseServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("zoho")
public class ZohoServiceAdapter implements ServiceAdapter {

    public static String _SERVICE_NAME = "zoho";

    private String name = "zoho";
    private WarehouseServiceInterface warehouseServiceInterface;
    private LocationServiceInterface locationServiceInterface;

    public ZohoServiceAdapter(@Qualifier("ZohoWarehouseServiceInterfaceImpl") WarehouseServiceInterface warehouseServiceInterface,
                              @Qualifier("ZohoLocationServiceInterfaceImpl") LocationServiceInterface locationServiceInterface) {
        this.warehouseServiceInterface = warehouseServiceInterface;
        this.locationServiceInterface = locationServiceInterface;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public WarehouseServiceInterface getWarehouseServiceInterface() {
        return new ZohoWarehouseServiceInterfaceImpl();
    }

    @Override
    public LocationServiceInterface getLocationServiceInterface() {
        return new ZohoLocationServiceInterfaceImpl();
    }
}
