package com.service;

import com.entities.Orders;


public interface OrdersService {

	/*List<Orders> findAllOrders();*/
	
	void create(Orders orders);

	Orders findOrdersByUserId(Integer usersId);
	
	Orders findOrdersByOrderId(Integer orderId);
	
}
