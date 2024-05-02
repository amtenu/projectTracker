package com.amtenu.controller;

import com.amtenu.DTO.IssueDTO;
import com.amtenu.models.Issue;
import com.amtenu.models.User;
import com.amtenu.repository.ProjectRepository;
import com.amtenu.request.IssueRequest;
import com.amtenu.response.AuthResponse;
import com.amtenu.response.MessageResponse;
import com.amtenu.service.IssueService;
import com.amtenu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        Issue issue = issueService.getIssueByID(issueId);
        return ResponseEntity.ok(issue);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueProjectId(@PathVariable Long projectId) throws Exception {
        List<Issue> issue = issueService.getIssuesByProjectId(projectId);

        return ResponseEntity.ok(issue);
    }

    @PostMapping("/createIssue")
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue,
                                                @RequestHeader("Authorization")
                                                String token)
            throws Exception {

        User tokenUser = userService.findUserProfileByJwt(token);
        User user = userService.findUserById(tokenUser.getId());


        Issue createdIssue = issueService.createNewIssue(issue, tokenUser);
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setProjectId(createdIssue.getProjectId());
        issueDTO.setAssignee(createdIssue.getAssignee());
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setTags(createdIssue.getTags());
        issueDTO.setPriority(createdIssue.getPriority());
        issueDTO.setDueDate(createdIssue.getDueDate());
        issueDTO.setProject(createdIssue.getProject());

        return ResponseEntity.ok(issueDTO);

    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                       @RequestHeader("Authorization") String token)
            throws Exception {
        User user = userService.findUserProfileByJwt(token);

        issueService.deleteIssue(issueId, user.getId());

        MessageResponse message = new MessageResponse();

        message.setMessage("Issue deleted");

        return ResponseEntity.ok(message);


    }

    @PostMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
                                                @PathVariable Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }


    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long issueId,
                                                   @PathVariable String status) throws Exception {
        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }

}
