package com.example.eaSpringDemo.feedback;


import jdk.jfr.consumer.RecordedStackTrace;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository){
        this.feedbackRepository=feedbackRepository;

    }
    public List<Feedback> getFeedback(Long answerId) {
        return feedbackRepository.findByAnswerId(answerId).orElseThrow(()->new IllegalStateException(
                "feedback with answer id " + answerId + " dose not exist"));
    }

    public Feedback addFeedback(Feedback feedback, Long userId, Long answerId) {
        feedback.setAnswerId(answerId);
        feedback.setUserId(userId);
        feedback.setCreatedDate(LocalDate.now());
        feedbackRepository.save(feedback);
        return feedback;
    }

    public void deleteFeedback(Long feedbackId) {
        boolean exists = feedbackRepository.existsById(feedbackId);
        if(!exists){
            throw new FeedbackNotFoundException(feedbackId);
        }

        feedbackRepository.deleteById(feedbackId);
    }

    public Feedback updateFeedback(Feedback newFeedback,Long feedbackId, Long userId, Long answerId) {
        return feedbackRepository.findById(feedbackId).map(feedback -> {
            feedback.setComment(newFeedback.getComment());
            feedback.setUserId(userId);
            feedback.setAnswerId(answerId);
            return feedbackRepository.save(feedback);
        }).orElseGet(()->{
            newFeedback.setAnswerId(answerId);
            newFeedback.setUserId(userId);
            newFeedback.setCreatedDate(LocalDate.now());
            return feedbackRepository.save(newFeedback);
        });

    }

    public void deleteFeedbackByQuestionId(Long answerId) {
      List<Feedback> feedbacks = feedbackRepository.findByAnswerId(answerId).orElseThrow(()->new IllegalStateException(
              "feedback with answer id " + answerId + " dose not exist"));
      if(!feedbacks.isEmpty()){
          for(Feedback f:feedbacks){
              feedbackRepository.deleteById(f.getId());
          }
      }

    }
}
