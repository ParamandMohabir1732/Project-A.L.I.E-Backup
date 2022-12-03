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

import static android.os.Build.VERSION_CODES.S;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

import android.app.Activity;
import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Implements;

@RunWith(RobolectricTestRunner.class)
@Implements(value = org.robolectric.shadows.ShadowBackdropFrameRenderer.class, minSdk = S, isInAndroidSdk = false)
public class inflaterCheck {

    @Test
    //check if button for inflator is there
    public void checkInflatorNavigation() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class)) {
            controller.setup();
            MainActivity activity = controller.get();

            activity.findViewById(R.id.TheSecondDawnNavigationView).performClick();
            assertNotNull(activity);
        }
    }

    //Check if the button actually inflates
    @Test
    public void unflateNavigation() {

        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class)) {
            controller.setup();
            MainActivity activity = controller.get();

            activity.findViewById(R.id.TheSecondDawnNavigationView).performClick();
            Intent expectedIntent = new Intent(activity, SettingsActivity.class);
            Intent actual = shadowOf(RuntimeEnvironment.getApplication()).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }
        //Test doesn't pass for Demonstration Purposes
    }
}