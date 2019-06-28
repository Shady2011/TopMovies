package com.shady.favouriteproducts;

import android.content.Intent;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {
    ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void getData() {
        testRule.launchActivity(new Intent());
    }

    @Test
    public void sort(){
        testRule.launchActivity(new Intent());
        onView(withId(R.id.rate)).perform(click());
    }
}
