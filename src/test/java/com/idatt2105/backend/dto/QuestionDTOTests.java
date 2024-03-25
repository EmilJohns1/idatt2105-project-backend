package com.idatt2105.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.idatt2105.backend.model.Tag;

class QuestionDTOTests {
  @Test
  void test() {
    List<Tag> tags = new ArrayList<>();
    tags.add(new Tag());
    tags.add(null);
    QuestionDTO questionDTO = new QuestionDTO();
    questionDTO.addAllTags(tags);
  }
}
