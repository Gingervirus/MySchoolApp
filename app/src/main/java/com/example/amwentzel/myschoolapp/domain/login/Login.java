package com.example.amwentzel.myschoolapp.domain.login;

/**
 * Created by amwentzel on 2016/08/08.
 */
public class Login {
    private long user_id;
    private String username;
    private String password;

    private Login(){}

    public long getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Login (Builder build)
    {
        this.user_id = build.user_id;
        this.username = build.username;
        this.password = build.password;

    }

    public static class Builder
    {
        private long user_id;
        private String username;
        private String password;

        public Builder user_id(long value)
        {
            this.user_id = value;
            return this;
        }

        public Builder Username(String value)
        {
            this.username = value;
            return this;

        }

        public Builder Password(String value)
        {
            this.password = value;
            return this;
        }

        public Builder copy(Login value)
        {
            this.user_id = value.user_id;
            this.username = value.username;
            this.password = value.password;

            return this;
        }

        public Login build(){return new Login(this);}
    }
}
