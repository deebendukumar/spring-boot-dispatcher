
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
package com.dispatcher.gateway.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class TrustStoreHandler {
    private final String storePath;
    private final char[] storePassword;
    private final String storeInstance;


    public TrustStoreHandler(String storePath, char[] storePassword, String storeInstance) {
        this.storePath = storePath;
        this.storePassword = storePassword;
        this.storeInstance = storeInstance;
    }

    public void createStoreWith(String... certs) throws Exception {
        KeyStore trustStore = KeyStore.getInstance(storeInstance);
        trustStore.load(null, null);

        for (String cert : certs) {
            try (FileInputStream fileInputStream = new FileInputStream(cert); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
                while (bufferedInputStream.available() > 0) {
                    System.out.println("adding " + cert + " to " + storePath);
                    Certificate certificate = CertificateFactory.getInstance("X.509").generateCertificate(bufferedInputStream);
                    trustStore.setCertificateEntry(cert, certificate);
                }
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(storePath)) {
            trustStore.store(fileOutputStream, storePassword);
        }
    }
}
