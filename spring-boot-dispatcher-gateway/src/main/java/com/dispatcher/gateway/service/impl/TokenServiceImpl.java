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

import com.dispatcher.gateway.model.Token;
import com.dispatcher.gateway.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

//    private final KeycloakProperties properties;

    public TokenServiceImpl() {
    }

    @Override
    public Token generate(Token token) {
//        TokenManager manager = new TokenManager.Builder()
//                .serverUrl(properties.getServerUrl())
//                .realm(properties.getRealm())
//                .clientId(token.getClientId())
//                .clientSecret(token.getClientSecret())
//                .username(token.getUsername())
//                .password(token.getPassword())
//                .build();
//        AccessTokenResponse response = manager.getAccessToken();
//        token.setAccessToken(response.getToken());
//        token.setTokenType(response.getTokenType());
//        return token;
        return null;
    }

    @Override
    public Token generate(String clientId, String clientSecret, String username, String password) {
//        Token token = new Token();
//        TokenManager manager = new TokenManager.Builder()
//                .serverUrl(properties.getServerUrl())
//                .realm(properties.getRealm())
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .username(username)
//                .password(password)
//                .build();
//        AccessTokenResponse response = manager.getAccessToken();
//        token.setAccessToken(response.getToken());
//        token.setTokenType(response.getTokenType());
//        return token;
        return null;
    }

}
