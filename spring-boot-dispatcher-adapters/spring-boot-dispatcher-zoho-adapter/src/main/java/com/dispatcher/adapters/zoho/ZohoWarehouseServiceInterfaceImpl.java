package com.dispatcher.adapters.zoho;

import com.dispatcher.common.model.warehouse.Warehouse;
import com.dispatcher.spi.WarehouseServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("ZohoWarehouseServiceInterfaceImpl")
public class ZohoWarehouseServiceInterfaceImpl implements WarehouseServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(ZohoWarehouseServiceInterfaceImpl.class);

    @Override
    public List<Warehouse> findAll() {
        return null;
    }

    @Override
    public Warehouse findById(String id) {
        return null;
    }
}
