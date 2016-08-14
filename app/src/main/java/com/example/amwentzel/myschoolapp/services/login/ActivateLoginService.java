package com.example.amwentzel.myschoolapp.services.login;

/**
 * Created by amwentzel on 2016/08/08.
 */
public interface ActivateLoginService {

    String activateLoginAccount(long clientid, String username, String password);

    boolean isAccountActivated();

    boolean deactivateAccount();
}