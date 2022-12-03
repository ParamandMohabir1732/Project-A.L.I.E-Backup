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
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import android.content.pm.ActivityInfo;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Implements;

@RunWith(RobolectricTestRunner.class)
@Implements(value = org.robolectric.shadows.ShadowBackdropFrameRenderer.class, minSdk = S, isInAndroidSdk = false)
public class settingsCheck {
    //see if the text view exists
    @Test
    public void textViewCheck() {
        try (ActivityController<SettingsActivity> controller = Robolectric.buildActivity(SettingsActivity.class)) {
            controller.setup();
            SettingsActivity activity = controller.get();

            TextView checker = (TextView) activity.findViewById(R.id.TheSecondDawnTextView36);
            assertNotNull("Text view cannot be accessed", checker);
            assertEquals("The text is incorrect", "Current Volume:", checker.getText().toString());


        }
    }

    //check whether you can put the screen to the landscape
    @Test
    public void checkLandscape() {
        try (ActivityController<SettingsActivity> controller = Robolectric.buildActivity(SettingsActivity.class)) {
            controller.setup();
            SettingsActivity activity = controller.get();

            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Test
    public void changeOrientation() {


        try (ActivityController<SettingsActivity> controller = Robolectric.buildActivity(SettingsActivity.class)) {
            controller.setup();
            SettingsActivity activity = controller.get();

            activity.findViewById(R.id.TheSecondDawnSwitch9).performClick();
            Assert.assertNotNull(activity);
        }

    }
}
