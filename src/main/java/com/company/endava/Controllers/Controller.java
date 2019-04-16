package com.company.endava.Controllers;

import com.company.endava.Models.Issue.*;
import com.company.endava.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    Service service;

    com.company.endava.Models.Issue.issue issue = new issue(
                        new fields(
                            new project("IIA"), "Summary", "desc", new issuetype(1),
                                new String[]{"LAbel","Lavel"},
                            new assignee("esegov")));

    @GetMapping("/login")
    public ResponseEntity Login()
    {return service.Login();}

    @GetMapping("/current")
    public ResponseEntity Current()
    {return service.getCurrentUser();}

    @GetMapping("/get/{id}")
    public ResponseEntity Get(@PathVariable String id)
    {return service.getIssue(id);}

    @GetMapping("/create")
    public ResponseEntity Create()
    {return service.createIssue(issue);}

    @GetMapping("/update/{id}")
    public ResponseEntity Update(@PathVariable String id)
    {return service.updateIssue(issue,id);}

    @GetMapping("/delete/{id}")
    public ResponseEntity Delete(@PathVariable String id)
    {return service.deleteIssue(id);}
}
