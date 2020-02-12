package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Role;

public interface RoleDao {
    Role getRoleByName(String name);
}
