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
package com.dispatcher.common.base;

import com.dispatcher.common.base.Auditable;
import lombok.Data;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@Data
public class BaseEntity implements Auditable {

    /** Primary key, assigned by the database or persistence strategy, shouldn't be used nor exposed in public API. */
    @Id
    private String id;

    /** Timestamp when the database record was inserted. */
    @CreatedDate
    private LocalDateTime createDt;

    /** Username who created the entity. */
    @CreatedBy
    private String createdBy;

    /** Timestamp when the database record was updated the last time. */
    @LastModifiedDate
    private LocalDateTime lastModifiedDt;

    /** Username who modified the entity. */
    @LastModifiedBy
    private String lastModifiedBy;

    protected BaseEntity() {
    }

    /**
     * Get the primary key value.
     *
     * @return The primary key, might be {@literal null} for transient entities.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the date when the entity was persisted.
     *
     * @return creation date
     */
    public LocalDateTime getCreateDt() {
        return createDt;
    }

    /**
     * Set the date when the entity was persisted.
     *
     * @param createDt Creation date - not {@literal null}
     * @throws IllegalArgumentException if the given {@code createDt} is {@literal null}
     */
    protected void setCreateDt(LocalDateTime createDt) {
        if (createDt == null) {
            throw new IllegalArgumentException("CreateDt must not be null");
        }
        this.createDt = createDt;
    }

    /**
     * Get the user who created this entity.
     *
     * @return The username
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set who has created the entity.
     *
     * @param createdBy The username
     */
    protected void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the date when the entity was modified.
     *
     * @return last modified date
     */
    public LocalDateTime getLastModifiedDt() {
        return lastModifiedDt;
    }

    /**
     * Set the date when the entity was modified.
     *
     * @param lastModifiedDt When the entity was modified the last time - not {@literal null}
     * @throws IllegalArgumentException if the given {@code createDt} is {@literal null}
     */
    protected void setLastModifiedDt(LocalDateTime lastModifiedDt) {
        if (lastModifiedDt == null) {
            throw new IllegalArgumentException("LastModifiedDt must not be null");
        }
        this.lastModifiedDt = lastModifiedDt;
    }

    /**
     * Get the user who modified this entity the last time.
     *
     * @return The users name
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Set who created the entity.
     *
     * @param lastModifiedBy The username
     */
    protected void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Long generateRandomNumber() {
        return System.currentTimeMillis() + new Random().nextInt();
    }
}
