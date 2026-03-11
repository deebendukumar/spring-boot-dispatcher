package com.dispatcher.adapters.odoo;

import com.dispatcher.adapter.common.WarehouseAdapter;
import org.springframework.stereotype.Component;

@Component
public class OdooWarehouseAdapter implements WarehouseAdapter {

    private final OdooSession odooSession;

    public OdooWarehouseAdapter(OdooSession odooSession) {
        this.odooSession = odooSession;
    }

    @Override
    public String getPlatform() {
        return "odoo";
    }

    @Override
    public void login() {

    }
}
