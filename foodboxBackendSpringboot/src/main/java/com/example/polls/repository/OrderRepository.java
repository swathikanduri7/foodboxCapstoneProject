package com.example.polls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.polls.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> 
{
  //  Optional<Role> findByName(RoleName roleName);

}
