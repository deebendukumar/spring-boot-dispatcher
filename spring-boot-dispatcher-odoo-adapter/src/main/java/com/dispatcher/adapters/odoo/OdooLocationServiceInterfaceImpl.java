package com.dispatcher.adapters.odoo;

import com.dispatcher.adapters.odoo.service.LocationServiceInterface;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("OdooLocationServiceInterfaceImpl")
public class OdooLocationServiceInterfaceImpl extends RouteBuilder implements LocationServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(OdooLocationServiceInterfaceImpl.class);

    @Override
    public void findAll() {
        logger.info("find all {odoo}");
    }

    @Override
    public void findById(String id) {
        logger.info("find by id {odoo}");
    }

    @Override
    public void configure() throws Exception {
    }
}
