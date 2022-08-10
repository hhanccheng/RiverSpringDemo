package com.usc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usc.beans.OrderProduct;

@Repository
public interface OrderProductDao extends JpaRepository<OrderProduct, Integer> {

}
