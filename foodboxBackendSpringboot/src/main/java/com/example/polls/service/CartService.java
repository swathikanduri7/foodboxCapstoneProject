package com.example.polls.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.polls.model.Cart;
import com.example.polls.repository.CartRepository;
import com.example.polls.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CartService.class);


	public void addtocart(Cart cart) {
		LOGGER.info("addtocart");
		cartRepository.save(cart);		
	}


	public void updatecart(Cart cart) {
		LOGGER.info("updatecart");
		cartRepository.save(cart);		
	}


}
