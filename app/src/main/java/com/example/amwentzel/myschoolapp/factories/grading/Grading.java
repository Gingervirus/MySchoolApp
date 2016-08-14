package com.example.amwentzel.myschoolapp.factories.grading;

/**
 * Created by Armin on 2016-04-07.
 */
public abstract class Grading {
    Grading nextGrading;

    public void setNextGrade(Grading nextGrading) {
        this.nextGrading = nextGrading;
    }
    public abstract String handleRequest(int request);

}