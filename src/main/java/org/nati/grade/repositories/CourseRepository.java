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
import org.nati.grade.domain.Course;
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
public interface CourseRepository extends Repository<Course, Integer> {

    /**
     * Retrieve {@link Course}s from the data store, returning all courses
     * whose last name <i>starts</i> with the given name.
     * @return a Collection of matching {@link Course}s (or an empty Collection if none
     * found)
     */
    @Query("SELECT course FROM Course course ORDER BY course.name")
    @Transactional(readOnly = true)
    Collection<Course> findAll() throws DataAccessException;

    /**
     * Retrieve an {@link Course} from the data store by id.
     * @param id the id to search for
     * @return the {@link Course} if found
     */
    // @Query("SELECT course FROM Owner course left join fetch course.pets WHERE course.id =:id")
    @Transactional(readOnly = true)
    Course findById(@Param("id") Integer id);

    /**
     * Save an {@link Course} to the data store, either inserting or updating it.
     * @param course the {@link Course} to save
     * @return 
     */
    Course save(Course course);

    /**
     * Delete an {@link Course} from data store.
     * @param course the {@link Course} to delete.
     */
    void delete(Course course);
}
