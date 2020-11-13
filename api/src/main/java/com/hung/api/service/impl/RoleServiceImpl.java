/**
 * ****************************************************
 * * Description :
 * * File        : RoleServiceImpl.java
 * * Author      : hung.tran
 * * Date        : Nov 13, 2020
 * ****************************************************
 **/
package com.hung.api.service.impl;

import com.hung.api.service.RoleService;
import com.hung.data.entity.Role;
import com.hung.data.entity.RoleName;
import com.hung.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
