package com.furnitureApp.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.furnitureApp.dto.newPrododuct;
import com.furnitureApp.model.Product;
import com.furnitureApp.model.Stock;
import com.furnitureApp.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	private static final String STARTING_URI = "http://localhost:8080/api/";
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProductService service;
	
	@InjectMocks
	private ProductController controller;
	
	private newPrododuct newProd = new newPrododuct("chair", "url", 5, 9.99);
	private Product prod = new Product("Chair", "url");
	private Stock stock = new Stock(5, 9.99, prod);
	

	@Test
	@WithMockUser(username="user",roles="USER")
	void testCreateProduct() throws Exception {
		when(service.createProduct(newProd)).thenReturn(prod);
		
		mvc.perform(post(STARTING_URI+"/product/create"))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).createProduct(newProd);
		verifyNoMoreInteractions(service);
	}
	
//	@Test
//	//@WithMockUser(username="user",roles="USER")
//	void testBadCreate() throws Exception {
//		
//		mvc.perform(post(STARTING_URI+"/product/create"))
//		.andDo(print())
//		.contentType(MediaType.APPLICATION_JSON)
//        .content(toJson(newProd)))
//		.andExpect(status().isOk());
//		
//		verify(service, times(1)).createProduct(newProd);
//		verifyNoMoreInteractions(service);
//	}
	
	@Test
	//@WithMockUser(username="user",roles="USER")
	void editProduct() throws Exception {
		when(service.editProduct(prod)).thenReturn(prod);
		
		mvc.perform(post(STARTING_URI+"/product/edit"))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).createProduct(newProd);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	//@WithMockUser(username="user",roles="USER")
	void editStock() throws Exception {
		when(service.editStock(stock)).thenReturn(stock);
		
		mvc.perform(post(STARTING_URI+"/stock/edit"))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).createProduct(newProd);
		verifyNoMoreInteractions(service);
	}
}
