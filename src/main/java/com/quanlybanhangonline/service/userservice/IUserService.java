package com.quanlybanhangonline.service.userservice;

import com.quanlybanhangonline.model.User;
import com.quanlybanhangonline.service.IGeneralService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
    //user co ton tai trong DB khong
    Optional<User> findByUsername(String username);
    //username co trong DB chua
    Boolean existsByUsername(String username);
    //email co trong DB chua
    Boolean existsByEmail(String email);
    //phone co trong DB chua
    Boolean existsByPhone(String phone);
}
