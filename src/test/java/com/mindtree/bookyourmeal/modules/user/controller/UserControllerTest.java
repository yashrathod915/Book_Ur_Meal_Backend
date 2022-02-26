package com.mindtree.bookyourmeal.modules.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mindtree.bookyourmeal.modules.core.dto.UserDto;
import com.mindtree.bookyourmeal.modules.core.entity.Address;
import com.mindtree.bookyourmeal.modules.core.entity.Role;
import com.mindtree.bookyourmeal.modules.user.dto.FoodsInCartDto;
import com.mindtree.bookyourmeal.modules.user.dto.UserInfoDto;
import com.mindtree.bookyourmeal.modules.user.entity.Cart;
import com.mindtree.bookyourmeal.modules.user.entity.FoodOrder;
import com.mindtree.bookyourmeal.modules.user.entity.FoodQuantity;
import com.mindtree.bookyourmeal.modules.user.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@TestConfiguration
	static class UserServiceImplTestConfiguration {
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
	}

	@Autowired
	MockMvc mockMvc;
	@MockBean
	UserService userService;
	@Autowired
	ModelMapper modelMapper;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void getFoodsFromCartTest() throws Exception
	{
		Mockito.when(userService.getFoodsFromCart(Mockito.anyInt())).thenReturn(Mockito.any(Set.class));
		mockMvc.perform(get("/getfoodsfromcart").param("userid", "1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void addfoodToCart() throws Exception
	{
		Set<FoodsInCartDto> foodsInCartSet=new HashSet<>();
		Mockito.when(userService.addFoodToCart(Mockito.anyInt(), Mockito.anyInt())).thenReturn(foodsInCartSet);
		mockMvc.perform(post("/addfoodtocart").param("userid", "1").param("foodid", "1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void removefoodFromCart() throws Exception
	{
		Set<FoodsInCartDto> foodsInCartSet=new HashSet<>();
		Mockito.when(userService.removeFoodFromCart(Mockito.anyInt(), Mockito.anyInt())).thenReturn(foodsInCartSet);
		mockMvc.perform(post("/removefoodfromcart").param("userid", "1").param("foodid", "1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void placeOrderTest() throws Exception
	{
		Set<FoodsInCartDto> foodsInCartSet=new HashSet<>();
		Mockito.when(userService.placeOrder(Mockito.anyInt(), Mockito.any(Set.class))).thenReturn(1);
		mockMvc.perform(post("/placeorder").param("userid", "1").content(foodsInCartSet.toString()).characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void loginUser() throws Exception {
		String mailId = "abdul@mail.com";
		String password = "admin123";
		Address address = new Address(1, "5", "4th Street", "Basheerabad", "Vaniyambadi", "635751", "TN",
				LocalDateTime.now());
		Role role = new Role(1, "USER", LocalDateTime.now());
		Set<FoodQuantity> foodQuantity = new HashSet<>();
		Cart cart = new Cart(1, null, LocalDateTime.now());
		Set<FoodOrder> pastOrders = new HashSet<FoodOrder>();
		UserDto userDto = new UserDto(1, "admin123", "Abdul", "9876543210", "abdul@mail.com", address, role, pastOrders,
				cart, LocalDateTime.now());
		UserInfoDto userInfoDto = modelMapper.map(userDto, UserInfoDto.class);
		System.err.println(userDto);
		System.err.println(userInfoDto);
		Mockito.when(userService.loginUser(Mockito.anyString(), Mockito.anyString())).thenReturn(userInfoDto);
		mockMvc.perform(get("/login-user").param("mailId", mailId).param("password", password)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		Mockito.verify(userService).loginUser(mailId, password);
	}

	@Test
	public void registerUser() throws Exception {
		String body = "{ \"userName\":\"ADMIN\"," + "\"mobileNumber\":9999999999,"
				+ "	\"mailId\":\"admin@gmail.com\" ," + "\"userPassword\":\"Admin@123\", " + "\"address\":{"
				+ "	\"doorNumber\":\"0\" ," + "	\"streetName\":\"default\"," + "	\"locality\":\"default\" ,"
				+ "	\"city\":\"default\", " + "\"zipcode\":\"111111\", " + "	\"state\":\"default\" " + "	},"
				+ "	\"role\":{	\"roleId\":1," + "	\"roleName\":\"ADMIN\"" + "	}," + "\"pastOrders\":null,"
				+ "\"cart\":null }";

		UserInfoDto userInfoDto = new UserInfoDto();
		Mockito.when(userService.registerUser(Mockito.any(UserDto.class))).thenReturn(userInfoDto);
		mockMvc.perform(post("/register-user").accept(MediaType.APPLICATION_JSON).content(body)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void googleLogin() throws Exception {
		String body = "{ \"userName\":\"ADMIN\"," + "\"mobileNumber\":9999999999,"
				+ "	\"mailId\":\"admin@gmail.com\" ," + "\"userPassword\":\"Admin@123\", " + "\"address\":{"
				+ "	\"doorNumber\":\"0\" ," + "	\"streetName\":\"default\"," + "	\"locality\":\"default\" ,"
				+ "	\"city\":\"default\", " + "\"zipcode\":\"111111\", " + "	\"state\":\"default\" " + "	},"
				+ "	\"role\":{	\"roleId\":1," + "	\"roleName\":\"ADMIN\"" + "	}," + "\"pastOrders\":null,"
				+ "\"cart\":null }";

		UserInfoDto userInfoDto = new UserInfoDto();
		Mockito.when(userService.googleLogin(Mockito.any(UserDto.class))).thenReturn(userInfoDto);
		mockMvc.perform(post("/google-login").accept(MediaType.APPLICATION_JSON).content(body)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
