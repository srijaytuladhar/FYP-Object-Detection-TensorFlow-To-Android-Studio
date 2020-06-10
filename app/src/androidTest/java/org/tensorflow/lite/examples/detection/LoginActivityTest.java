package org.tensorflow.lite.examples.detection;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivitityTestRule = new ActivityTestRule <LoginActivity> (LoginActivity.class);

    private LoginActivity LoginActivity = null;

    @Test
    public void checkARMode() {
        Espresso.onView(withId(R.id.sign_in_button)).perform(click());
    }

    @Before
    public void setUp() throws Exception {
        LoginActivity = mActivitityTestRule.getActivity();

    }

    @After
    public void tearDown() throws Exception {
        LoginActivity = null;

    }
}