package com.mindtree.bookyourmeal.modules.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.bookyourmeal.modules.core.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
      @Query(value="select count(user_id) from user",nativeQuery=true)
       int getTotalUser();

	Optional<User> findByMailId(String mailId);

	Optional<User> findByMobileNumber(String mobileNumber);
	
}
