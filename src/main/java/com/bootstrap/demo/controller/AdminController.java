package com.bootstrap.demo.controller;

import com.bootstrap.demo.model.User;
import com.bootstrap.demo.service.RoleService;
import com.bootstrap.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Principal principal, Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("authorityUser", userService.findUserByEmail(principal.getName()));
        model.addAttribute("users", userList);
        model.addAttribute("userForAdd", new User());
        return "users-table";
    }


    @PostMapping("/users-add")
    public String addUser(@ModelAttribute("userForAdd") User user,
                          @RequestParam(value = "roleForAdd") String[] roles) {

        user.setRoles(roleService.findAllRolesByNameOnDataBase(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/user-update/{id}")
    public String updateUser(@ModelAttribute(value = "user") User user,
                             @RequestParam(value = "editRoles") String[] roles) {

        user.setRoles(roleService.findAllRolesByNameOnDataBase(roles));
        userService.saveAndFlush(user);

        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
