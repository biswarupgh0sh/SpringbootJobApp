package com.jobApp.firstJobAppSpringboot.review.implementation;

import com.jobApp.firstJobAppSpringboot.company.Company;
import com.jobApp.firstJobAppSpringboot.company.CompanyService;
import com.jobApp.firstJobAppSpringboot.review.Review;
import com.jobApp.firstJobAppSpringboot.review.ReviewRepository;
import com.jobApp.firstJobAppSpringboot.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }


    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }
}
