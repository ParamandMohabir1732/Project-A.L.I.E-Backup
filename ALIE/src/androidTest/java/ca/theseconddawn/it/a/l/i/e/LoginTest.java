package ca.theseconddawn.it.a.l.i.e;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class LoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    //Test should run successfully if no Email and Password is store in remember me
    @Test
    public void validLogin() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText1))
                .perform(typeText("TheSecondDawnALIEAPP@gmail.com"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText2))
                .perform(typeText("TheSecondDawn$123"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton1)).perform(click());
    }

    @Test
    public void invalidLogin() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText1))
                .perform(typeText("TheSecondDawnALIEAPP@gmail.com"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText2))
                .perform(typeText("Wrong Password"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton1)).perform(click());
    }

    @Test
    public void invalidLEmail() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText1))
                .perform(typeText("TheSecondDawnALIEAPP@gmailcom"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText2))
                .perform(typeText("TheSecondDawn$123"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton1)).perform(click());
    }
}

