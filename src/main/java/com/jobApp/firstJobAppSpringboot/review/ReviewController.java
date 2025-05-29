package com.jobApp.firstJobAppSpringboot.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }


    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean isReviewAdded = reviewService.addReview(companyId, review);
        if(isReviewAdded) {
            return ResponseEntity.ok("Review added successfully");
        }else{
            return new ResponseEntity<>("Review was not added", HttpStatus.NOT_FOUND);
        }
    }
}
