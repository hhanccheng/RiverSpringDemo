package com.usc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usc.beans.Product;
import com.usc.http.Response;
import com.usc.service.ProductService;

@RestController()
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductService productService;
	
	//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable int id) {
		return productService.getProduct(id);
	}
	
	//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	@GetMapping
	public List<Product> getProducts(){
		return productService.getProducts();
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping
	public Response addProduct(@RequestBody Product product){
		return productService.addProduct(product);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping
	public Response changeProduct(@RequestBody Product product) {
		return productService.changeProduct(product);
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public Response deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}
	
}
