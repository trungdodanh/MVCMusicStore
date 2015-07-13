package com.service.implementation;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Orders;
import com.service.OrdersService;

@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

	protected static Logger logger = Logger.getLogger("DAO");
	
	// Injected database connection
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * create an orders object
	 * 
	 * @param orders
	 */
	@Transactional
	public void create(Orders orders) {
		em.persist(orders);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Orders> findAllOrders() {
		return em.createNamedQuery("Orders.findAll").getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public Orders findOrdersByOrderId(Integer orderId) {
		List<Orders> ordersList = null;
		Query q = em.createNamedQuery("Orders.findByOrderId");
		q.setParameter("orderId", orderId);
		if (!q.getResultList().isEmpty()) {
			ordersList = q.getResultList();
			return ordersList.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Find the order(Shipping information) which belong to this user
	 * @param usersId
	 * @return
	 */
	public Orders findOrdersByUserId(Integer usersId) {
		List<Orders> ordersList = findAllOrders();
		Orders ordersReturn = null;
		for (Orders orders : ordersList) 
		{
			if (orders.getUsersId().getUsersId() == usersId) 
			{
				ordersReturn = orders;
			}
		}
		
		return ordersReturn;
	}

	
	
}
