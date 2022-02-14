package com.example.polls.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.polls.model.Recipe;
import com.example.polls.payload.RecipeRequest;
import com.example.polls.repository.RecipeRepository;
import com.example.polls.repository.UserRepository;

@Service
public class RecipeService {
	
	private final Path root = Paths.get("uploads");
	  

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RecipeService.class);


	public void saveRecipe(RecipeRequest recipeRequest) {
		LOGGER.info("Inside save recipe");
		try {			
		      Files.createDirectory(root);
		    } catch (IOException e) {
		      //throw new RuntimeException("Could not initialize folder for upload!");
		    }
		 try {
		      Files.copy(recipeRequest.getFile().getInputStream(), this.root.resolve(recipeRequest.getFile().getOriginalFilename()));
		    } catch (Exception e) {
		      //throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		    }
		Recipe recipe = new Recipe(recipeRequest.getDishname(),recipeRequest.getQuantity(),recipeRequest.getPrice(),recipeRequest.getFile().getOriginalFilename());
		recipeRepository.save(recipe);		
	}
	
	public void updateRecipe(RecipeRequest recipeRequest) {
		LOGGER.info("Inside save recipe");
		try {
		      Files.createDirectory(root);
		    } catch (IOException e) {
		      //throw new RuntimeException("Could not initialize folder for upload!");
		    }
		 try {
		      Files.copy(recipeRequest.getFile().getInputStream(), this.root.resolve(recipeRequest.getFile().getOriginalFilename()));
		    } catch (Exception e) {
		      //throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		    }
	    Recipe recipe = new Recipe(recipeRequest.getId(),recipeRequest.getDishname(),recipeRequest.getQuantity(),recipeRequest.getPrice(),recipeRequest.getFile().getOriginalFilename());
		    
		recipeRepository.save(recipe);		
	}
	
	public void updateRecipe(Recipe recipe) {
		LOGGER.info("Inside save recipe");	        
		recipeRepository.save(recipe);		
	}
	
	public void deleteRecipe(Recipe recipe) {
		recipeRepository.delete(recipe);		
	}


}
