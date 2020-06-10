package org.tensorflow.lite.examples.detection;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AfterLoginActivityTest {

    @Rule
    public ActivityTestRule<AfterLoginActivity> mActivitityTestRule = new ActivityTestRule <AfterLoginActivity> (AfterLoginActivity.class);

    private AfterLoginActivity afterLoginActivity = null;

    @Test
    public void checkARMode() {
        Espresso.onView(withId(R.id.btn_object_detection)).perform(click());
        Espresso.onView(withId(R.id.btn_AR)).perform(click());
        Espresso.onView(withId(R.id.button_sign_out)).perform(click());
    }

    @Before
    public void setUp() throws Exception {
        afterLoginActivity = mActivitityTestRule.getActivity();

    }

    @After
    public void tearDown() throws Exception {
        afterLoginActivity = null;

    }
}