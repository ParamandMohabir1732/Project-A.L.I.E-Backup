/*
Paramand Mohabir N01421732
CENG322 Section 0NC
Software Project
*/
/*
Vladislav Vassilyev N01436627
CENG322 Section 0NB
Software Project
*/
/*
Dave Patel N01465129
CENG322 Section 0NA
Software Project
*/
/*
Paolo Brancato N01434080
CENG322 Section ONC
Software Project
*/

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
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText1)).perform(typeText("TheSecondDawnALIEAPP@gmail.com"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText2)).perform(typeText("TheSecondDawn$123"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton1)).perform(click());
    }

    //This user doesn't exist so login won't work
    @Test
    public void invalidLogin() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText1)).perform(typeText("TheSecondDawnALIEAPP100@gmail.com"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText2)).perform(typeText("Wrong Password"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton1)).perform(click());
    }

    //This email is invalid so the system will use regex to deny the request.
    @Test
    public void invalidEmail() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText1)).perform(typeText("TheSecondDawnALIEAPP@gmailcom"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText2)).perform(typeText("TheSecondDawn$123"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton1)).perform(click());
    }

    //This password is incorrect for this account so user cannot login
    @Test
    public void invalidPassword() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText1)).perform(typeText("TheSecondDawnALIEAPP@gmail.com"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText2)).perform(typeText("TheSecondDawn$123222"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton1)).perform(click());
    }
}

