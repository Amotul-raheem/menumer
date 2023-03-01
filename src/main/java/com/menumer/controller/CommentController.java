package com.menumer.controller;

import com.menumer.model.Comment;
import com.menumer.payload.request.CommentRequest;
import com.menumer.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/comment/recipe")
@Slf4j
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createComment(@RequestBody final CommentRequest commentRequest,
                                           @RequestHeader(name = "USER_ID", required = true) String userId) {
        Comment createdComment = commentService.createComment(commentRequest, userId);
        if (ObjectUtils.isEmpty(createdComment)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{commentId}")
    public ResponseEntity<?> deleteComment (@PathVariable String commentId,
                                            @RequestHeader(name = "USER_ID",
                                            required = true)String userId){
        commentService.deleteComment(commentId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
