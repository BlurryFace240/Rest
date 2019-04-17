package com.company.endava.service;

import com.company.endava.models.issue.Issue;
import org.springframework.http.ResponseEntity;

public interface ServiceInterface {
    public ResponseEntity Login();
    public ResponseEntity getCurrentUser();
    public ResponseEntity getIssue(String id);
    public ResponseEntity createIssue(Issue issue);
    public ResponseEntity updateIssue(Issue issue, String id);
    public ResponseEntity deleteIssue(String id);
}
