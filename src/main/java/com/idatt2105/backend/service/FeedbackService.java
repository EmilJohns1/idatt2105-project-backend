package com.idatt2105.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.FeedbackDTO;
import com.idatt2105.backend.model.Feedback;
import com.idatt2105.backend.repository.FeedbackRepository;
import com.idatt2105.backend.repository.UserRepository;

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
    Feedback feedback = convertToEntity(feedbackDTO);
    Feedback savedFeedback = feedbackRepository.save(feedback);
    return convertToDTO(savedFeedback);
  }

  /**
   * Updates an existing feedback with the provided feedbackId and FeedbackDTO.
   *
   * @param feedbackId The ID of the feedback to update.
   * @param feedbackDTO The DTO containing the updated feedback data.
   */
  public void updateFeedback(Long feedbackId, FeedbackDTO feedbackDTO) {
    Feedback existingFeedback =
        feedbackRepository
            .findById(feedbackId)
            .orElseThrow(() -> new IllegalArgumentException("Feedback not found"));
    Feedback updatedFeedback = convertToEntity(feedbackDTO);
    existingFeedback.setFirstName(updatedFeedback.getFirstName());
    existingFeedback.setLastName(updatedFeedback.getLastName());
    existingFeedback.setEmail(updatedFeedback.getEmail());
    existingFeedback.setFeedbackType(updatedFeedback.getFeedbackType());
    existingFeedback.setContent(updatedFeedback.getContent());
    feedbackRepository.save(existingFeedback);
  }

  /**
   * Deletes the feedback with the provided feedbackId.
   *
   * @param feedbackId The ID of the feedback to delete.
   */
  public void deleteFeedback(Long feedbackId) {
    feedbackRepository.deleteById(feedbackId);
  }

  /**
   * Retrieves all feedback entities and converts them to a list of FeedbackDTOs.
   *
   * @return A list of FeedbackDTOs representing all feedback entities.
   */
  public List<FeedbackDTO> getAllFeedback() {
    List<Feedback> feedbackList = feedbackRepository.findAll();
    return feedbackList.stream().map(this::convertToDTO).collect(Collectors.toList());
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
    List<Feedback> feedbackList = feedbackRepository.findByUserId(userId);
    return feedbackList.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  /**
   * Converts a Feedback entity to a FeedbackDTO.
   *
   * @param feedback The Feedback entity to convert.
   * @return The converted FeedbackDTO.
   */
  private FeedbackDTO convertToDTO(Feedback feedback) {
    FeedbackDTO dto = new FeedbackDTO();
    dto.setFirstName(feedback.getFirstName());
    dto.setLastName(feedback.getLastName());
    dto.setEmail(feedback.getEmail());
    dto.setFeedbackType(feedback.getFeedbackType());
    dto.setContent(feedback.getContent());
    if (feedback.getUser() != null) {
      dto.setUserId(feedback.getUser().getId());
    }
    return dto;
  }

  /**
   * Converts a FeedbackDTO to a Feedback entity.
   *
   * @param dto The FeedbackDTO to convert.
   * @return The converted Feedback entity.
   */
  private Feedback convertToEntity(FeedbackDTO dto) {
    Feedback feedback = new Feedback();
    feedback.setFirstName(dto.getFirstName());
    feedback.setLastName(dto.getLastName());
    feedback.setEmail(dto.getEmail());
    feedback.setFeedbackType(dto.getFeedbackType());
    feedback.setContent(dto.getContent());
    // You may set the user here if needed
    return feedback;
  }
}
