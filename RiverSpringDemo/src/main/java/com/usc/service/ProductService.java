package com.usc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usc.beans.Product;
import com.usc.dao.ProductDao;
import com.usc.http.Response;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	
	//get
	public Product getProduct(int id) {
		return productDao.findById(id).get();
	}
	
	public List<Product> getProducts() {
		return productDao.findAll();
		
	}
	//post
	public Response addProduct(Product product) {
		productDao.save(product);
		return new Response(true);
	}
	//put
	public Response changeProduct(Product product) {
		Product p = productDao.findById(product.getId()).get();
		p.setBrand(product.getImage());
		p.setImage(p.getImage());
		p.setPrice(product.getPrice());
		p.setName(product.getName());
		p.setStock(product.getStock());
		productDao.save(p);
		return new Response(true);
	}
	
	//delete
	public Response deleteProduct(int id) {
		if(productDao.findById(id)!=null) {
			productDao.deleteById(id);
			return new Response(true);
		}else {
			return new Response(false,"product is not found");
		}
	}
	
}
