package ca.theseconddawn.it.a.l.i.e;

import static android.os.Build.VERSION_CODES.S;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Implements;

@RunWith(RobolectricTestRunner.class)
@Implements(value = org.robolectric.shadows.ShadowBackdropFrameRenderer.class, minSdk = S, isInAndroidSdk = false)
public class homeCheck {
    
}
