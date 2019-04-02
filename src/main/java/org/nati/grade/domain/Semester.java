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

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple JavaBean domain object representing an user.
 *
 * @author Eric Costa Hall
 */
@Entity
@Table(name = "semesters")
public class Semester extends BaseEntity {

    @Column(name = "number")
    private Integer number;
    
    @Column(name = "total_credits")
    @Min(0)
    private Integer total_credits;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_course")
    private Course course;

    @ManyToMany
    @JoinTable(
    name="semester_subject",
    joinColumns=@JoinColumn(name="id_subject", referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="id_semester", referencedColumnName="id"))
    private List<Subject> subjects;
    
    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the total_credits
     */
    public Integer getTotal_credits() {
        return total_credits;
    }

    /**
     * @param total_credits the total_credits to set
     */
    public void setTotal_credits(Integer total_credits) {
        this.total_credits = total_credits;
    }

    /**
     * @return the course
     */
    @JsonIgnore
    public Course getCourse() {
        return course;
    }
    
    /**
     * @param course the course to set
     */
    @JsonIgnore
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * @return the subjects
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    @JsonIgnore
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
