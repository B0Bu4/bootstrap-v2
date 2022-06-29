package com.bootstrap.demo.service;


import com.bootstrap.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;



public interface UserService extends UserDetailsService {
    User findUserByEmail(String name);

    List<User> findAll();

    void save(User user);

    void deleteById(Long id);

    User findUserById(Long id);

    void saveAndFlush(User user);
}
