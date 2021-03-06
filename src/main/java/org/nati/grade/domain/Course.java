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
package org.nati.grade.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * Simple JavaBean domain object representing an user.
 *
 * @author Eric Costa Hall
 */
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    
    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "total_credits")
    private Integer total_credits;

    @OneToMany(mappedBy = "course")
	private List<Semester> semesters = new ArrayList<>();

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param total_credits the total_credits to set
     */
    public void setTotal_credits(Integer total_credits) {
        this.total_credits = total_credits;
    }
    
    /**
     * @return the total_credits
     */
    public Integer getTotal_credits() {
        return total_credits;
    }

    /**
     * @return the semesters
     */
    public List<Semester> getSemesters() {
        return semesters;
    }

    /**
     * @param semesters the semesters to set
     */
    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }
}
