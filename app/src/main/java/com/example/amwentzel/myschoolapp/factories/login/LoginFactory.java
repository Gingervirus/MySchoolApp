package com.example.amwentzel.myschoolapp.factories.login;

import com.example.amwentzel.myschoolapp.domain.login.Login;

/**
 * Created by amwentzel on 2016/08/08.
 */
public class LoginFactory implements CreateLogin {
    @Override
    public Login createLogin(long userid, String username, String password) {

        Login login = new Login.Builder()
                .Username(username)
                .Password(password)
                .build();
        return login;
    }
}