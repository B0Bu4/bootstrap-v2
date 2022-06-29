package com.bootstrap.demo.controller;

import com.bootstrap.demo.model.User;
import com.bootstrap.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String snowUserList(Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user",user);
        return "user";
    }
}
