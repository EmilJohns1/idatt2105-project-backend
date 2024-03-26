package com.idatt2105.backend.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** Entity representing a question for a quiz. */
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "questions")
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "question_text")
  @NotEmpty
  private String questionText;

  @Column(name = "media_url")
  private String mediaUrl;

  @Column(name = "category")
  private String category;

  @Column(name = "points")
  private int points;

  @ManyToOne
  @JoinColumn(name = "quiz_id", nullable = false)
  @JsonIgnore
  private Quiz quiz;

  @ManyToMany
  @JoinTable(
      name = "question_tag",
      joinColumns = @JoinColumn(name = "question_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> tags = new HashSet<>();

  public void addTags(Collection<Tag> tags) {
    tags.stream().filter(Objects::nonNull).forEach(this.tags::add);
  }

  public void removeTags(Collection<Tag> tags) {
    this.tags.removeAll(tags);
  }
}
