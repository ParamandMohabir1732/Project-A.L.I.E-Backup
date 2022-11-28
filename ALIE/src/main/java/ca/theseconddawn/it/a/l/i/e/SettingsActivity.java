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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton leftArrow;
    private SwitchCompat orientation, fanControl;
    private SeekBar volume;
    private TextView volumeProgress;
    private AudioManager audioManager;
    private Spinner ledControls;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String SETTINGS = "Settings Pref";
    private static final String SWITCH_ORIENTATION = "Screen Orientation";
    private static final String FAN_CONTROL = "Fan Control";
    private static final String LED_SPINNER = "LED Control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton3);
        leftArrow.setOnClickListener(this);

        ledControls = findViewById(R.id.TheSecondDawnSpinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ledControls.setAdapter(adapter);
        ledControls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String LED = ledControls.getItemAtPosition(position).toString();
                Toast.makeText(adapterView.getContext(), LED, Toast.LENGTH_SHORT).show();

                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putString(LED_SPINNER, String.valueOf(ledControls.getSelectedItemPosition()));
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        sharedPreferences.getString(LED_SPINNER, String.valueOf(ledControls.getSelectedItemPosition()));

        volumeProgress = findViewById(R.id.TheSecondDawnTextView37);
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        volume = findViewById(R.id.TheSecondDawnSeekBar2);
        volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                volumeProgress.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        orientation = findViewById(R.id.TheSecondDawnSwitch9);
        orientation.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putBoolean(SWITCH_ORIENTATION, true);
                editor.apply();
                orientation.setChecked(true);

                orientation.setText("Landscape");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putBoolean(SWITCH_ORIENTATION, false);
                editor.apply();
                orientation.setChecked(false);

                orientation.setText("Portrait");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });

        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        orientation.setChecked(sharedPreferences.getBoolean(SWITCH_ORIENTATION, false));

        fanControl = findViewById(R.id.TheSecondDawnSwitch10);
        fanControl.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putBoolean(FAN_CONTROL, true);
                editor.apply();
                fanControl.setChecked(true);

                fanControl.setText("ON");
                fanControl.setTextColor(getResources().getColor(R.color.brightgreen));
            } else {
                editor = getSharedPreferences(SETTINGS, MODE_PRIVATE).edit();
                editor.putBoolean(FAN_CONTROL, false);
                editor.apply();
                fanControl.setChecked(false);

                fanControl.setText("OFF");
                fanControl.setTextColor(getResources().getColor(R.color.brightred));
            }
        });

        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        fanControl.setChecked(sharedPreferences.getBoolean(FAN_CONTROL, false));

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton3) {
            finish();
        }
    }
}