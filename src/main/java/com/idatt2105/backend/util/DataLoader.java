package com.idatt2105.backend.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.idatt2105.backend.dto.AlternativeDTO;
import com.idatt2105.backend.dto.CommentDTO;
import com.idatt2105.backend.dto.QuestionDTO;
import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.enumerator.QuestionType;
import com.idatt2105.backend.model.Category;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.service.CommentService;
import com.idatt2105.backend.service.QuestionService;
import com.idatt2105.backend.service.QuizService;
import com.idatt2105.backend.service.UserService;

import jakarta.transaction.Transactional;

/**
 * The DataLoader class is a class that initializes the database with some default data when the
 * application starts.
 */
@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {
  private final UserService userService;
  private final QuizService quizService;
  private final QuestionService questionService;
  private final CommentService commentService;

  public DataLoader(
      UserService userService,
      QuizService quizService,
      QuestionService questionService,
      CommentService commentService) {
    this.userService = userService;
    this.quizService = quizService;
    this.questionService = questionService;
    this.commentService = commentService;
  }

  @Override
  @Transactional
  public void onApplicationEvent(ApplicationReadyEvent event) {
    initialize();
  }

  /** Initializes the database with some default data. */
  @Transactional
  public void initialize() {
    try {
      userService.getUserByUsername(
          "admin"); // This will throw exception if the user does not exist
    } catch (UserNotFoundException e) {
      User admin = new User();
      admin.setUsername("admin");
      admin.setPassword("password");
      userService.addUser(admin, "ADMIN");
    }
    List<String> categoryNames =
        Arrays.asList(
            "Biology", "Chemistry", "Math", "Science", "Geography", "English", "Physics", "Sports");
    List<String> alternatives = Arrays.asList("Oslo", "Stockholm", "Copenhagen", "Helsinki");
    categoryNames.forEach(
        name -> {
          try {
            Category c = new Category();
            c.setName(name);
            quizService.createCategory(c);
          } catch (Exception ignored) {
            // Do nothing
          }
        });
    if (quizService.getAllQuizzes(Pageable.ofSize(1)).isEmpty()) { // No quizzes are stored
      // Make 3 users to show off comments
      List<Long> ids = new ArrayList<>();
      User user = new User();
      user.setUsername("John");
      user.setPassword("password");
      ids.add(userService.addUser(user).getId());

      User user2 = new User();
      user2.setUsername("Jane");
      user2.setPassword("password");
      ids.add(userService.addUser(user2).getId());

      User user3 = new User();
      user3.setUsername("Alice");
      user3.setPassword("password");
      ids.add(userService.addUser(user3).getId());

      String[] comments =
          new String[] {"This quiz is great!", "I love this quiz!", "This quiz is so fun!"};

      for (String category : categoryNames) {
        for (int i = 0; i < 5; i++) { // Create 5 quizzes for each category
          QuizDTO quiz =
              new QuizDTO.Builder()
                  .setTitle("The Fun Quiz")
                  .setDescription(
                      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                          + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, "
                          + "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                  .setAuthorId(1L)
                  .setCategoryName(category)
                  .setIsPublic(true)
                  .setRandomizedOrder(false)
                  .build();
          Long savedId = quizService.save(quiz).getId();

          for (int j = 0; j < 3; j++) {
            CommentDTO comment = new CommentDTO();
            comment.setContent(comments[j]);
            comment.setQuizId(savedId);
            comment.setUserId(ids.get(j));
            commentService.saveComment(comment);
          }

          quizService.addUserToQuiz(1L, savedId);
          Long questionId;
          QuestionDTO question = new QuestionDTO();
          question.setPoints(5);
          question.setQuizId(savedId);

          question.setType(QuestionType.TRUE_OR_FALSE);
          question.setQuestionText(
              "Light from the Sun reaches the Earth in exactly 8 minutes and 20 seconds.");
          question.setIsCorrect(true);
          questionId = questionService.addQuestion(question).getId();
          question.setQuestionId(questionId);
          questionService.updateTrueOrFalseQuestion(question);

          question.setQuestionText(
              "The Great Wall of China is visible from space with the naked eye.");
          question.setIsCorrect(false);
          questionId = questionService.addQuestion(question).getId();
          question.setQuestionId(questionId);
          questionService.updateTrueOrFalseQuestion(question);

          question.setQuestionText("Sharks are mammals.");
          question.setIsCorrect(false);
          questionId = questionService.addQuestion(question).getId();
          question.setQuestionId(questionId);
          questionService.updateTrueOrFalseQuestion(question);

          question.setQuestionText("Octopuses have three hearts.");
          question.setIsCorrect(true);
          questionId = questionService.addQuestion(question).getId();
          question.setQuestionId(questionId);
          questionService.updateTrueOrFalseQuestion(question);

          question.setType(QuestionType.MULTIPLE_CHOICE);
          question.setQuestionText("What is the capital of Norway?");
          questionId = questionService.addQuestion(question).getId();

          AlternativeDTO alternative = new AlternativeDTO();
          alternative.setQuestionId(questionId);
          for (String alternativeText : alternatives) {
            alternative.setAlternativeText(alternativeText);
            alternative.setCorrect(alternativeText.equals("Oslo"));
            questionService.addAlternative(alternative);
          }
        }
      }
    }
  }
}
