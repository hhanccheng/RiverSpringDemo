package com.usc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usc.beans.OrderProduct;
import com.usc.dao.OrderDao;
import com.usc.dao.OrderProductDao;
import com.usc.dao.ProductDao;
import com.usc.http.Response;

@Service
@Transactional
public class OrderProductService {
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderProductDao orderProductDao;
	
	public OrderProduct getOrderProduct(int id) {
		return orderProductDao.findById(id).get();
	}
	
	public List<OrderProduct> getOrderProducts() {
		return orderProductDao.findAll();
	}
	
	//post
	public Response addOrderProduct(OrderProduct orderProduct) {
		int leftStock = productDao.findById(orderProduct.getProduct().getId()).get().getStock() - orderProduct.getQuantity();
		if(leftStock >= 0) {
			productDao.findById(orderProduct.getProduct().getId()).get().setStock(leftStock);
			productDao.save(productDao.findById(orderProduct.getProduct().getId()).get());
			orderProduct.setProduct(productDao.findById(orderProduct.getProduct().getId()).get());
			orderProductDao.save(orderProduct);
			return new Response(true);
		}else {
			return new Response(false, "Out the stock :" + leftStock);
		}
		
	}
	//put
	public Response changeOrderProduct(OrderProduct orderProduct) {
		int leftStock = productDao.findById(orderProduct.getProduct().getId()).get().getStock() - (orderProduct.getQuantity() - orderProductDao.findById(orderProduct.getId()).get().getQuantity());
		if(leftStock >= 0) {
			productDao.findById(orderProduct.getProduct().getId()).get().setStock(leftStock);
			OrderProduct op = orderProductDao.findById(orderProduct.getId()).get();
			op.setOrder(orderProduct.getOrder());
			productDao.save(productDao.findById(orderProduct.getProduct().getId()).get());
			op.setProduct(orderProduct.getProduct());
			op.setQuantity(orderProduct.getQuantity());
			orderProductDao.save(op);												
			return new Response(true);
		}else {
			return new Response(false, "Out the stock :" + leftStock);
		}
	}
	
	//delete
	public Response deleteOrderProduct(int id) {
		if(orderProductDao.findById(id)!=null) {
			orderProductDao.deleteById(id);
			return new Response(true);
		}else {
			return new Response(false,"orderProduct is not found");
		}
	}
	
	
}
