package com.example.amwentzel.myschoolapp.factories.detention;


import com.example.amwentzel.myschoolapp.domain.detention.Detention;
import com.example.amwentzel.myschoolapp.domain.detention.DetentionNo;
import com.example.amwentzel.myschoolapp.domain.detention.DetentionYes;

/**
 * Created by Armin on 2016-04-07.
 */
public class DetentionFactory {
    public static boolean getDetention(String req)
    {
        Detention chain = setUpChain();
        return chain.handleRequest(req);
    }

    public static Detention setUpChain()
    {
        Detention a = new DetentionYes();
        Detention b = new DetentionNo();

        a.setNextJob(b);
        return a;
    }
}
