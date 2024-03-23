package com.idatt2105.backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.idatt2105.backend.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class UserDTO {
    private Long id;
    private String username;
    private List<QuizDTO> quizzes;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.quizzes = user.getQuizzes()
                .stream()
                .map(QuizDTO::new)
                .collect(Collectors.toList());
    }

    public UserDTO(Long id, String username, List<QuizDTO> quizzes) {
        this.id = id;
        this.username = username;
        this.quizzes = quizzes;
    }

}