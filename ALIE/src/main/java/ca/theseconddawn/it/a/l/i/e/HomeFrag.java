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

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Locale;

public class HomeFrag extends Fragment {

    public TextView voiceInput;
    private ImageButton voiceInputButton, speakerButton, ledButton, fanButton;
    private SwitchCompat speakerSwitch, ledSwitch, fanSwitch, voiceSwitch;

    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    private static final int RESULT_OK = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        voiceInput = view.findViewById(R.id.TheSecondDawnTextView19);

        voiceInputButton = view.findViewById(R.id.TheSecondDawnImageButton4);
        voiceInputButton.setOnClickListener(view1 -> setVoiceInput());

        speakerButton = view.findViewById(R.id.TheSecondDawnImageButton5);
        speakerButton.setOnClickListener(view12 -> {
            SpeakerFrag speakerFrag = new SpeakerFrag();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.TheSecondDawnDrawerLayout, speakerFrag, speakerFrag.getTag()).commit();
        });

        ledButton = view.findViewById(R.id.TheSecondDawnImageButton6);
        ledButton.setOnClickListener(view14 -> {
        });

        fanButton = view.findViewById(R.id.TheSecondDawnImageButton7);
        fanButton.setOnClickListener(view13 -> {
        });

        speakerSwitch = view.findViewById(R.id.TheSecondDawnSwitch1);

        ledSwitch = view.findViewById(R.id.TheSecondDawnSwitch2);

        fanSwitch = view.findViewById(R.id.TheSecondDawnSwitch3);

        voiceSwitch = view.findViewById(R.id.TheSecondDawnSwitch4);

        return view;
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
}