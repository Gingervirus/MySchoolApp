package com.example.amwentzel.myschoolapp.Detention;

import com.example.armin.tp5.factories.Detention.DetentionFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Armin on 2016-05-08.
 */
public class TestDetention {
    @Test
    public void testDetention()
    {
        Assert.assertEquals(true,DetentionFactory.getDetention("YES"));
    }
}
