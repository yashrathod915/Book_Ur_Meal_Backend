package com.mindtree.bookyourmeal.modules.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.bookyourmeal.modules.core.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
