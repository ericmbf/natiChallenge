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
import java.util.List;

import org.hibernate.sql.Delete;
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
 * @author Ken Krebs
 * @author Eric Costa Hall
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface SubjectRepository extends Repository<Subject, Integer> {

    /**
     * Retrieve {@link Subject}s from the data store by email, returning all users
     * whose last name <i>starts</i> with the given name.
     * @param email Value to search for
     * @return a Collection of matching {@link Subject}s (or an empty Collection if none
     * found)
     */
    @Query("SELECT user FROM Subject user ORDER BY user.name")
    @Transactional(readOnly = true)
    Collection<Subject> findAll() throws DataAccessException;

    /**
     * Retrieve an {@link Subject} from the data store by id.
     * @param id the id to search for
     * @return the {@link Subject} if found
     */
    // @Query("SELECT user FROM Owner user left join fetch user.pets WHERE user.id =:id")
    @Transactional(readOnly = true)
    Subject findById(@Param("id") Integer id);

    /**
     * Save an {@link Subject} to the data store, either inserting or updating it.
     * @param user the {@link Subject} to save
     * @return 
     */
    Subject save(Subject user);

    /**
     * Delete an {@link Subject} from data store.
     * @param user the {@link Subject} to delete.
     */
    void delete(Subject user);
}
