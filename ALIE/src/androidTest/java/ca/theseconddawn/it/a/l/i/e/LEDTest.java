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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasTextColor;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.android21buttons.fragmenttestrule.FragmentTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    @Test
    public void switchLEDWhite() {
        onView(ViewMatchers.withId(R.id.TheSecondDawnSwitch6)).perform(click());
    }

    @Test
    public void titleColor() {
        onView(withId(R.id.TheSecondDawnTextView9)).check(matches(hasTextColor(R.color.aqua)));
    }

    @Test
    public void LEDImage() {
        onView(withId(R.id.TheSecondDawnImageView38)).check(matches(withContentDescription(R.string.imageView38)));
    }
}
