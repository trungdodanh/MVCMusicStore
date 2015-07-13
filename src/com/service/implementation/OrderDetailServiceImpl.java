package com.service.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.OrderDetail;
import com.service.OrderDetailService;

@Service("orderdetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void create(OrderDetail orderDetail) {
		em.persist(orderDetail);
	}

}
