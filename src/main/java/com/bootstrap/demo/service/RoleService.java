package com.bootstrap.demo.service;


import com.bootstrap.demo.model.Role;

import java.util.Collection;

public interface RoleService {
    void saveAll(Collection<Role> roles);

    void save(Role role);

    void deleteAll(Collection<Role> roles);

    Collection<Role> findAllRolesByNameOnDataBase(String[] roles);
}
