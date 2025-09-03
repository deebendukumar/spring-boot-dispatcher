package com.dispatcher.adapters.zoho;

import com.dispatcher.spi.LocationServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ZohoLocationServiceInterfaceImpl")
public class ZohoLocationServiceInterfaceImpl implements LocationServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(ZohoLocationServiceInterfaceImpl.class);

    @Override
    public void findAll() {
        logger.info("find all {zoho}");
    }

    @Override
    public void findById(String id) {
        logger.info("find by id {zoho}");
    }
}
