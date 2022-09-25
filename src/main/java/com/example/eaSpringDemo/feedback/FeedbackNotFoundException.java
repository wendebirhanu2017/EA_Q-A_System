package com.example.eaSpringDemo.feedback;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException(Long feedbackId) {
        super("Could not find Feedback " + feedbackId);
    }
}
