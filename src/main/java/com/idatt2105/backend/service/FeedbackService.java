package com.idatt2105.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.FeedbackDTO;
import com.idatt2105.backend.model.Feedback;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.FeedbackRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;

@Service
public class FeedbackService {

  private final FeedbackRepository feedbackRepository;
  private final UserRepository userRepository;

  /**
   * Constructs a new FeedbackService with the specified repositories.
   *
   * @param feedbackRepository The repository for managing feedback entities.
   * @param userRepository The repository for managing user entities.
   */
  @Autowired
  public FeedbackService(FeedbackRepository feedbackRepository, UserRepository userRepository) {
    this.feedbackRepository = feedbackRepository;
    this.userRepository = userRepository;
  }

  /**
   * Creates a new feedback based on the provided FeedbackDTO.
   *
   * @param feedbackDTO The DTO containing the feedback data.
   * @return The created FeedbackDTO.
   */
  public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
    if (feedbackDTO == null) {
      throw new IllegalArgumentException("FeedbackDTO cannot be null");
    }

    User user =
        userRepository
            .findById(feedbackDTO.getUserId())
            .orElseThrow(() -> new InvalidIdException("User not found"));
    Feedback feedback = feedbackDTO.convertToEntity();
    feedback.setUser(user);

    return new FeedbackDTO(feedbackRepository.save(feedback));
  }

  /**
   * Updates an existing feedback with the provided feedbackId and FeedbackDTO.
   *
   * @param feedbackId The ID of the feedback to update.
   * @param feedbackDTO The DTO containing the updated feedback data.
   */
  public void updateFeedback(Long feedbackId, FeedbackDTO feedbackDTO) {
    if (feedbackId == null) {
      throw new IllegalArgumentException("FeedbackDTO cannot be null");
    }
    if (feedbackDTO == null) {
      throw new IllegalArgumentException("FeedbackDTO cannot be null");
    }

    Feedback existingFeedback =
        feedbackRepository
            .findById(feedbackId)
            .orElseThrow(() -> new InvalidIdException("Feedback not found"));
    existingFeedback.setFirstName(feedbackDTO.getFirstName());
    existingFeedback.setLastName(feedbackDTO.getLastName());
    existingFeedback.setEmail(feedbackDTO.getEmail());
    existingFeedback.setFeedbackType(feedbackDTO.getFeedbackType());
    existingFeedback.setContent(feedbackDTO.getContent());
    feedbackRepository.save(existingFeedback);
  }

  /**
   * Deletes the feedback with the provided feedbackId.
   *
   * @param feedbackId The ID of the feedback to delete.
   */
  public void deleteFeedback(Long feedbackId) {
    if (feedbackId == null) {
      throw new IllegalArgumentException("FeedbackDTO cannot be null");
    }
    feedbackRepository.deleteById(feedbackId);
  }

  /**
   * Retrieves all feedback entities and converts them to a list of FeedbackDTOs.
   *
   * @return A list of FeedbackDTOs representing all feedback entities.
   */
  public List<FeedbackDTO> getAllFeedback() {
    List<Feedback> feedbackList = feedbackRepository.findAll();
    return feedbackList.stream().map(FeedbackDTO::new).toList();
  }

  /**
   * Retrieves all feedback entities associated with the specified userId and converts them to a
   * list of FeedbackDTOs.
   *
   * @param userId The ID of the user to retrieve feedback for.
   * @return A list of FeedbackDTOs representing the feedback entities associated with the specified
   *     userId.
   */
  public List<FeedbackDTO> getFeedbackByUserId(Long userId) {
    if (userId == null) {
      throw new IllegalArgumentException("User ID cannot be null");
    }

    List<Feedback> feedbackList = feedbackRepository.findByUserId(userId);
    return feedbackList.stream().map(FeedbackDTO::new).toList();
  }
}
