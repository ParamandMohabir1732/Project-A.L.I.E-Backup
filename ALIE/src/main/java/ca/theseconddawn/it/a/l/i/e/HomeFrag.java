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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class HomeFrag extends Fragment implements View.OnClickListener {

    public TextView voiceInput;
    private ImageButton voiceButton, speakerButton, ledButton, fanButton;
    private SwitchCompat speakerSwitch, ledSwitch, fanSwitch, voiceSwitch;
    private FloatingActionButton voiceInputButton;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String HOME = "Home Pref";
    private static final String SPEAKER = "Speaker Switch";
    private static final String LED = "LED Switch";
    private static final String FAN = "Fan Switch";
    private static final String VOICE = "Voice Switch";

    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    private static final int RESULT_OK = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        voiceInput = view.findViewById(R.id.TheSecondDawnTextView19);

        voiceInputButton = view.findViewById(R.id.TheSecondDawnFloatingButton1);
        voiceInputButton.setOnClickListener(this);

        speakerButton = view.findViewById(R.id.TheSecondDawnImageButton5);
        speakerButton.setOnClickListener(this);

        ledButton = view.findViewById(R.id.TheSecondDawnImageButton6);
        ledButton.setOnClickListener(this);

        fanButton = view.findViewById(R.id.TheSecondDawnImageButton7);
        fanButton.setOnClickListener(this);

        voiceButton = view.findViewById(R.id.TheSecondDawnImageButton8);
        voiceButton.setOnClickListener(this);

        speakerSwitch = view.findViewById(R.id.TheSecondDawnSwitch1);
        speakerSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> speakerControl(isChecked));

        sharedPreferences = view.getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE);
        speakerSwitch.setChecked(sharedPreferences.getBoolean(SPEAKER, false));

        ledSwitch = view.findViewById(R.id.TheSecondDawnSwitch2);
        ledSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> ledControl(isChecked));

        sharedPreferences = view.getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE);
        ledSwitch.setChecked(sharedPreferences.getBoolean(LED, false));

        fanSwitch = view.findViewById(R.id.TheSecondDawnSwitch3);
        fanSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> fanControl(isChecked));

        sharedPreferences = view.getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE);
        fanSwitch.setChecked(sharedPreferences.getBoolean(FAN, false));

        voiceSwitch = view.findViewById(R.id.TheSecondDawnSwitch4);
        voiceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    editor = view.getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE).edit();
                    editor = editor.putBoolean(VOICE, true);
                    editor.apply();

                    voiceSwitch.setChecked(true);
                    voiceSwitch.setText("ON");
                    voiceSwitch.setTextColor(getResources().getColor(R.color.brightgreen));
                    voiceButton.setImageResource(R.drawable.voice_button_on);
                } else {
                    editor = view.getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE).edit();
                    editor = editor.putBoolean(VOICE, false);
                    editor.apply();

                    voiceSwitch.setChecked(false);
                    voiceSwitch.setText("OFF");
                    voiceSwitch.setTextColor(getResources().getColor(R.color.brightred));
                    voiceButton.setImageResource(R.drawable.voice_button_off);
                }
            }
        });

        sharedPreferences = view.getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE);
        voiceSwitch.setChecked(sharedPreferences.getBoolean(VOICE, false));

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton5) {
            SpeakerFrag speakerFrag = new SpeakerFrag();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.TheSecondDawnDrawerLayout, speakerFrag, speakerFrag.getTag()).commit();
        }

        if (view.getId() == R.id.TheSecondDawnFloatingButton1) {
            setVoiceInput();
        }
    }

    private void setVoiceInput() {
        Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voice.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voicePrompt));

        try {
            startActivityForResult(voice, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception exception) {
            Toast.makeText(getActivity(), "" + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                voiceInput.setText(result.get(0));
            }
        }
    }

    private void speakerControl(boolean isChecked) {
        if (isChecked) {
            editor = getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE).edit();
            editor = editor.putBoolean(SPEAKER, true);
            editor.apply();

            speakerSwitch.setChecked(true);
            speakerSwitch.setText("ON");
            speakerSwitch.setTextColor(getResources().getColor(R.color.brightgreen));
            speakerButton.setImageResource(R.drawable.speaker_button_on);
        } else {
            editor = getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE).edit();
            editor = editor.putBoolean(SPEAKER, false);
            editor.apply();

            speakerSwitch.setChecked(false);
            speakerSwitch.setText("OFF");
            speakerSwitch.setTextColor(getResources().getColor(R.color.brightred));
            speakerButton.setImageResource(R.drawable.speaker_button_off);
        }
    }

    private void ledControl(boolean isChecked) {
        if (isChecked) {
            editor = getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE).edit();
            editor = editor.putBoolean(LED, true);
            editor.apply();

            ledSwitch.setChecked(true);
            ledSwitch.setText("ON");
            ledSwitch.setTextColor(getResources().getColor(R.color.brightgreen));
            ledButton.setImageResource(R.drawable.led_button_on);
        } else {
            editor = getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE).edit();
            editor = editor.putBoolean(LED, false);
            editor.apply();

            ledSwitch.setChecked(false);
            ledSwitch.setText("OFF");
            ledSwitch.setTextColor(getResources().getColor(R.color.brightred));
            ledButton.setImageResource(R.drawable.led_button_off);
        }
    }

    private void fanControl(boolean isChecked) {
        if (isChecked) {
            editor = getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE).edit();
            editor = editor.putBoolean(FAN, true);
            editor.apply();

            fanSwitch.setChecked(true);
            fanSwitch.setText("ON");
            fanSwitch.setTextColor(getResources().getColor(R.color.brightgreen));
            fanButton.setImageResource(R.drawable.fan_button_on);
        } else {
            editor = getContext().getSharedPreferences(HOME, Context.MODE_PRIVATE).edit();
            editor = editor.putBoolean(FAN, false);
            editor.apply();

            fanSwitch.setChecked(false);
            fanSwitch.setText("OFF");
            fanSwitch.setTextColor(getResources().getColor(R.color.brightred));
            fanButton.setImageResource(R.drawable.fan_button_off);
        }
    }
}