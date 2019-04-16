package com.company.endava.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private AtomicLong failedLoginCount;
    private AtomicLong loginCount;
    private String lastFailedLoginTime;
    private String previousLoginTime;
}
