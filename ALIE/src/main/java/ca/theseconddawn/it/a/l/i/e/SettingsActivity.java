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
        //sets the orientation
        orientation.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            //if the orientation switch is clicked then the orientation would be set so landscape
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
        });

        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        orientation.setChecked(sharedPreferences.getBoolean(SWITCH_ORIENTATION, false));

        fanControl = findViewById(R.id.TheSecondDawnSwitch10);
        //sets the fan to on if switched
        fanControl.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putBoolean(FAN_CONTROL, true);
                editor.apply();
                fanControl.setChecked(true);

                fanControl.setText(R.string.fanControlText1);
                fanControl.setTextColor(getResources().getColor(R.color.brightgreen));
            }
            //sets the fan off on the switch click
            else {
                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putBoolean(FAN_CONTROL, false);
                editor.apply();
                fanControl.setChecked(false);

                fanControl.setText(R.string.fanControlText2);
                fanControl.setTextColor(getResources().getColor(R.color.brightred));
            }
        });

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
        } if (radioGroup == R.id.TheSecondDawnRadioButton5) {
            greenLED.setChecked(true);
        }  if (radioGroup == R.id.TheSecondDawnRadioButton6) {
            blueLED.setChecked(true);
        }

        LEDGroup.setOnCheckedChangeListener((radioGroup1, checkedId) -> {
            if (checkedId == R.id.TheSecondDawnRadioButton4) {
                Snackbar snackbar = Snackbar.make(settingsLayout, R.string.snackbar5, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.brightred));
                snackbar.setTextColor(getResources().getColor(R.color.black));
                snackbar.show();

                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putInt(LED_CONTROL, R.id.TheSecondDawnRadioButton4);
                editor.apply();
            } if (checkedId == R.id.TheSecondDawnRadioButton5) {
                Snackbar snackbar = Snackbar.make(settingsLayout, R.string.snackbar6, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.brightgreen));
                snackbar.setTextColor(getResources().getColor(R.color.black));
                snackbar.show();

                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putInt(LED_CONTROL, R.id.TheSecondDawnRadioButton5);
                editor.apply();
            } if (checkedId == R.id.TheSecondDawnButton6) {
                Snackbar snackbar = Snackbar.make(settingsLayout, R.string.snackbar7, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.brightblue));
                snackbar.setTextColor(getResources().getColor(R.color.black));
                snackbar.show();

                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putInt(LED_CONTROL, R.id.TheSecondDawnRadioButton6);
                editor.apply();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton3) {
            finish();
        }
    }
}