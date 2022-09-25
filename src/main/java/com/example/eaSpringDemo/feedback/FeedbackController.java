package com.example.eaSpringDemo.feedback;



import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final RestTemplate restTemplate;

    public FeedbackController(FeedbackService feedbackService
            ,RestTemplate restTemplate){
        this.feedbackService=feedbackService;
        this.restTemplate=restTemplate;
    }



    @GetMapping(path = "/{answerId}")
    public ResponseEntity<List<Feedback>> getFeedback(@PathVariable ("answerId") Long answerId){
        List<Feedback> feedbacks = feedbackService.getFeedback(answerId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @PostMapping(path="/{answerId}/{userId}")
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback
                            ,@PathVariable("userId") Long userId
                            ,@PathVariable("answerId") Long answerId){

        Feedback newFeedback = feedbackService.addFeedback(feedback, userId, answerId);
        return new ResponseEntity<>(newFeedback, HttpStatus.OK);
    }

    @DeleteMapping(path = "{feedbackId}")
    public ResponseEntity deleteFeedback(@PathVariable ("feedbackId") Long feedbackId){
        feedbackService.deleteFeedback(feedbackId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(path = "answer/{answerId}")
    public ResponseEntity deleteFeedbackByQuestionId(@PathVariable ("answerId") Long answerId){
        feedbackService.deleteFeedbackByQuestionId(answerId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping(path = "{feedbackId}/{answerId}/{userId}")
    public ResponseEntity<Feedback>  updateFeedback(
            @PathVariable("feedbackId") Long feedbackId
            ,@RequestBody Feedback feedback
            ,@PathVariable("userId") Long userId
            ,@PathVariable("answerId") Long answerId){

        Feedback editedFeedback = feedbackService.updateFeedback(feedback,feedbackId, userId, answerId);
        return new ResponseEntity<>(editedFeedback, HttpStatus.OK);

    }

    @GetMapping(value = "/user")
    public User getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
int id = 1;
        User user=  restTemplate.getForObject("http://localhost:8082/api/v1/student/"+id, User.class);
        return user;
//        return restTemplate.exchange("http://localhost:8082/api/v1/student/", HttpMethod.GET, entity, String.class).getBody();
    }
}
