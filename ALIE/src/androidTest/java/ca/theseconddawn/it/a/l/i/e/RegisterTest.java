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

public class RegisterTest {

    @Rule
    public ActivityTestRule<RegisterActivity> activityActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void invalidEmail() {
        //Email is Invalid
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText11)).perform(typeText("Titus"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText3)).perform(typeText("Titus@gmailcom"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton2)).perform(click());
    }

    @Test
    public void invalidPasswordSpecialChar() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText11)).perform(typeText("Titus"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText3)).perform(typeText("Titus@gmail.com"), closeSoftKeyboard());
        //Password Needs 1 Special Char
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText4)).perform(typeText("TheSecondDawn"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton2)).perform(click());
    }

    @Test
    public void invalidPasswordNumber() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText11)).perform(typeText("Titus"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText3)).perform(typeText("Titus@gmail.com"), closeSoftKeyboard());
        //Password Needs at least 1 digit
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText4)).perform(typeText("TheSecondDawn$"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton2)).perform(click());
    }

    @Test
    public void invalidPasswordAlphabet() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText11)).perform(typeText("Titus"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText3)).perform(typeText("Titus@gmail.com"), closeSoftKeyboard());
        //Password Needs at least 1 Upper case Alphabet
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText4)).perform(typeText("1111111$"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton2)).perform(click());
    }

    @Test
    public void passwordNotMatching() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText11)).perform(typeText("Titus"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText3)).perform(typeText("Titus@gmail.com"), closeSoftKeyboard());
        //Passwords Don't Match
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText4)).perform(typeText("TheSecondDawn$123"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnEditText5)).perform(typeText("TheSecondDawn$1234"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton2)).perform(click());
    }
}
