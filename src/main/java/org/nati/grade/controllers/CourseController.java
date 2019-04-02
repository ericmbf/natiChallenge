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
import org.nati.grade.services.CourseService;
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
@RequestMapping(value = "/grade/courses")
class CourseController {

    @Autowired
    public CourseService courseService;

    @CrossOrigin
    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<Course>> showCourseList() {
        List<Course> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }
    
    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course newCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body(newCourse);
    }
    
    @CrossOrigin
	@ResponseBody
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable("courseId") int courseId) 
        throws RelationNotFoundException{
        Course course = courseService.findById(courseId);
        return ResponseEntity.ok(course);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/{courseId}/edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> initUpdateCourseForm(@PathVariable("courseId") int courseId, 
        @RequestBody Course course) {
        Course editCourse = courseService.findById(courseId);
        editCourse.setName(course.getName());
        editCourse.setTotal_credits(course.getTotal_credits());
        courseService.createCourse(editCourse);
        return ResponseEntity.status(HttpStatus.OK).body(editCourse);
    }

    @RequestMapping(path = "/{courseId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> processDeleteCourseForm(@PathVariable("courseId") int courseId)
            throws RelationNotFoundException {
        Course course = courseService.findById(courseId);
        courseService.delete(course);
        return ResponseEntity.noContent().build();
    }
}
