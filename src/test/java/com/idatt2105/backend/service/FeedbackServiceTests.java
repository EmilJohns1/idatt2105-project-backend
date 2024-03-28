package com.idatt2105.backend.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.idatt2105.backend.dto.FeedbackDTO;
import com.idatt2105.backend.model.Feedback;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.FeedbackRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FeedbackServiceTests {
  @InjectMocks private FeedbackService feedbackService;
  @Mock private FeedbackRepository feedbackRepository;
  @Mock private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    when(feedbackRepository.save(any(Feedback.class))).thenAnswer(returnsFirstArg());
    User user = new User();
    user.setId(1L);
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
  }

  @Nested
  class BasicFunctionality {
    private FeedbackDTO dto;
    private Feedback feedback;

    @BeforeEach
    void setUp() {
      String firstName = "John";
      String lastName = "Doe";

      dto = new FeedbackDTO();
      dto.setFirstName(firstName);
      dto.setLastName(lastName);

      feedback = new Feedback();
      feedback.setFirstName(firstName);
      feedback.setLastName(lastName);
    }

    @Test
    void createFeedback() {
      dto.setUserId(1L);
      FeedbackDTO created = feedbackService.createFeedback(dto);
      assertEquals(dto, created);
    }

    @Test
    void updateFeedback() {
      when(feedbackRepository.findById(1L)).thenReturn(Optional.of(new Feedback()));
      feedbackService.updateFeedback(1L, dto);
      verify(feedbackRepository).save(feedback);
    }

    @Test
    void deleteFeedback() {
      feedbackService.deleteFeedback(1L);
      verify(feedbackRepository).deleteById(1L);
    }

    @Test
    void getAllFeedback() {
      when(feedbackRepository.findAll()).thenReturn(List.of(feedback));
      assertEquals(List.of(dto), feedbackService.getAllFeedback());
    }

    @Test
    void getFeedbackByUserId() {
      when(feedbackRepository.findByUserId(1L)).thenReturn(List.of(feedback));
      assertEquals(List.of(dto), feedbackService.getFeedbackByUserId(1L));
    }
  }

  @Nested
  class InvalidParameters {
    @Test
    void createFeedbackWithNullAsParameterThrowsException() {
      assertThrows(IllegalArgumentException.class, () -> feedbackService.createFeedback(null));
    }

    @Test
    void createFeedbackWithNonExistentUserThrowsException() {
      FeedbackDTO dto = new FeedbackDTO();
      dto.setUserId(5L);
      assertThrows(InvalidIdException.class, () -> feedbackService.createFeedback(dto));
    }

    @Test
    void updateFeedbackWithNullAsIdParameterThrowsException() {
      FeedbackDTO dto = new FeedbackDTO();
      assertThrows(IllegalArgumentException.class, () -> feedbackService.updateFeedback(null, dto));
    }

    @Test
    void updateFeedbackWithNullAsDTOParameterThrowsException() {
      assertThrows(IllegalArgumentException.class, () -> feedbackService.updateFeedback(1L, null));
    }

    @Test
    void deleteFeedbackWithNullAsIdParameterThrowsException() {
      assertThrows(IllegalArgumentException.class, () -> feedbackService.deleteFeedback(null));
    }

    @Test
    void getFeedbackByUserIdWithNullAsParameterThrowsException() {
      assertThrows(IllegalArgumentException.class, () -> feedbackService.getFeedbackByUserId(null));
    }
  }
}
