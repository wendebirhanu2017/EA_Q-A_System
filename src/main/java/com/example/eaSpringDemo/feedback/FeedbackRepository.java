package com.example.eaSpringDemo.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
  Optional<List<Feedback>> findByAnswerId(Long answerId);

    void deleteByAnswerId(Long answerId);

  boolean existsByAnswerId(Long answerId);
}
