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

package com.dispatcher.gateway.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class PostResponseFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(PostResponseFilter.class);

    /**
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(req, res);
        if (res instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) res;
            printResponseHeaders(httpResponse);
        }
    }

    /**
     * @param response
     */
    synchronized private void printResponseHeaders(HttpServletResponse response) {
        logger.info("");
        logger.info("response");
        logger.info("header");
        Collection<String> headerNames = response.getHeaderNames();
        if (headerNames != null) {
            Iterator<String> namesIterator = headerNames.iterator();
            while (namesIterator.hasNext()) {
                String name = namesIterator.next();
                Collection<String> values = response.getHeaders(name);
                if (values != null) {
                    Iterator<String> valuesIterator = values.iterator();
                    while (valuesIterator.hasNext()) {
                        String value = valuesIterator.next();
                        logger.info(name + ": " + value);
                    }
                }
            }
        }
    }
}
