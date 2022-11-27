package ca.theseconddawn.it.a.l.i.e;

import static android.os.Build.VERSION_CODES.S;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import android.widget.TextView;

import com.google.firebase.database.annotations.NotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Implements;

@RunWith(RobolectricTestRunner.class)
@Implements(value = org.robolectric.shadows.ShadowBackdropFrameRenderer.class, minSdk = S, isInAndroidSdk = false)
public class settingsCheck {
    @Test
    public void textViewCheck(){
        try(ActivityController<SettingsActivity> controller = Robolectric.buildActivity(SettingsActivity.class)){
            controller.setup();
            SettingsActivity activity = controller.get();

            TextView checker = (TextView) activity.findViewById(R.id.TheSecondDawnTextView36);
            assertNotNull("Text view cannot be accessed", checker);
            assertTrue("The text is incorrect", "Current Volume:".equals(checker.getText().toString()));


        }
    }
}
