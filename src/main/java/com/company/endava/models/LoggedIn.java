package com.company.endava.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoggedIn {
    private String self;
    private String name;
    private LoginInfo loginInfo;
}
