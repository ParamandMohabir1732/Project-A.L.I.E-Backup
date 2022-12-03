package ca.theseconddawn.it.a.l.i.e;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.android21buttons.fragmenttestrule.FragmentTestRule;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.theseconddawn.it.a.l.i.e.LEDFrag;
import ca.theseconddawn.it.a.l.i.e.R;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class LEDTest {

    @Rule
    public FragmentTestRule<?, LEDFrag> fragmentTestRule = FragmentTestRule.create(LEDFrag.class);

    @Test
    public void colorPickerButton() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton7)).perform(click());
    }

    @Test
    public void locationPermissionButton() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnButton8)).perform(click());
    }

    @Test
    public void switchPower() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnSwitch5)).perform(click());
    }
}
