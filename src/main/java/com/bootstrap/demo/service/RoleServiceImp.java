package com.bootstrap.demo.service;

import com.bootstrap.demo.model.Role;
import com.bootstrap.demo.dao.RoleDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class RoleServiceImp implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void saveAll(Collection<Role> roles) {
        roleDao.saveAll(roles);
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public void deleteAll(Collection<Role> roles){
        roleDao.deleteAll(roles);
    }

    @Override
    @Transactional
    public Collection<Role> findAllRolesByNameOnDataBase(String[] roles) {
        return roleDao.findAllRolesByNameOnDataBase(roles);
    }
}
