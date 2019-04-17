package com.company.endava.controllers;

import com.company.endava.models.issue.*;
import com.company.endava.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    Service service;

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
    public ResponseEntity Create(@RequestBody Issue issue)
    {return service.createIssue(issue);}

    @GetMapping("/update/{id}")
    public ResponseEntity Update(@PathVariable String id,@RequestBody Issue issue)
    {return service.updateIssue(issue,id);}

    @GetMapping("/delete/{id}")
    public ResponseEntity Delete(@PathVariable String id)
    {return service.deleteIssue(id);}
}
