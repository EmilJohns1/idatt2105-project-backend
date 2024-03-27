package com.idatt2105.backend.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** Represents a quiz. */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "quizzes")
@EqualsAndHashCode(exclude = "users")
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "quiz_picture_url")
  private String quizPictureUrl;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "last_modified_date")
  private LocalDateTime lastModifiedDate;

  @ManyToMany(
      mappedBy = "quizzes",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JsonBackReference
  @Hidden
  private Set<User> users = new HashSet<>();

  @OneToMany(mappedBy = "quiz")
  private Set<Question> questions = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "quiz_tag",
      joinColumns = @JoinColumn(name = "quiz_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> tags = new HashSet<>();

  public void addTags(Collection<Tag> tags) {
    tags.stream().filter(Objects::nonNull).forEach(this.tags::add);
  }

  public void removeTags(Collection<Tag> tags) {
    tags.stream().filter(Objects::nonNull).forEach(this.tags::remove);
  }
}
