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

package com.dispatcher.pim.controller;

import com.dispatcher.service.exception.DataNotFoundException;
import org.ameba.http.Response;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Response> handleConflict(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(Response.newBuilder()
                .withMessage(ex.getMessage())
                .withHttpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(Response.newBuilder()
                .withMessage(ex.getMessage())
                .withHttpStatus(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .build(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<Object> handleDuplicateKey(Exception ex, WebRequest request) {
        return new ResponseEntity<>(Response.newBuilder()
                .withMessage(ex.getMessage())
                .withHttpStatus(String.valueOf(HttpStatus.CONFLICT.value()))
                .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<Object> handleDuplicateKey(DataNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(Response.newBuilder()
                .withMessage(ex.getMessage())
                .withMessageKey(ex.getMessageKey())
                .withHttpStatus(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .withObj(ex.getData())
                .build(),
                HttpStatus.NOT_FOUND
        );
    }
}
