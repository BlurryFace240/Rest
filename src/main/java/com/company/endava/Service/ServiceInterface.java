package com.company.endava.Service;

import com.company.endava.Models.Issue.issue;
import org.springframework.http.ResponseEntity;

public interface ServiceInterface {
    public ResponseEntity Login();
    public ResponseEntity getCurrentUser();
    public ResponseEntity getIssue(String id);
    public ResponseEntity createIssue(issue issue);
    public ResponseEntity updateIssue(issue issue,String id);
    public ResponseEntity deleteIssue(String id);
}
