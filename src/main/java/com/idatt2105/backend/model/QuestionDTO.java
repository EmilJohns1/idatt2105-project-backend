package com.idatt2105.backend.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.idatt2105.backend.util.InvalidQuestionTypeException;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** Data Transfer Object (DTO) for questions. Contains an enum for question types. */
@Data
public class QuestionDTO {
  /** Enum for question types. */
  private enum Type {
    TRUE_OR_FALSE("true_or_false"),
    MULTIPLE_CHOICE("multiple_choice");

    private final String value;

    Type(String value) {
      this.value = value;
    }

    /**
     * Gets the enum from a string value.
     *
     * @param value (String) The string value to get the enum from.
     * @return (Type) The enum corresponding to the value.
     * @throws InvalidQuestionTypeException if the value is not a valid question type.
     */
    @JsonCreator
    public static Type fromValue(String value) {
      return switch (value) {
        case "true_or_false" -> TRUE_OR_FALSE;
        case "multiple_choice" -> MULTIPLE_CHOICE;
        default -> throw new InvalidQuestionTypeException(value);
      };
    }

    @Override
    public String toString() {
      return value;
    }
  }

  private Long quizId;
  private String questionText;
  private Type type;
  private Long questionId;
  private String mediaUrl;
  private String category;
  private Set<Tag> tags = new HashSet<>();
  private Boolean isCorrect;

  /**
   * Adds all the tags from a given Collection.
   *
   * @param tags (Collection &lt;Tag&gt;) The tags to add.
   */
  public void addAllTags(@Validated @NotNull Collection<Tag> tags) {
    this.tags.addAll(tags);
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
