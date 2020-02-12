package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.Role;
import com.xingzhi.apartment.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
/* SCOPE_SINGLETON is default scope, it can be omitted */
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoleServiceImpl implements RoleService {
    //@Autowired
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
