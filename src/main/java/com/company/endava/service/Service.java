package com.company.endava.service;

import com.company.endava.models.issue.Issue;
import com.company.endava.models.LoggedIn;
import com.company.endava.models.Session;
import com.company.endava.models.SessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
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

    public ResponseEntity<?> Auth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<>();
        body.put("username", user);
        body.put("password", pass);
        String uri = adress + "/rest/auth/1/session";
        HttpEntity request = new HttpEntity<>(body, headers);
        sessionResponse = restTemplate.postForObject(uri, request, SessionResponse.class);
        sessionid.setName(sessionResponse.getSession().getName());
        sessionid.setValue(sessionResponse.getSession().getValue());
        return new ResponseEntity<>(sessionid, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> Login() {
        Auth();
        System.err.println(sessionid);
        return new ResponseEntity<>(sessionid, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCurrentUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie", sessionid.getName() + "=" + sessionid.getValue());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        Object ob = restTemplate.exchange(adress + "/rest/auth/1/session", HttpMethod.GET, entity, Object.class).getBody();
        return new ResponseEntity(ob, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getIssue(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie", sessionid.getName() + "=" + sessionid.getValue());
        Map<String, String> body = new HashMap<>();
        HttpEntity entity = new HttpEntity<>(body, headers);
        String uri = adress + "/rest/api/2/issue/" + id;
        Object result = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class).getBody();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createIssue(Issue issue) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie", sessionid.getName() + "=" + sessionid.getValue());
        String uri = adress + "/rest/api/2/issue";
        HttpEntity<String> request = new HttpEntity(issue, headers);
        Object result1 = restTemplate.exchange(uri, HttpMethod.POST, request, Object.class).getBody();
        return new ResponseEntity(result1, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateIssue(Issue issue, String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie", sessionid.getName() + "=" + sessionid.getValue());
        String uri = adress + "/rest/api/2/issue/" + id;
        HttpEntity<String> request = new HttpEntity(issue, headers);
        Object result1 = restTemplate.exchange(uri, HttpMethod.PUT, request, Object.class).getBody();
        return new ResponseEntity(result1, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteIssue(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cookie", sessionid.getName() + "=" + sessionid.getValue());
        String uri = adress + "/rest/api/2/issue/" + id;
        Map<String, String> body = new HashMap<>();
        HttpEntity<String> request = new HttpEntity(body, headers);
        Object result = restTemplate.exchange(uri, HttpMethod.DELETE, request, Object.class).getBody();
        return new ResponseEntity(result, HttpStatus.OK);
    }
}