package com.idatt2105.backend.dto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.Tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Data Transfer Object (DTO) for a Quiz. This class is used to transfer Quiz data
 * between different layers of the application.
 */
@NoArgsConstructor
@Getter
@Setter
@Data
@AllArgsConstructor
public class QuizDTO {
  private Long id;
  private String title;
  private String description;
  private String quizPictureUrl;
  private LocalDateTime creationDate;
  private LocalDateTime lastModifiedDate;
  private Set<UserDTO> userDTOs;
  private Set<Tag> tags = new HashSet<>();

  /**
   * Constructs a QuizDTO object from a Quiz entity.
   *
   * @param quiz The Quiz entity to construct the DTO from.
   */
  public QuizDTO(Quiz quiz) {
    this.id = quiz.getId();
    this.title = quiz.getTitle();
    this.description = quiz.getDescription();
    this.quizPictureUrl = quiz.getQuizPictureUrl();
    this.creationDate = quiz.getCreationDate();
    this.lastModifiedDate = quiz.getLastModifiedDate();
    this.userDTOs = new HashSet<>();
    quiz.getUsers().stream().map(UserDTO::new).forEach(this.userDTOs::add);
    this.tags = new HashSet<>(quiz.getTags());
  }

  /**
   * Converts the QuizDTO object to a Quiz entity.
   *
   * @return The Quiz entity.
   */
  public Quiz toEntity() {
    Quiz quiz = new Quiz();
    quiz.setId(this.id);
    quiz.setTitle(this.title);
    quiz.setDescription(this.description);
    quiz.setQuizPictureUrl(this.quizPictureUrl);
    quiz.setCreationDate(this.creationDate);
    quiz.setLastModifiedDate(this.lastModifiedDate);
    return quiz;
  }

  /**
   * Adds all the tags from a given Collection.
   *
   * @param tags (Collection &lt;Tag&gt;) The tags to add.
   */
  public void addAllTags(Collection<Tag> tags) {
    tags.stream().filter(Objects::nonNull).forEach(this.tags::add);
  }

  public static class Builder {
    private Long id;
    private String title;
    private String description;
    private String quizPictureUrl;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private Set<UserDTO> userDTOs;
    private Set<Tag> tags = new HashSet<>();

    public Builder setId(Long id) {
      this.id = id;
      return this;
    }

    public Builder setTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder setQuizPictureUrl(String quizPictureUrl) {
      this.quizPictureUrl = quizPictureUrl;
      return this;
    }

    public Builder setCreationDate(LocalDateTime creationDate) {
      this.creationDate = creationDate;
      return this;
    }

    public Builder setLastModifiedDate(LocalDateTime lastModifiedDate) {
      this.lastModifiedDate = lastModifiedDate;
      return this;
    }

    public Builder setUserDTOs(Set<UserDTO> userDTOs) {
      this.userDTOs = userDTOs;
      return this;
    }

    public Builder setTags(Set<Tag> tags) {
      this.tags = tags;
      return this;
    }

    public QuizDTO build() {
      return new QuizDTO(
          id, title, description, quizPictureUrl, creationDate, lastModifiedDate, userDTOs, tags);
    }
  }
}
