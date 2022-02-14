package com.example.polls.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.polls.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{ 
	
	Optional<Cart> findByWhichuser(String whichuser);

}
