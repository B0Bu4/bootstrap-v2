package com.bootstrap.demo.dao;

import com.bootstrap.demo.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface RoleDao {

    void saveAll(Collection<Role> roles);

    void save(Role role);

    void deleteAll(Collection<Role> roles);

    Collection<Role> findAllRolesByNameOnDataBase(String[] roles);
}
