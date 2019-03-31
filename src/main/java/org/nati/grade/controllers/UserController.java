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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.nati.grade.domain.User;
import org.nati.grade.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Eric Costa Hall
 */
@Controller
class UserController {

    private final UserRepository users;

    public UserController(UserRepository clinicService) {
        this.users = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/users")
    public String showUserList(Map<String, Object> model) {
        List<User> users = new ArrayList<>();
        users.addAll(this.users.findAll());
        model.put("users", users);
        return "users/userList";
    }

    @GetMapping("/users/new")
    public String initCreationForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "users/createUser";
    }

    @PostMapping("/users/new")
    public String processCreationForm(@Valid User user, BindingResult result) {
        this.users.save(user);
        // return "redirect:/users/" + user.getId();
        return "redirect:/users/";
    }

    @GetMapping("/users/{userId}/edit")
    public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
        User user = this.users.findById(userId);
        model.addAttribute(user);
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/users/{userId}/edit")
    public String processUpdateUserForm(@Valid User user, BindingResult result, @PathVariable("userId") int userId) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            user.setId(userId);
            this.users.save(user);
            return "redirect:/users/{userId}";
        }
    }

    @GetMapping("/users/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("user", new User());
        return "users/findUsers";
    }

    // @GetMapping("/usersprocessFindForm")
    // public String processFindForm(User user, BindingResult result, Map<String, Object> model) {

    //     // // allow parameterless GET request for /users to return all records
    //     // if (user.getEmail() == null) {
    //     //     user.setEmail(""); // empty string signifies broadest possible search
    //     // }

    //     // // find users by email
    //     // // Collection<User> results = this.users.findByEmail(user.getEmail());
    //     // if (results.isEmpty()) {
    //     //     // no users found
    //     //     result.rejectValue("lastName", "notFound", "not found");
    //     //     return "users/findUsers";
    //     // } else if (results.size() == 1) {
    //     //     // 1 user found
    //     //     user = results.iterator().next();
    //     //     return "redirect:/users/" + user.getId();
    //     // } else {
    //     //     // multiple users found
    //     //     model.put("selections", results);
    //     //     return "users/usersList";
    //     // }
    // }

    /**
     * Custom handler for displaying an user.
     *
     * @param userId the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/users/{userId}")
    public ModelAndView showUser(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView("users/userDetails");
        mav.addObject(this.users.findById(userId));
        return mav;
    }

}
