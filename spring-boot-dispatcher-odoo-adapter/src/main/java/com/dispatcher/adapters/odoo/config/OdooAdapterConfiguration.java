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

package com.dispatcher.adapters.odoo.config;

import com.dispatcher.adapters.odoo.OdooSession;
import com.dispatcher.adapters.odoo.RPCProtocol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.dispatcher.adapters.odoo"})
public class OdooAdapterConfiguration {

    private final OdooProperties environment;

    OdooAdapterConfiguration(OdooProperties environment) {
        this.environment = environment;
    }

    @Bean
    public OdooSession session() throws Exception {
        OdooSession session = new OdooSession(RPCProtocol.RPC_HTTP,
                environment.getHost(),
                Integer.parseInt(environment.getPort()),
                environment.getDatabase(),
                environment.getUsername(),
                environment.getPassword());
        return session;
    }
}

