package com.amtenu.service;

import com.amtenu.models.Comments;
import com.amtenu.models.Issue;
import com.amtenu.models.User;
import com.amtenu.repository.CommentRepository;
import com.amtenu.repository.IssueRepository;
import com.amtenu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class commentServiceImpl implements commentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired

    private UserRepository userRepository;


    @Override
    public Comments createComment(Long issueId, Long userId, String commentContent) throws Exception {

        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalIssue.isEmpty()) {
            throw new Exception("Issue not found with id" + issueId);
        }

        if (optionalUser.isEmpty()) {
            throw new Exception("User " + userId + " not found");
        }

        Issue issue = optionalIssue.get();
        User user = optionalUser.get();

        Comments comment = new Comments();


        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(commentContent);

        Comments savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);

        return savedComment;


    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comments> optionalComment = commentRepository.findById(commentId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalComment.isEmpty()) {
            throw new Exception("Comment not found " + commentId);
        }

        if (optionalUser.isEmpty()) {
            throw new Exception("User not found " + userId);
        }

        Comments comment = optionalComment.get();
        User user = optionalUser.get();

        if (comment.getUser().equals(user)) {
            commentRepository.delete(comment);
        } else {
            throw new Exception("User is not allowed to delete a comment");
        }


    }

    @Override
    public List<Comments> findCommentsByIssueId(Long issueId) {
        return null;
    }
}
