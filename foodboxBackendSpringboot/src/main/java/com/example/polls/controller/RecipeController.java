package com.example.polls.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.model.Recipe;
import com.example.polls.payload.RecipeOutput;
import com.example.polls.payload.RecipeRequest;
import com.example.polls.payload.RecipeResponse;
import com.example.polls.payload.ResponseOutput;
import com.example.polls.repository.RecipeRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.service.RecipeService;
@RestController

public class RecipeController {
	
	@Autowired
    private RecipeRepository recipeRepository;
   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeService recipeService;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    //@PostMapping("/admin/addrecipe")
    @RequestMapping(path = "/admin/addrecipe", 
    		consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            method = { RequestMethod.POST})
    public ResponseEntity<?> saverecipe(@Valid @ModelAttribute RecipeRequest recipeRequest) {    	

        // Creating user's account
    	//
    	recipeService.saveRecipe(recipeRequest);

    	return ResponseEntity.status(HttpStatus.CREATED).body(new RecipeOutput());
    }
    
    
   // @PostMapping("/admin/editrecipewithoutimage")
    @RequestMapping(path = "/admin/editrecipewithoutimage", 
    		//headers = "Accept= application/json, text/plain, */*, Content-type=application/json",
            method = { RequestMethod.GET})
    public ResponseEntity<?> updaterecipe(@RequestParam String id,
    		@RequestParam String recipename,
    		@RequestParam String quantity,
    		@RequestParam String price) {  
    	Recipe recipe1 =  recipeRepository.getById(Long.valueOf(id));
    	Recipe recipe = new Recipe(Long.valueOf(id),recipename,quantity  ,Double.valueOf(price),recipe1.getDishimage());
    	recipe.setCreatedAt(recipe1.getCreatedAt());

        // Creating user's account
    	recipeService.updateRecipe(recipe);

    	return ResponseEntity.status(HttpStatus.CREATED).body(new RecipeOutput());
    }
    
   // @PostMapping("/admin/editrecipewithimage")
    @RequestMapping(path = "/admin/editrecipewithimage", 
    		consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            method = { RequestMethod.POST})
    public ResponseEntity<?> updaterecipe(@Valid @ModelAttribute RecipeRequest recipeRequest) {    	

        // Creating user's account
    		recipeService.updateRecipe(recipeRequest);

    		return ResponseEntity.status(HttpStatus.CREATED).body(new RecipeOutput());
    }
    
    @DeleteMapping("/admin/deleterecipe/{recipeId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable String  recipeId) {    	

        // Creating user's account
    	Recipe recipe =  recipeRepository.getById(Long.valueOf(recipeId));
    	recipeService.deleteRecipe(recipe);

    	return ResponseEntity.ok(new ResponseOutput("All ok"));
    }
    
    @GetMapping("/admin/recipe/{recipeId}")
    public ResponseEntity<?> getrecipe(@PathVariable String recipeId) {    	

        // Creating user's account
    	Recipe recipe =  recipeRepository.getById(Long.valueOf(recipeId));
    	
    	return ResponseEntity.ok(new ResponseOutput(recipe));
    }
    
    @GetMapping("/admin/getallrecipe")
    public ResponseEntity<?> getrecipes() {    	

        // Creating user's account
    	List<Recipe> recipes =  recipeRepository.findAll();
    	List<RecipeResponse> recipesList = new ArrayList<>();
    	for(Recipe recipe:recipes){
    		RecipeResponse recipeResponse=new RecipeResponse();
    		recipeResponse.setId(recipe.getId());
    		recipeResponse.setDishimage(recipe.getDishimage());
    		recipeResponse.setDishname(recipe.getDishname());
    		recipeResponse.setPrice(recipe.getPrice());
    		recipeResponse.setQuantity(recipe.getQuantity());
    		recipesList.add(recipeResponse);
    	}

    	return ResponseEntity.ok(new ResponseOutput(recipesList));
    }
    
    @GetMapping("/getallrecipe")
    public ResponseEntity<?> getallrecipe() {    	

        // Creating user's account
    	List<Recipe> recipes =  recipeRepository.findAll();
    	List<RecipeResponse> recipesList = new ArrayList<>();
    	for(Recipe recipe:recipes){
    		RecipeResponse recipeResponse=new RecipeResponse();
    		recipeResponse.setId(recipe.getId());
    		recipeResponse.setDishimage(recipe.getDishimage());
    		recipeResponse.setDishname(recipe.getDishname());
    		recipeResponse.setPrice(recipe.getPrice());
    		recipeResponse.setQuantity(recipe.getQuantity());
    		recipesList.add(recipeResponse);
    	}

    	return ResponseEntity.ok(new ResponseOutput(recipesList));
    }
}
