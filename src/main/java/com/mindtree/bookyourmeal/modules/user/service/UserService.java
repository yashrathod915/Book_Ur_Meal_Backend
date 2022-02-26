package com.mindtree.bookyourmeal.modules.user.service;

import java.util.Set;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.dto.UserDto;
import com.mindtree.bookyourmeal.modules.user.dto.EditUserDto;
import com.mindtree.bookyourmeal.modules.user.dto.FoodsInCartDto;
import com.mindtree.bookyourmeal.modules.user.exception.UserException;
import com.mindtree.bookyourmeal.modules.user.dto.UserInfoDto;
import com.mindtree.bookyourmeal.modules.user.exception.UserException;

public interface UserService {
	public Set<FoodsInCartDto> addFoodToCart(int userId, int foodId);

	public Set<FoodsInCartDto> removeFoodFromCart(int userId, int foodId);

	public Set<FoodsInCartDto> getFoodsFromCart(int userId);

	public UserInfoDto registerUser(UserDto user) throws ServiceException;

	public UserInfoDto loginUser(String mailId, String password) throws UserException;

	public UserInfoDto googleLogin(UserDto userDto) throws UserException;

	public EditUserDto editUserProfile(EditUserDto user) throws UserException;
	
//	public UserDto editUserProfile(UserDto userDto) throws UserException;
	
	public EditUserDto getEditUserProfile(int userId);
	
	public Integer placeOrder(int userId, Set<FoodsInCartDto> foodsOrdered);
}
