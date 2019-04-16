package com.company.endava;

import com.company.endava.Models.Issue.issue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EndavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndavaApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate()
    {return new RestTemplate();}

    @Bean
    public issue getIssue()
    {return new issue();}
}
