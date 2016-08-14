package com.example.amwentzel.myschoolapp.factories.grading;

/**
 * Created by Armin on 2016-04-07.
 */
public class GradingTwo extends Grading{
    @Override
    public String handleRequest(int request) {
        if(request > 70 & request < 75){
            return "High Pass";
        }else{
            if (nextGrading!=null) {
                return nextGrading.handleRequest(request);
            }
            return "No Grade";
        }
    }
}