package com.amtenu.controller;


import com.amtenu.models.Comments;
import com.amtenu.models.User;
import com.amtenu.request.CreateCommentRequest;
import com.amtenu.response.MessageResponse;
import com.amtenu.service.CommentService;
import com.amtenu.service.UserService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<Comments> createComment(@RequestBody CreateCommentRequest request,
                                                  @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Comments createdComment = commentService.createComment(request.getIssueId(), user.getId(), request.getCommentContent());
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                         @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        commentService.deleteComment(commentId, user.getId());
        MessageResponse response = new MessageResponse();
        response.setMessage("Comment deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comments>> getAllComments(@PathVariable Long issueId,
                                                         @RequestHeader("Authorization") String jwt)
            throws Exception {

        List<Comments> commentsList = commentService.findCommentsByIssueId(issueId);
        return new ResponseEntity<>(commentsList, HttpStatus.OK);
    }


}
