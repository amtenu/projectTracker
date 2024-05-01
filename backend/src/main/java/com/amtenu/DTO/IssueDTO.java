package com.amtenu.DTO;

import com.amtenu.models.Project;
import com.amtenu.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {

    private Long Id;
    private String title;
    private String description;
    private String status;
    private Long projectId;

    private String priority;
    private LocalDate dueDate;
    private List<String> tags=new ArrayList<>();
    private Project project;

    private User assignee;

}
