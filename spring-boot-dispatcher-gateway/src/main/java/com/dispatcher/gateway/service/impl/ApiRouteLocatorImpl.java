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

package com.dispatcher.gateway.service.impl;

import com.dispatcher.gateway.config.ApplicationProperties;
import com.dispatcher.gateway.document.ApiRoute;
import com.dispatcher.gateway.filter.GatewayPostFilter;
import com.dispatcher.gateway.filter.GatewayPreFilter;
import com.dispatcher.gateway.service.ApiRouteService;
import com.dispatcher.gateway.util.Passport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ApiRouteLocatorImpl implements RouteLocator {

    private static final Logger logger = LoggerFactory.getLogger(ApiRouteLocatorImpl.class);

    private final Environment environment;
    private final ApplicationProperties applicationProperties;
    private final ApiRouteService service;
    private final RouteLocatorBuilder routeLocatorBuilder;

    public ApiRouteLocatorImpl(Environment environment, ApplicationProperties applicationProperties, ApiRouteService service, RouteLocatorBuilder routeLocatorBuilder) {
        this.environment = environment;
        this.applicationProperties = applicationProperties;
        this.service = service;
        this.routeLocatorBuilder = routeLocatorBuilder;
    }

    @Override
    public Flux<Route> getRoutes() {
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
        List<ApiRoute> list = service.findAll();
        Flux<Route> routes = null;
//        Flux<Route> routes = service.findAll().stream()
//                .map(apiRoute -> builder.route(String.valueOf(apiRoute.getId()), predicateSpec -> setPredicateSpec(apiRoute, predicateSpec)))
//                .(b -> b.build().getRoutes());

        //adding default routes
        applicationProperties.getRoutes().forEach(new Consumer<ApiRoute>() {
            @Override
            public void accept(ApiRoute apiRoute) {
                apiRoute.setId(Passport.encrypt(apiRoute.getName()));
                Flux.concat(routes, Flux.just(builder.route(String.valueOf(apiRoute.getId()),
                        predicateSpec -> setPredicateSpec(apiRoute, predicateSpec))));
            }
        });
        logger.info(Arrays.toString(new List[]{routes.collectList().block()}));
        return routes;
    }

    private Buildable<Route> setPredicateSpec(ApiRoute apiRoute, PredicateSpec predicateSpec) {
        logger.info(apiRoute.toJson());
        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getContext() + "" + apiRoute.getVersion() + "/**");
        booleanSpec
                .and()
                .method("GET", "POST", "PUT", "PATCH", "DELETE")
                .filters(filter -> filter.rewritePath(apiRoute.getContext() + "" + apiRoute.getVersion() + "/(?<segment>.*)", apiRoute.getEndpoint().getContext() + "" + apiRoute.getEndpoint().getVersion() + "/$\\{segment}")
                                .filter(new GatewayPreFilter()).filter(new GatewayPostFilter())
                        //.filter(new AuthenticationFilter().apply(new AuthenticationFilter.Config()))
                        //.addResponseHeader("Access-Control-Allow-Origin", "http://localhost:3000")
                        //.dedupeResponseHeader("Access-Control-Allow-Credentials", DedupeResponseHeaderGatewayFilterFactory.Strategy.RETAIN_FIRST.toString())
                        //.dedupeResponseHeader("Access-Control-Allow-Origin", DedupeResponseHeaderGatewayFilterFactory.Strategy.RETAIN_FIRST.toString())
                );
        return booleanSpec.uri(apiRoute.getEndpoint().getBaseUri());
    }
}
