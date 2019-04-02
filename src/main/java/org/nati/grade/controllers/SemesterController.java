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
package org.nati.grade.controllers;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.nati.grade.domain.Course;
import org.nati.grade.domain.Semester;
import org.nati.grade.services.CourseService;
import org.nati.grade.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Eric Costa Hall
 */
@Controller
@RequestMapping(value = "/grade/")
class SemesterController {

    @Autowired
    public SemesterService semesterService;

    @Autowired
    public CourseService courseService;

    @CrossOrigin
    @ResponseBody
    @GetMapping("/{courseId}/semesters")
    public ResponseEntity<List<Semester>> showSemesterList(
        @PathVariable Integer courseId) {
        Course course = courseService.findById(courseId);
        List<Semester> semesters = semesterService.findByCourse(course);
        return ResponseEntity.ok(semesters);
    }

    @GetMapping("/semesters/{id}")
    public ResponseEntity<Semester> showSemester(
        @PathVariable("id") Integer id) {
        Semester semester = semesterService.findById(id);
        return ResponseEntity.ok(semester);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/{courseId}/semesters/new", 
        method = RequestMethod.POST, 
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Semester> createSemester(
        @PathVariable Integer courseId, @RequestBody Semester semester) {
        Course course = courseService.findById(courseId);
        Semester newSemester = semester;
        newSemester.setCourse(course);
        newSemester = semesterService.createSemester(newSemester);
        return ResponseEntity.status(HttpStatus.OK).body(newSemester);
	}

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/{semesterId}/edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Semester> initUpdateSemesterForm(@PathVariable("semesterId") int semesterId, 
        @RequestBody Semester semester) {
        Semester editSemester = semesterService.findById(semesterId);
        editSemester.setNumber(semester.getNumber());
        editSemester.setTotal_credits(semester.getTotal_credits());
        semesterService.createSemester(editSemester);
        return ResponseEntity.status(HttpStatus.OK).body(editSemester);
    }

    @RequestMapping(path = "/{semesterId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> processDeleteSemesterForm(@PathVariable("semesterId") int semesterId)
            throws RelationNotFoundException {
        Semester semester = semesterService.findById(semesterId);
        semesterService.delete(semester);
        return ResponseEntity.noContent().build();
    }
}
