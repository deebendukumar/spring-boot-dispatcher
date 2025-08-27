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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class PreRequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(PreRequestFilter.class);

    /**
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        printRequestHeaders(request);
        chain.doFilter(req, res);
    }

    /**
     *
     * @param request
     */
    synchronized public void printRequestHeaders(HttpServletRequest request) {
        logger.info("");
        logger.info("request");
        logger.info("header information");
        if (request.getHeaderNames() != null) {
            Enumeration names = request.getHeaderNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                Enumeration values = request.getHeaders(name);
                if (values != null) {
                    while (values.hasMoreElements()) {
                        String value = (String) values.nextElement();
                        logger.info(name + " : " + value);
                    }
                }
            }
        }
        logger.info("method: " + request.getMethod());
        logger.info("uri: " + request.getRequestURI());
        logger.info("path: " + request.getPathInfo());
        logger.info("context path: " + request.getContextPath());
        logger.info("auth type: " + request.getAuthType());
        logger.info("session id: " + request.getRequestedSessionId());
        logger.info("content type: " + request.getContentType());
        logger.info("protocol: " + request.getProtocol());
        logger.info("remote address: " + request.getRemoteAddr());
        logger.info("remote host: " + request.getRemoteHost());
        logger.info("scheme: " + request.getScheme());
    }
}
