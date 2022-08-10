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
	
	public List<OrderProduct> getOrderProducts() {
		return orderProductDao.findAll();
	}
	
	//post
	public Response addOrderProduct(OrderProduct orderProduct) {
		int leftStock = productDao.getOne(orderProduct.getProductid()).getStock() - orderProduct.getAmount();
		if(leftStock >= 0) {
			productDao.getOne(orderProduct.getProductid()).setStock(leftStock);
			orderProduct.setProduct(productDao.getOne(orderProduct.getProductid()));
			orderProductDao.save(orderProduct);
			productDao.save(productDao.getOne(orderProduct.getProductid()));
			return new Response(true);
		}else {
			return new Response(false, "Out Stock, Stock left:" + leftStock);
		}
		
	}
	//put
	public Response changeOrderProduct(OrderProduct orderProduct) {
		int leftStock = productDao.findById(orderProduct.getProductid()).get().getStock() - (orderProduct.getAmount() - orderProductDao.findById(orderProduct.getId()).get().getAmount());
		if(leftStock >= 0) {
			productDao.findById(orderProduct.getProductid()).get().setStock(leftStock);
			OrderProduct op = orderProductDao.findById(orderProduct.getId()).get();
			op.setOrder(orderProduct.getOrder());
			op.setProduct(orderProduct.getProduct());
			op.setAmount(orderProduct.getAmount());
			orderProductDao.save(op);												
			productDao.save(productDao.findById(orderProduct.getProductid()).get());
			return new Response(true);
		}else {
			return new Response(false, "Out Stock, Stock left:" + leftStock);
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
