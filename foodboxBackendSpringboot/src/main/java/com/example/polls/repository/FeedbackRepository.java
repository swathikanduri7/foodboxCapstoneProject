package com.example.polls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.polls.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> 
{
  //  Optional<Role> findByName(RoleName roleName);

}



  