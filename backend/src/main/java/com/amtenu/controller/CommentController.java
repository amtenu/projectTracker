package com.amtenu.controller;


import com.amtenu.models.Comments;
import com.amtenu.models.User;
import com.amtenu.request.CreateCommentRequest;
import com.amtenu.service.CommentService;
import com.amtenu.service.UserService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
