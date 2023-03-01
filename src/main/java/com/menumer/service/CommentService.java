package com.menumer.service;

import com.menumer.model.Comment;

import com.menumer.model.Recipe;
import com.menumer.model.User;
import com.menumer.payload.request.CommentRequest;
import com.menumer.repository.CommentRepository;
import com.menumer.repository.RecipeRepository;
import com.menumer.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@Slf4j
public class CommentService {
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;
    private CommentRepository commentRepository;


    public Comment createComment(CommentRequest commentRequest, String userId) {
        String recipeId = commentRequest.getRecipeId();
        String commentText = commentRequest.getText();

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.info("User with id {} does not exist", userId);
            return null;
        }

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isEmpty()) {
            log.info("Recipe with id {} does not exist", recipeId);
            return null;
        }

        Recipe recipe = recipeOptional.get();
        Comment comment = Comment.builder()
                .recipeId(recipeId)
                .text(commentText)
                .build();
        commentRepository.save(comment);
        List<Comment> createdComment = recipe.getComments();
        createdComment.add(comment);
        recipe.setComments(createdComment);
        recipeRepository.save(recipe);

        return comment;
    }

    public void deleteComment(String commentId, String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (userOptional.isEmpty()) {
            log.info("User with id {} does not exist", userId);
            return;
        }
        if (commentOptional.isEmpty()) {
            log.info("Comment with id {} does not exist", commentId);
            return;
        }

        Comment comment = commentOptional.get();

        if (!StringUtils.equals(comment.getUser().getId(), userId)){
            log.error("User with id {} is trying to delete a comment that wasn't created by user", commentId);
            return;
        }
        commentRepository.delete(comment);
    }
}
