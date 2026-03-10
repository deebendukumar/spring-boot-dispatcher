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
package com.dispatcher.app.config;

import com.dispatcher.adapters.odoo.service.LocationServiceInterface;
import com.dispatcher.adapters.odoo.service.ServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ServiceLoader;

@Configuration
public class DispatcherConfiguration {

    private final Environment environment;
    private final Adapters adapters;

    DispatcherConfiguration(Environment environment) {
        this.environment = environment;
        this.adapters = new Adapters();
    }

    @Bean
    public ServiceLoader<ServiceAdapter> warehouse() {
        ServiceLoader<ServiceAdapter> loader = ServiceLoader.load(ServiceAdapter.class);
        return loader;
    }

    @Bean
    public ServiceLoader<LocationServiceInterface> location() {
        ServiceLoader<LocationServiceInterface> loader = ServiceLoader.load(LocationServiceInterface.class);
        this.adapters.setLocationServiceLoader(loader);
        return loader;
    }
}
