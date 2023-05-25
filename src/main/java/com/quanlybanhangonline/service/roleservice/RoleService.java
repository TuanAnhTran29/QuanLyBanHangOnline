package com.quanlybanhangonline.service.roleservice;

import com.quanlybanhangonline.model.Role;
import com.quanlybanhangonline.model.RoleName;
import com.quanlybanhangonline.repo.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepo roleRepo;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepo.findByName(roleName);
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepo.findById(id);
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void remove(Long id) {
        roleRepo.deleteById(id);
    }
}
