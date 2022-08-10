package com.usc.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


import com.usc.beans.Order;
import com.usc.beans.OrderProduct;
import com.usc.beans.Product;
import com.usc.dao.OrderDao;
import com.usc.dao.OrderProductDao;
import com.usc.dao.ProductDao;
import com.usc.dao.UserDao;
import com.usc.http.Response;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderProductDao orderProductDao;
	
	@Autowired
	UserDao userDao;
	
	public boolean isAdmin(Collection<? extends GrantedAuthority> profiles) {
		boolean isAdmin = false;
		for(GrantedAuthority profile : profiles) {
			if(profile.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}
		return isAdmin;
	}
	//get
	/**
	 * @param order
	 * @param authentication
	 * @return all the order under authentication
	 */
	public List<Order> getOrders(Authentication authentication){
		if(isAdmin(authentication.getAuthorities())) {
			return orderDao.findAll();
		} else {
			return orderDao.findAllByUser(userDao.findByUsername(authentication.getName()));
		}
	}
	
	public Order getOrder(int id) {
		return orderDao.findById(id).get();
	}
	
	//post
	/**
	 * create a new order
	 * @param order
	 * @param authentication
	 * @return Response
	 */
	public Response addOrder(Order order, Authentication authentication) {
		try {
			List<OrderProduct> purchaseOrderProducts = order.getPurchaseOrderProducts();
			for(OrderProduct orderProduct : purchaseOrderProducts) {
				Product product = (Product)productDao.getOne(orderProduct.getProductid());
				orderProduct.setProduct(product);
				orderProduct.setOrder(order);
			}
			order.setUser(userDao.findByUsername(authentication.getName()));
			orderDao.save(order);
			order.setPurchaseOrderProducts(purchaseOrderProducts);
			return new Response(true);
		}catch (Exception e) {
			return new Response(false,e.getMessage());
		}
	}
	//put
	public Response changeOrder(Order order) {
		Order o = orderDao.findById(order.getId()).get();
		o.setPurchase_date(order.getPurchase_date());
		for(OrderProduct orderProduct : order.getPurchaseOrderProducts()) {
			if(orderProduct.getAmount() > orderProduct.getProduct().getStock()) {
				return new Response(false, "Not enough Products in Stock");
			}
		}
		o.setPurchaseOrderProducts(order.getPurchaseOrderProducts());
		orderDao.save(o);
		return new Response(true);
	}
	//delete
	public Response deleteOrder(int id) {
		if(orderDao.findById(id)!=null) {
			orderDao.deleteById(id);
			return new Response(true);
		}else {
			return new Response(false,"order is not found");
		}
	}
}
