package com.ahmedwar.brm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmedwar.brm.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByCode(String code);

}
