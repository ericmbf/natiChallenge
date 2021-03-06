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
import org.nati.grade.domain.User;
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
public interface UserRepository extends Repository<User, Integer> {

    /**
     * Retrieve {@link User}s from the data store, returning all users
     * @return a Collection of matching {@link User}s (or an empty Collection if none
     * found)
     */
    @Query("SELECT user FROM User user ORDER BY user.name")
    @Transactional(readOnly = true)
    Collection<User> findAll() throws DataAccessException;

    /**
     * Retrieve an {@link User} from the data store by id.
     * @param id the id to search for
     * @return the {@link User} if found
     */
    // @Query("SELECT user FROM Owner user left join fetch user.pets WHERE user.id =:id")
    @Transactional(readOnly = true)
    User findById(@Param("id") Integer id);

    /**
     * Save an {@link User} to the data store, either inserting or updating it.
     * @param user the {@link User} to save
     * @return 
     */
    User save(User user);

    /**
     * Delete an {@link User} from data store.
     * @param user the {@link User} to delete.
     */
    void delete(User user);
}
