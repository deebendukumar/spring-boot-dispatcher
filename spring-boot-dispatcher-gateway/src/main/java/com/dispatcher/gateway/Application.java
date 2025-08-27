/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dispatcher.gateway;

import com.dispatcher.common.utils.FileUtils;
import com.dispatcher.common.utils.TrustStoreHandler;
import com.dispatcher.gateway.config.KeycloakProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
@ConfigurationPropertiesScan("com.dispatcher.gateway")
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static final String STORE_TYPE = "PKCS12";
    private static final String trustStorePath = System.getProperty("java.io.tmpdir") + "test.truststore";
    private static final char[] trustStorePassword = "Reset@123".toCharArray();
    private static final String keyStorePath = System.getProperty("java.io.tmpdir") + "test.keystore";
    private static final char[] keyStorePassword = "Reset@321".toCharArray();

    private final Environment environment;
    private final KeycloakProperties keycloakProperties;

    public Application(Environment environment, KeycloakProperties keycloakProperties) {
        this.environment = environment;
        this.keycloakProperties = keycloakProperties;
    }

    /**
     * void main.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * The @PostConstruct annotation can only be used on methods that are declared as public or protected.
     * It cannot be used on static methods or methods that have a return type other than void.
     */
    @PostConstruct
    public void init() {
        //TODO
    }

    /**
     * load external certificates from specified path
     *
     * @throws Exception
     */
    private void loadCertificatesFromFolder() throws Exception {
        Set<String> files = FileUtils.listFiles(this.environment.getProperty("spring.security.cert-file") + File.separator + "certs");
        if (!files.isEmpty()) {
            Iterator<String> iterator = files.iterator();
            while (iterator.hasNext()) {
                File file = ResourceUtils.getFile("" + this.environment.getProperty("spring.security.cert-file")
                        + File.separator
                        + "certs"
                        + File.separator
                        + iterator.next());
                logger.info("" + file.getAbsolutePath());
                new TrustStoreHandler(trustStorePath, trustStorePassword, STORE_TYPE).createStoreWith(file.getAbsolutePath());
                System.setProperty("javax.net.ssl.trustStore", trustStorePath);
                System.setProperty("javax.net.ssl.trustStorePassword", String.valueOf(trustStorePassword));
                System.setProperty("javax.net.ssl.trustStoreType", STORE_TYPE);
            }
        }
    }
}
