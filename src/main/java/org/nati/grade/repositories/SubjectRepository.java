/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nati.grade.repositories;

import java.util.Collection;

import org.nati.grade.domain.Subject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for <code>Owner</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data.
 * See: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @author Eric Costa Hall
 */
public interface SubjectRepository extends Repository<Subject, Integer> {

    /**
     * Retrieve {@link Subject}s from the data store, returning all subjects
     * @return a Collection of matching {@link Subject}s (or an empty Collection if none
     * found)
     */
    @Query("SELECT subject FROM Subject subject ORDER BY subject.name")
    @Transactional(readOnly = true)
    Collection<Subject> findAll() throws DataAccessException;

    /**
     * Retrieve an {@link Subject} from the data store by id.
     * @param id the id to search for
     * @return the {@link Subject} if found
     */
    // @Query("SELECT subject FROM Owner subject left join fetch subject.pets WHERE subject.id =:id")
    @Transactional(readOnly = true)
    Subject findById(@Param("id") Integer id);

    /**
     * Save an {@link Subject} to the data store, either inserting or updating it.
     * @param subject the {@link Subject} to save
     * @return 
     */
    Subject save(Subject subject);

    /**
     * Delete an {@link Subject} from data store.
     * @param subject the {@link Subject} to delete.
     */
    void delete(Subject subject);
}
