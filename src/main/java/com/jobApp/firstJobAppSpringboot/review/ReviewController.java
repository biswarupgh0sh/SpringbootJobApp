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


    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        return ResponseEntity.ok(reviewService.getReview(companyId, reviewId));
    }


    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(isReviewUpdated){
            return ResponseEntity.ok("Review updated successfully");
        }else{
            return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isReviewDeleted){
            return ResponseEntity.ok("Review is successfully deleted");
        }else{
            return new ResponseEntity<>("Review is not found", HttpStatus.NOT_FOUND);
        }
    }
}
