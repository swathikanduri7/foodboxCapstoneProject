package com.example.polls.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.model.Feedback;
import com.example.polls.payload.FeedbackRequest;
import com.example.polls.payload.FeedbackResponse;
import com.example.polls.payload.ResponseOutput;
import com.example.polls.repository.FeedbackRepository;
import com.example.polls.service.FeedbackService;


@RestController
//@RequestMapping("/api")
public class FeedbackController {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
    @Autowired
    private FeedbackService feedbackService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    
    @PostMapping("/feedback")
    public ResponseEntity<?> saveFeedback(@Valid @RequestBody FeedbackRequest feedbackRequest) {    	

        // Creating user's account
    	Feedback feedback = new Feedback(feedbackRequest.getEmail(),feedbackRequest.getName(),feedbackRequest.getMsg(),feedbackRequest.getWhichuser());
    	feedbackService.saveFeedback(feedback);

    	return ResponseEntity.ok(new ResponseOutput("All ok"));
    }
    
    @PutMapping("/feedback")
    public ResponseEntity<?> updateFeedback(@Valid @RequestBody FeedbackRequest feedbackRequest) {    	

        // Creating user's account
    	Feedback feedback = new Feedback(feedbackRequest.getId(),feedbackRequest.getEmail(),feedbackRequest.getName(),feedbackRequest.getMsg(),feedbackRequest.getWhichuser());
    	feedbackService.updateFeedback(feedback);

    	return ResponseEntity.ok(new ResponseOutput("All ok"));
    }
    
    @DeleteMapping("/admin/deletefeedback/{feedbackId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable String feedbackId) {    	

        // Creating user's account
    	Feedback feedback =  feedbackRepository.getById(Long.valueOf(feedbackId));
    	feedbackService.deleteFeedback(feedback);

    	return ResponseEntity.ok(new ResponseOutput("All ok"));
    }
    
    @GetMapping("/feedback/{feedbackId}")
    public ResponseEntity<?> getFeedback(@PathVariable String feedbackId) {    	

        // Creating user's account
    	Feedback feedback =  feedbackRepository.getById(Long.valueOf(feedbackId));
    	
    	return ResponseEntity.ok(new ResponseOutput(feedback));
    }
    
    @GetMapping("/admin/getallfeedbback")
    public ResponseEntity<?> getFeedbacks() {    	

        // Creating user's account
    	List<Feedback> feedbacks =  feedbackRepository.findAll();
    	
    	List<FeedbackResponse>feedbacksList = new ArrayList<>();
    	for(Feedback feedback:feedbacks){
    		FeedbackResponse feedbackResponse=new FeedbackResponse();
    		feedbackResponse.setId(feedback.getId());
    		feedbackResponse.setEmail(feedback.getEmail());
    		feedbackResponse.setMsg(feedback.getMsg());
    		feedbackResponse.setName(feedback.getName());
    		feedbackResponse.setWhichuser(feedback.getWhichuser());
    		feedbacksList.add(feedbackResponse);
    	}
     	

    	return ResponseEntity.ok(new ResponseOutput(feedbacksList));
    }
}


