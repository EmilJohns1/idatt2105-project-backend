package com.idatt2105.backend.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.QuestionType;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.model.TrueOrFalseQuestion;
import com.idatt2105.backend.util.NoNullElements;

import lombok.Data;
import lombok.experimental.Accessors;

/** Data Transfer Object (DTO) for questions. Contains an enum for question types. */
@Data
public class QuestionDTO {
  private Long quizId;
  private String questionText;
  private QuestionType type;
  private Long questionId;
  private String mediaUrl;
  private String category;
  @NoNullElements private Set<Tag> tags = new HashSet<>();

  @Accessors(fluent = true)
  private Boolean isCorrect;

  /**
   * Adds all the tags from a given Collection.
   *
   * @param tags (Collection &lt;Tag&gt;) The tags to add.
   */
  public void addAllTags(Collection<Tag> tags) {
    tags.stream().filter(Objects::nonNull).forEach(this.tags::add);
  }

  /**
   * Gets the ids of all tags.
   *
   * @return The ids of all tags.
   */
  public List<Long> getAllTagIds() {
    return tags.stream().map(Tag::getId).toList();
  }

  /**
   * Instantiates a Question object based on the type.
   *
   * @return (Question) A new instance of the class specified by the type field.
   * @see Question
   */
  public Question instantiateQuestion() {
    return switch (type) {
      case TRUE_OR_FALSE -> new TrueOrFalseQuestion();
      case MULTIPLE_CHOICE -> new MultipleChoiceQuestion();
    };
  }
}
