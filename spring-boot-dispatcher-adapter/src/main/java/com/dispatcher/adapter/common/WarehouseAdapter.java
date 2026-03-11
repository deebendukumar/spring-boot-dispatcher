package com.dispatcher.adapter.common;

public interface  WarehouseAdapter {

    String getPlatform();

    //warehouse management functions
    void listWarehouses();
    void getWarehouseInfo(String warehouseId);
    void activateWarehouse(String id);
    void deactivateWarehouse(String id);
}
