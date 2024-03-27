package com.idatt2105.backend.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Entity representing a tag. This class represents a tag that can be added to questions. */
@Entity
@Data
@Table(name = "tags")
@NoArgsConstructor
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "tag_name", nullable = false, unique = true)
  private String tagName;

  @ManyToMany(mappedBy = "tags")
  @Hidden
  @JsonIgnore
  private Set<Quiz> quizzes = new HashSet<>();

  public Tag(String tagName) {
    setTagName(tagName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tag tag = (Tag) o;
    return Objects.equals(this.tagName, tag.tagName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tagName);
  }

  @Override
  public String toString() {
    return "Tag{" + "id=" + id + ", tagName=\"" + tagName + "\"}";
  }
}
