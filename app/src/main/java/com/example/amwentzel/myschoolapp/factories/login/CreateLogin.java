package com.example.amwentzel.myschoolapp.factories.login;

import com.example.amwentzel.myschoolapp.domain.login.Login;

/**
 * Created by amwentzel on 2016/08/08.
 */
public interface CreateLogin {

    Login createLogin(long userid, String username, String password);

}