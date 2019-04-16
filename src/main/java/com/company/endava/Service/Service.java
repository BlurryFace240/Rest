package com.company.endava.Service;

import com.company.endava.Models.Issue.issue;
import com.company.endava.Models.LoggedIn;
import com.company.endava.Models.Session;
import com.company.endava.Models.SessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service implements ServiceInterface {

    @Value("${a.adress}")
    public String adress;

    @Value("${r.username}")
    public String user;

    @Value("${r.password}")
    public String pass;
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    LoggedIn loggedIn = new LoggedIn();
    SessionResponse sessionResponse = new SessionResponse();
    static Session sessionid = new Session();

    public ResponseEntity<?> Auth()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<>();
        body.put("username", user);
        body.put("password", pass);
        String uri = adress + "/rest/auth/1/session";
        HttpEntity request = new HttpEntity<>(body, headers);
        sessionResponse = restTemplate.postForObject(uri,request,SessionResponse.class);
        sessionid.setName(sessionResponse.getSession().getName());
        sessionid.setValue(sessionResponse.getSession().getValue());
        return new ResponseEntity<>(sessionid,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> Login() {
        Auth();
        System.err.println(sessionid);
        return new ResponseEntity<>(sessionid,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCurrentUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie", sessionid.getName() + "=" + sessionid.getValue());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        loggedIn = restTemplate.exchange(adress + "/rest/auth/1/session", HttpMethod.GET, entity, LoggedIn.class).getBody();
        Object ob = restTemplate.exchange(adress + "/rest/auth/1/session", HttpMethod.GET, entity, Object.class);
        System.err.println(loggedIn);
        return new ResponseEntity(ob, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getIssue(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie",sessionid.getName() + "=" + sessionid.getValue());
        Map<String, String> body = new HashMap<>();
        HttpEntity entity = new HttpEntity<> (body,headers);
        String uri=adress+"/rest/api/2/issue/"+id;
        issue issue = restTemplate.exchange(uri, HttpMethod.GET, entity, com.company.endava.Models.Issue.issue.class).getBody();
        System.err.println(issue);
        Object result = restTemplate.exchange(uri, HttpMethod.GET, entity,Object.class);
        return new ResponseEntity(issue,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createIssue(issue issue) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie",sessionid.getName() + "=" + sessionid.getValue());
        String uri = adress + "/rest/api/2/issue";
        HttpEntity<String> request = new HttpEntity(issue, headers);
        String result = restTemplate.exchange(uri, HttpMethod.POST, request, String.class).getBody();
        Object result1 = restTemplate.exchange(uri, HttpMethod.POST, request, Object.class);
        System.err.println(result);
        return new ResponseEntity(result1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateIssue(issue issue,String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie",sessionid.getName() + "=" + sessionid.getValue());
        String uri = adress + "/rest/api/2/issue/"+id;
        HttpEntity<String> request = new HttpEntity(issue, headers);
        String result = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class).getBody();
        Object result1 = restTemplate.exchange(uri,HttpMethod.PUT,request,Object.class);
        System.err.println(result);
        return new ResponseEntity(result1,HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteIssue(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie",sessionid.getName() + "=" + sessionid.getValue());
        String uri = adress + "/rest/api/2/issue/"+id;
        Map<String,String> body = new HashMap<>();
        HttpEntity<String> request = new HttpEntity(body, headers);
        Object result = restTemplate.exchange(uri,HttpMethod.DELETE,request,Object.class);
        return new ResponseEntity(result,HttpStatus.OK);
    }
}