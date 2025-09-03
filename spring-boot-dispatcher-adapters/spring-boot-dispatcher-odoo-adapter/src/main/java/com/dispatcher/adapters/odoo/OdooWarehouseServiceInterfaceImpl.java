package com.dispatcher.adapters.odoo;

import com.dispatcher.common.model.warehouse.Warehouse;
import com.dispatcher.odoo.OdooSession;
import com.dispatcher.odoo.exception.OdooApiException;
import com.dispatcher.odoo.facade.OdooApiClient;
import com.dispatcher.odoo.facade.OdooWarehouseApiClient;
import com.dispatcher.spi.WarehouseServiceInterface;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Qualifier("OdooWarehouseServiceInterfaceImpl")
public class OdooWarehouseServiceInterfaceImpl implements WarehouseServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(OdooWarehouseServiceInterfaceImpl.class);

    private OdooApiClient apiClient;
    private OdooSession session;

    public OdooWarehouseServiceInterfaceImpl(OdooSession session) {
        this.session = session;
        try {
            apiClient = new OdooWarehouseApiClient(session);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        } catch (OdooApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> result = new ArrayList<>();
        try {
            List<HashMap<String, Object>> list = apiClient.findAll();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        } catch (OdooApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Warehouse findById(String id) {
        Warehouse result = null;
        try {
            HashMap<String, Object> map = apiClient.findByPKey(Integer.parseInt(id));
        } catch (OdooApiException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return result;
    }
}
