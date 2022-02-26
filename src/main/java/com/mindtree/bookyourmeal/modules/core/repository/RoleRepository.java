package com.mindtree.bookyourmeal.modules.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.bookyourmeal.modules.core.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
