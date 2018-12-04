package io.ram.openbanking.controller;

import io.ram.openbanking.json.AbstractJsonBuilder;
import io.ram.openbanking.model.User;
import io.ram.openbanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractJsonBuilder {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public @ResponseBody User addNewUser(@RequestBody String userReq) throws IOException {

        User user = super.mapFromJson(userReq, User.class);
        User savedUser = userService.addNewUser(user.getUsername(), user.getPassword());
        return savedUser;
    }

}
