package com.example.polls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.polls.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> 
{
   // Optional<Role> findByName(RoleName roleName);

}

