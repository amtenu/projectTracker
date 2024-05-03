package com.amtenu.service;


import com.amtenu.models.Comments;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    Comments createComment(Long commentId,Long issueId,String comment) throws Exception;

    void deleteComment(Long commentId,Long userId) throws Exception;

    List<Comments> findCommentsByIssueId(Long issueId);

}
