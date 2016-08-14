package com.example.amwentzel.myschoolapp.factories.grading;

/**
 * Created by Armin on 2016-04-07.
 */
public class MarkGradingFactory {
    public static String getGrade(int value){
        Grading chain = setUpChain();
        return chain.handleRequest(value);
    }

    public static Grading setUpChain(){
        Grading a = new GradingOne();
        Grading b = new GradingTwo();
        Grading c = new GradingThree();
        a.setNextGrade(b);
        b.setNextGrade(c);
        return a;
    }
}
