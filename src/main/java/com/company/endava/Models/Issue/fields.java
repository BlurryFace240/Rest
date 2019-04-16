package com.company.endava.Models.Issue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class fields {
    private com.company.endava.Models.Issue.project project;
    private String summary;
    private String description;
    private issuetype issuetype;
    private String[] labels;
    private com.company.endava.Models.Issue.assignee assignee;
}