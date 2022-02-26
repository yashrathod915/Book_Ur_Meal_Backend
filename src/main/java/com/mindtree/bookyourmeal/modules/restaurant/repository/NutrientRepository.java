package com.mindtree.bookyourmeal.modules.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.bookyourmeal.modules.restaurant.entity.Nutrient;
@Repository
public interface NutrientRepository extends JpaRepository<Nutrient, Integer>{

}
