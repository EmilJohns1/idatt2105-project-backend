package com.idatt2105.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Data;

/**
 * Entity representing a tag.
 * This class represents a tag that can be added to questions.
 */
@Entity
@Data
@Table(name = "tags")
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "tag_name", nullable = false, unique = true)
  private String tagName;

  @ManyToMany(mappedBy = "tags")
  @Hidden
  @JsonIgnore
  private Set<Question> questions = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tag tag = (Tag) o;
    return Objects.equals(id, tag.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
