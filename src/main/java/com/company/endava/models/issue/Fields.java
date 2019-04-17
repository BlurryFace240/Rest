package com.company.endava.models.issue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fields {
    private Project project;
    private String summary;
    private String description;
    private IssueType issuetype;
    private String[] labels;
    private Assignee assignee;
}