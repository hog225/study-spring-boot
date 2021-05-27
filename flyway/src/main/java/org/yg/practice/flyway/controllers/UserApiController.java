package org.yg.practice.flyway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yg.practice.flyway.models.Header;
import org.yg.practice.flyway.models.User;
import org.yg.practice.flyway.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public Header<User> create(@RequestBody User requestUser) {
        User user = userService.create(requestUser);
        if (user != null){
            return Header.OK(user);
        }
        return Header.ERROR();
    }

    @GetMapping("{id}")
    public Header<User> read(@PathVariable(name = "id") Long id) {
        User user = userService.read(id);
        if (user != null){
            return Header.OK(user);
        }
        return Header.ERROR();
    }

    @PutMapping("{id}")
    public Header<User> update(@RequestBody User requestUser) {
        User user = userService.update(requestUser);
        if (user != null){
            return Header.OK(user);
        }
        return Header.ERROR();
    }

    @DeleteMapping("{id}")
    public Header<User> delete(@PathVariable(name = "id") Long id) {
        boolean result = userService.delete(id);
        if (result == true){
            return Header.OK();
        }
        return Header.ERROR();
    }

}
