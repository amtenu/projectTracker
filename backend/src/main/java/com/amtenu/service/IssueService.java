package com.amtenu.service;

import com.amtenu.models.Issue;
import com.amtenu.models.User;
import com.amtenu.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueByID(Long issueId) throws Exception;

    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;

    Issue createNewIssue(IssueRequest issue, User user) throws Exception;

  void deleteIssue(Long issueId,Long userId) throws Exception;

   Issue addUserToIssue(Long issueId,Long userId) throws Exception;

   Issue updateStatus(Long issueId,String status) throws Exception;


}
