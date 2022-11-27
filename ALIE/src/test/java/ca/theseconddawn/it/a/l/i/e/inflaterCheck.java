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

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


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
            Intent expectedIntent = new Intent(activity, ConfigurationActivity.class);
            Intent actual = shadowOf(RuntimeEnvironment.getApplication()).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }
        //no it does not
    }

}




