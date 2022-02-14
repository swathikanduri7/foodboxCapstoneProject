package com.example.polls.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.model.Order;
import com.example.polls.payload.OrderRequest;
import com.example.polls.payload.ResponseOutput;
import com.example.polls.repository.OrderRepository;
import com.example.polls.service.OrderService;

@RestController
// @RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	@PostMapping("/orders")
	public ResponseEntity<?> saveOrder(
			@Valid @RequestBody OrderRequest orderRequest) {

		// Creating user's account
		Order order = new Order();
		orderService.saveOrder(order);

		return ResponseEntity.ok(new ResponseOutput("All ok"));
	}

	@PutMapping("/orders")
	public ResponseEntity<?> updateOrder(
			@Valid @RequestBody OrderRequest orderRequest) {

		// Creating user's account
		Order order = new Order();
		orderService.updateOrder(order);

		return ResponseEntity.ok(new ResponseOutput("All ok"));
	}

	@DeleteMapping("/admin/deleteorder/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable String orderId) {

		// Creating user's account
		Order order = orderRepository.getById(Long.getLong(orderId));
		orderService.deleteOrder(order);

		return ResponseEntity.ok(new ResponseOutput("All ok"));
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<?> getOrder(@PathVariable String orderId) {

		// Creating user's account
		Order order = orderRepository.getById(Long.getLong(orderId));

		return ResponseEntity.ok(new ResponseOutput(order));
	}

	@GetMapping("/admin/getallorder")
	public ResponseEntity<?> getOrders() {

		// Creating user's account
		List<Order> orders = orderRepository.findAll();

		return ResponseEntity.ok(new ResponseOutput(orders));
	}

}
