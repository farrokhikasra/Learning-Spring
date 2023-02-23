package com.firstapp.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password) {
        boolean isValidUsername = username.equalsIgnoreCase("kasra");
        boolean isValidPassword = password.equalsIgnoreCase("farrokhi");

        return isValidUsername && isValidPassword;
    }
}
