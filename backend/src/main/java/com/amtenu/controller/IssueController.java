package com.amtenu.controller;

import com.amtenu.DTO.IssueDTO;
import com.amtenu.models.Issue;
import com.amtenu.models.User;
import com.amtenu.repository.ProjectRepository;
import com.amtenu.request.IssueRequest;
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
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue, @RequestHeader("Authorization") String token)
            throws Exception {

        User tokenUser= userService.findUserProfileByJwt(token);
        User user=userService.findUserById(tokenUser.getId());


            Issue createdIssue=issueService.createNewIssue(issue,tokenUser);
            IssueDTO issueDTO=new IssueDTO();
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


}
