package com.usc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usc.beans.Order;
import com.usc.beans.User;


@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
	List<Order> findAllByUser(User user);

}
