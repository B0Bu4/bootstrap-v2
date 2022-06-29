package com.bootstrap.demo.service;

import com.bootstrap.demo.model.Role;
import com.bootstrap.demo.model.User;
import com.bootstrap.demo.dao.UserDao;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImp(UserDao userDao, RoleService roleService, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;

    }

    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional
    public void saveAndFlush(User user) {

        if(user.getPassword() == null || user.getPassword().equals("") ||
                user.getPassword().equals(userDao.findUserById(user.getId()).getPassword())){

            user.setPassword(userDao.findUserById(user.getId()).getPassword());

        } else {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.saveAndFlush(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Hibernate.initialize(user.getAuthorities());

        return user;
    }
    @Transactional
    public void addAdmin(){
        try {
            User user1 = userDao.findUserByEmail("admin");
        } catch (NoResultException e) {
            User admin = new User();
            admin.setEmail("admin");
            admin.setPassword(passwordEncoder.encode("admin"));

            Collection<Role> rolesList = new HashSet<>();

            Role roleOne = new Role();
            roleOne.setName("ROLE_ADMIN");
            roleService.save(roleOne);
            rolesList.add(roleOne);

            Role roleTwo = new Role();
            roleTwo.setName("ROLE_USER");
            roleService.save(roleTwo);
            rolesList.add(roleTwo);


            admin.setRoles(rolesList);

            userDao.save(admin);
        }
    }
}