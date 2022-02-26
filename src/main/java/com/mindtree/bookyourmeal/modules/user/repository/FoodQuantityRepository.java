package com.mindtree.bookyourmeal.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.bookyourmeal.modules.user.entity.FoodQuantity;
@Repository
public interface FoodQuantityRepository extends JpaRepository<FoodQuantity, Integer> {

}
