package com.mindtree.bookyourmeal.modules.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.bookyourmeal.modules.core.entity.Image;

public interface ImageRepository extends JpaRepository<Image, String>{

}
