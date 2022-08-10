package com.RiverSpringDemo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.usc.beans.Product;
import com.usc.controller.ProductController;
import com.usc.dao.ProductDao;
import com.usc.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {ProductController.class})
class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	ProductDao productDao;
	@MockBean
	ProductService productService;
	
	@Test
	void test() throws Exception{
		List<Product> products = new ArrayList<>();
		products.add(new Product("testname", "testbrand", 3, 3,""));
		Mockito.when(productDao.findAll()).thenReturn(products);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
	}

}
