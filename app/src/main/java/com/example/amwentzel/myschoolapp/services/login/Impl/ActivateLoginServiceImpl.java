package com.example.amwentzel.myschoolapp.services.login.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.amwentzel.myschoolapp.config.util.DomainState;
import com.example.amwentzel.myschoolapp.domain.login.Login;
import com.example.amwentzel.myschoolapp.repository.login.Impl.LoginRepositoryImpl;
import com.example.amwentzel.myschoolapp.repository.login.LoginRepository;
import com.example.amwentzel.myschoolapp.services.login.ActivateLoginService;

/**
 * Created by amwentzel on 2016/08/08.
 */
 public class ActivateLoginServiceImpl extends Service implements ActivateLoginService {


        private final IBinder localBinder = new ActivateLoginServiceLocalBinder();
        private LoginRepository loginRepository;

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return localBinder;
        }



        public class ActivateLoginServiceLocalBinder extends Binder {

            public ActivateLoginServiceImpl getService(){return ActivateLoginServiceImpl.this;}
        }

        @Override
        public String activateLoginAccount(long clientid, String username, String password) {

            if(true)
            {
                Login standardUser = new Login.Builder()
                        .user_id(clientid)
                        .Username(username)
                        .Password(password)
                        .build();


                createLogin(standardUser);

                return DomainState.ACTIVATED.name();
            }
            else
            {
                return DomainState.NOTACTIVATED.name();
            }

        }

        @Override
        public boolean isAccountActivated() {
            return loginRepository.findAll().size()>0;
        }

        @Override
        public boolean deactivateAccount() {
            int row = loginRepository.deleteAll();
            return row>0;
        }

        private Login createLogin(Login login)
        {
            loginRepository = new LoginRepositoryImpl(this.getApplicationContext());

            return loginRepository.save(login);
        }

    }
