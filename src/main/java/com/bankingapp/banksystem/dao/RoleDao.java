package com.bankingapp.banksystem.dao;

import com.bankingapp.banksystem.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
