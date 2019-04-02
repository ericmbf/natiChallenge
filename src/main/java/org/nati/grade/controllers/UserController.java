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

import org.nati.grade.domain.User;
import org.nati.grade.services.UserService;
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
@RequestMapping(value = "/grade/users")
class UserController {

    @Autowired
    public UserService userService;

    @CrossOrigin
    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<User>> showUserList() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("12345678");
        }
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
	}

    @CrossOrigin
	@ResponseBody
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) 
        throws RelationNotFoundException{
        User user = userService.findById(userId);
        return ResponseEntity.ok(user); // return 200, with json body
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/{userId}/edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> initUpdateUserForm(@PathVariable("userId") int userId, 
        @RequestBody User user) {
        User editUser = userService.findById(userId);
        editUser.setEmail(user.getEmail());
        editUser.setName(user.getName());
        editUser.setPassword(user.getPassword());
        editUser.setType(user.getType());
        userService.createUser(editUser);
        return ResponseEntity.status(HttpStatus.OK).body(editUser);
    }

    @RequestMapping(path = "/{userId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> processDeleteUserForm(@PathVariable("userId") int userId)
            throws RelationNotFoundException {
        User user = userService.findById(userId);
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }
}
