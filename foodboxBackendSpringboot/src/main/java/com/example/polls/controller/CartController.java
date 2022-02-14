package com.example.polls.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.model.Cart;
import com.example.polls.model.User;
import com.example.polls.payload.CartRequest;
import com.example.polls.payload.CartResponse;
import com.example.polls.payload.ResponseOutput;
import com.example.polls.repository.CartRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
//@RequestMapping("/api/polls")
public class CartController {

    @Autowired
    private CartRepository cartRepository;
   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    //@GetMapping("/getcartitem")
    
    
    @PostMapping("/addtocart")
    public ResponseEntity<?> addtocart(@CurrentUser UserPrincipal currentUser,@Valid @RequestBody CartRequest cartRequest) {    	

    	Cart cart = new Cart();
    	
    	Optional<Cart> cartItem = cartRepository.findByWhichuser(currentUser.getUsername());
		if(cartItem.isPresent()){
			 cart = cartItem.get();			
		}
		double total = cart.getTotal();
		total = total+ (cartRequest.getPrice() * cartRequest.getQty());
    	cart.setTotal(total);
    	cart.setCreatedAt(Instant.now());
    	cart.setWhichuser(currentUser.getUsername());
    	
    	List<CartRequest> recipe = new ArrayList<>();
    	recipe.add(cartRequest);
    	
    	 ObjectMapper mapper = new ObjectMapper();
    	 try {
			String recipeJson = mapper.writeValueAsString(recipe);
			cart.setRecipeItems(recipeJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	cartService.addtocart(cart);

    	return ResponseEntity.ok(new ResponseOutput("All ok"));
    }
    
    @PutMapping("/updatecart")
    public ResponseEntity<?> updateFeedback(@CurrentUser UserPrincipal currentUser,@Valid @RequestBody CartRequest cartRequest) {    	
    	Cart cart = new Cart();
    	cart.setId(cartRequest.getId());
    	double total = cart.getTotal();
		total = total+ (cartRequest.getPrice() * cartRequest.getQty());
    	cart.setTotal(total);
    	cart.setCreatedAt(Instant.now());
    	cart.setWhichuser(currentUser.getUsername());
    	
    	List<CartRequest> recipe = new ArrayList<>();
    	recipe.add(cartRequest);
    	
    	cart.setRecipeItems(recipe.toString());
    	
    	cartService.updatecart(cart);
    	
    	return ResponseEntity.ok(new ResponseOutput("All ok"));
    }
    
	@GetMapping("/getcartitem")
	public ResponseEntity<?> getcartitem(@CurrentUser UserPrincipal currentUser) {
		CartResponse cartResponse =new CartResponse();		
		Optional<Cart> cartItem = cartRepository.findByWhichuser(currentUser.getUsername());
		if(cartItem.isPresent()){
			Cart cart = cartItem.get();
			cartResponse.setId(cart.getId());
			String recipeItems = cart.getRecipeItems();
			Set<CartRequest> recipe = new HashSet<>();
			 ObjectMapper mapper = new ObjectMapper();
	    	 try {
	    		 List<CartRequest> participantJsonList = mapper.readValue(recipeItems, new TypeReference<List<CartRequest>>(){});
	    		 recipe= new HashSet<CartRequest>(participantJsonList);
	    		 
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			cartResponse.setRecipe(recipe);
			cartResponse.setTotal(cart.getTotal());
			cartResponse.setWhichuser(cart.getWhichuser());
		}
		return ResponseEntity.ok(new ResponseOutput(cartResponse));
	}
	
	@GetMapping("/emptycheck")
	public ResponseEntity<?> emptycheck(@CurrentUser UserPrincipal currentUser) {
		CartResponse cartResponse =new CartResponse();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String whichUser = "";
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			User user = userRepository.findByUsernameOrEmail(
					currentUser.getUsername(), currentUser.getEmail())
					.orElseThrow(
							() -> new UsernameNotFoundException(
									"User not found with username or email : "
											+ currentUserName));
			whichUser = user.getUsername();
		}
		Optional<Cart> cartItem = cartRepository.findByWhichuser(whichUser);
		if(cartItem.isPresent()){
			return ResponseEntity.ok(new ResponseOutput("not empty cart"));
		}
		return ResponseEntity.ok(new ResponseOutput(cartResponse));
	}
}
