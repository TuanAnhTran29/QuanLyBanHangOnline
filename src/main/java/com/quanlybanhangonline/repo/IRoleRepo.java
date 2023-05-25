package com.quanlybanhangonline.repo;

import com.quanlybanhangonline.model.Role;
import com.quanlybanhangonline.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
