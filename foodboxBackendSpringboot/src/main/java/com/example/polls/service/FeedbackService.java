package com.example.polls.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.polls.model.Feedback;
import com.example.polls.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;


	private static final Logger logger = LoggerFactory
			.getLogger(FeedbackService.class);


	public void saveFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);		
	}
	
	public void updateFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);		
	}
	
	public void deleteFeedback(Feedback feedback) {
		feedbackRepository.delete(feedback);		
	}


}
