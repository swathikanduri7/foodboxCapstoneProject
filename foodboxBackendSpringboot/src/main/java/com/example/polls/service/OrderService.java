package com.example.polls.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.polls.model.Order;
import com.example.polls.repository.OrderRepository;
import com.example.polls.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderService.class);


	public void saveOrder(Order order) {
		orderRepository.save(order);		
	}
	
	public void updateOrder(Order order) {
		orderRepository.save(order);		
	}
	
	public void deleteOrder(Order order) {
		orderRepository.delete(order);		
	}

}
