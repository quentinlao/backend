package com.wsspring.controller;

import com.wsspring.model.Role;
import com.wsspring.model.User;
import com.wsspring.model.UserProfile;
import com.wsspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping(value="/{username}")
    public ResponseEntity<UserProfile> getOneById(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ResponseEntity<UserProfile>(HttpStatus.NOT_FOUND);
        }
        Collection<Role> roles = user.getRoles();
        List<String> rolesNames = new ArrayList<>();
        for(Role r : roles) {
            rolesNames.add(r.getName());
        }
        return new ResponseEntity<UserProfile>(new UserProfile(user.getId(),user.getEmail(),rolesNames,  user.getUsername()), HttpStatus.OK);
    }

}
