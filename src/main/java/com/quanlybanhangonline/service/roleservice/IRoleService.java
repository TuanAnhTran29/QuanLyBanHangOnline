package com.quanlybanhangonline.service.roleservice;

import com.quanlybanhangonline.model.Role;
import com.quanlybanhangonline.model.RoleName;
import com.quanlybanhangonline.service.IGeneralService;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByName(RoleName roleName);
}
