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

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.snackbar.Snackbar;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton leftArrow;
    private SwitchCompat orientation, fanControl;
    private SeekBar volume;
    private TextView volumeProgress;
    private AudioManager audioManager;
    private LinearLayout settingsLayout;

    private RadioGroup LEDGroup;
    private RadioButton redLED, greenLED, blueLED;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //defining the local instances of the settings
    private static final String SETTINGS = "Settings Pref";
    private static final String SWITCH_ORIENTATION = "Screen Orientation";
    private static final String FAN_CONTROL = "Fan Control";
    private static final String LED_CONTROL = "LED Control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        settingsLayout = findViewById(R.id.TheSecondDawnSettingsLayout);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton3);
        leftArrow.setOnClickListener(this);

        volumeProgress = findViewById(R.id.TheSecondDawnTextView37);
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        volume = findViewById(R.id.TheSecondDawnSeekBar2);
        volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        //changes the volume with the slider values change
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                volumeProgress.setText(getString(R.string.volumeText1) + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        orientation = findViewById(R.id.TheSecondDawnSwitch9);
        orientation.setOnCheckedChangeListener((compoundButton, isChecked) -> setOrientation(isChecked));

        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        orientation.setChecked(sharedPreferences.getBoolean(SWITCH_ORIENTATION, false));

        fanControl = findViewById(R.id.TheSecondDawnSwitch10);
        fanControl.setOnCheckedChangeListener((compoundButton, isChecked) -> fanControl(isChecked));

        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        fanControl.setChecked(sharedPreferences.getBoolean(FAN_CONTROL, false));

        LEDGroup = findViewById(R.id.TheSecondDawnRadioGroup2);
        redLED = findViewById(R.id.TheSecondDawnRadioButton4);
        greenLED = findViewById(R.id.TheSecondDawnRadioButton5);
        blueLED = findViewById(R.id.TheSecondDawnRadioButton6);

        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        int radioGroup = sharedPreferences.getInt(LED_CONTROL, MODE_PRIVATE);
        //sets the LED according to the selected color
        if (radioGroup == R.id.TheSecondDawnRadioButton4) {
            redLED.setChecked(true);
        } else if (radioGroup == R.id.TheSecondDawnRadioButton5) {
            greenLED.setChecked(true);
        } else if (radioGroup == R.id.TheSecondDawnRadioButton6) {
            blueLED.setChecked(true);
        }

        LEDGroup.setOnCheckedChangeListener((radioGroup1, checkedId) -> setLEDColor(checkedId));
    }

    @Override
    public void onClick(View view) {
        //When Left Arrow Image Button is clicked, take user back to previous screen
        if (view.getId() == R.id.TheSecondDawnImageButton3) {
            finish();
        }
    }

    //Refactored Orientation from OnCreate Method to its own Method setOrientation
    private void setOrientation(boolean isChecked) {
        //sets the orientation to landscape
        if (isChecked) {
            editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
            editor.putBoolean(SWITCH_ORIENTATION, true);
            editor.apply();
            orientation.setChecked(true);

            orientation.setText(R.string.orientationText1);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        //else the orientation would turn to portrait
        else {
            editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
            editor.putBoolean(SWITCH_ORIENTATION, false);
            editor.apply();
            orientation.setChecked(false);

            orientation.setText(R.string.orientationText2);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    //Refactored Fan Control from OnCreate Method to its own Method fanControl
    private void fanControl(boolean isChecked) {
        //sets the fan to ON if switched
        if (isChecked) {
            editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
            editor.putBoolean(FAN_CONTROL, true);
            editor.apply();
            fanControl.setChecked(true);

            fanControl.setText(R.string.fanControlText1);
            fanControl.setTextColor(getResources().getColor(R.color.brightgreen));
        }
        //sets the fan OFF on the switch click
        else {
            editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
            editor.putBoolean(FAN_CONTROL, false);
            editor.apply();
            fanControl.setChecked(false);

            fanControl.setText(R.string.fanControlText2);
            fanControl.setTextColor(getResources().getColor(R.color.brightred));
        }
    }

    //Refactored LED Color from OnCreate Method to its own Method setLEDColor
    private void setLEDColor(int checkedId) {

        Snackbar snackbar;

        switch (checkedId) {
            //if Red Radio Button is chosen, Display Snackbar in Red and Store into SharedPref
            case R.id.TheSecondDawnRadioButton4:
                snackbar = Snackbar.make(settingsLayout, R.string.snackbar5, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.brightred));
                snackbar.setTextColor(getResources().getColor(R.color.black));
                snackbar.show();

                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putInt(LED_CONTROL, R.id.TheSecondDawnRadioButton4);
                editor.apply();
                break;

            //if Green Radio Button is chosen, Display Snackbar in Green and Store into SharedPref
            case R.id.TheSecondDawnRadioButton5:
                snackbar = Snackbar.make(settingsLayout, R.string.snackbar6, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.brightgreen));
                snackbar.setTextColor(getResources().getColor(R.color.black));
                snackbar.show();

                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putInt(LED_CONTROL, R.id.TheSecondDawnRadioButton5);
                editor.apply();

                break;

            //if Blue Radio Button is chosen, Display Snackbar in Blue and Store into SharedPref
            case R.id.TheSecondDawnRadioButton6:
                snackbar = Snackbar.make(settingsLayout, R.string.snackbar7, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.brightblue));
                snackbar.setTextColor(getResources().getColor(R.color.black));
                snackbar.show();

                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putInt(LED_CONTROL, R.id.TheSecondDawnRadioButton6);
                editor.apply();
        }
    }
}