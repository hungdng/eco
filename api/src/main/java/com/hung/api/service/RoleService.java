/**
 * ****************************************************
 * * Description :
 * * File        : RoleService.java
 * * Author      : hung.tran
 * * Date        : Nov 13, 2020
 * ****************************************************
 **/
package com.hung.api.service;

import com.hung.data.entity.Role;
import com.hung.data.entity.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName roleName);
}
