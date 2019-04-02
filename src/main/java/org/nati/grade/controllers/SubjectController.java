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

import org.nati.grade.domain.Subject;
import org.nati.grade.services.SubjectService;
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
@RequestMapping(value = "/grade/subjects")
class SubjectController {

    @Autowired
    public SubjectService subjectService;

    @CrossOrigin
    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<Subject>> showSubjectList() {
        List<Subject> subjects = subjectService.getSubjects();
        return ResponseEntity.ok(subjects);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject newSubject = subjectService.createSubject(subject);
        return ResponseEntity.status(HttpStatus.OK).body(newSubject);
	}

    @CrossOrigin
	@ResponseBody
    @GetMapping("/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("subjectId") int subjectId) 
        throws RelationNotFoundException{
        Subject subject = subjectService.findById(subjectId);
        return ResponseEntity.ok(subject);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/{subjectId}/edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> initUpdateSubjectForm(@PathVariable("subjectId") int subjectId, 
        @RequestBody Subject subject) {
        Subject editSubject = subjectService.findById(subjectId);
        editSubject.setName(subject.getName());
        editSubject.setTotal_credits(subject.getTotal_credits());
        subjectService.createSubject(editSubject);
        return ResponseEntity.status(HttpStatus.OK).body(editSubject);
    }

    @RequestMapping(path = "/{subjectId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> processDeleteSubjectForm(@PathVariable("subjectId") int subjectId)
            throws RelationNotFoundException {
        Subject subject = subjectService.findById(subjectId);
        subjectService.delete(subject);
        return ResponseEntity.noContent().build();
    }
}
