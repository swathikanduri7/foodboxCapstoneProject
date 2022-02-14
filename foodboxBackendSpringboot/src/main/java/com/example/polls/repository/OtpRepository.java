package com.example.polls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.polls.model.Role;

@Repository
public interface OtpRepository extends JpaRepository<Role, Long> 
{
   // Optional<Role> findByName(RoleName roleName);

}

