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

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class HomeFrag extends Fragment implements View.OnClickListener {

    public TextView voiceInput;
    private ImageButton voiceButton, speakerButton, ledButton, fanButton;
    private SwitchCompat speakerSwitch, ledSwitch, fanSwitch, voiceSwitch;
    private FloatingActionButton voiceInputButton;

    private final String userID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    private final String userKey = "Users/";

    private final String speakerKey = "/Speaker Status";
    private final String ledKey = "/LED Status";
    private final String fanKey = "/Fan Status";
    private final String voiceKey = "/Voice Status";

    private String speakerDB;
    private String ledDB;
    private String fanDB;
    private String voiceDB;

    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    private static final int RESULT_OK = -1;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

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

        ledSwitch = view.findViewById(R.id.TheSecondDawnSwitch2);
        ledSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> ledControl(isChecked));

        fanSwitch = view.findViewById(R.id.TheSecondDawnSwitch3);
        fanSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> fanControl(isChecked));

        voiceSwitch = view.findViewById(R.id.TheSecondDawnSwitch4);
        voiceSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> voiceControl(isChecked));

        readDatabase();

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton5) {
            SpeakerFrag speakerFrag = new SpeakerFrag();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.TheSecondDawnDrawerLayout, speakerFrag, speakerFrag.getTag()).commit();
        }

        if (view.getId() == R.id.TheSecondDawnFloatingButton1) {
            //Calling Voice Input Method upon onClick on Fab Button
            setVoiceInput();
        }
    }

    private void readDatabase() {
        databaseReference = firebaseDatabase.getReference().child(userKey + userID + speakerKey);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    speakerDB = Objects.requireNonNull(snapshot.getValue()).toString();

                    if (speakerDB.equals("ON")) {
                        speakerSwitch.setChecked(true);
                    } else if (speakerDB.equals("OFF")) {
                        speakerSwitch.setChecked(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference = firebaseDatabase.getReference().child(userKey + userID + ledKey);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ledDB = Objects.requireNonNull(snapshot.getValue()).toString();

                    if (ledDB.equals("ON")) {
                        ledSwitch.setChecked(true);
                    } else if (ledDB.equals("OFF")) {
                        ledSwitch.setChecked(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference = firebaseDatabase.getReference().child(userKey + userID + fanKey);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    fanDB = Objects.requireNonNull(snapshot.getValue()).toString();

                    if (fanDB.equals("ON")) {
                        fanSwitch.setChecked(true);
                    } else if (fanDB.equals("OFF")) {
                        fanSwitch.setChecked(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference = firebaseDatabase.getReference().child(userKey + userID + voiceKey);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    voiceDB = Objects.requireNonNull(snapshot.getValue()).toString();

                    if (voiceDB.equals("ON")) {
                        voiceSwitch.setChecked(true);
                    } else if (voiceDB.equals("OFF")) {
                        voiceSwitch.setChecked(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setVoiceInput() {
        //Start the Intent of the Voice Recognize Speech
        Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //Prompt the User to say a Command
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
                //Display Results in VoiceInput Text view
                voiceInput.setText(result.get(0));
            }
        }
    }

    //Refactored Speaker Control from OnCreate Method to its own Method speakerControl()
    private void speakerControl(boolean isChecked) {

        //If Speaker Control Switch is Checked On, Change the Text to "ON" and the color to Green.
        if (isChecked) {
            databaseReference = firebaseDatabase.getReference().child(userKey + userID + speakerKey);
            databaseReference.setValue("ON");

            speakerSwitch.setChecked(true);
            speakerSwitch.setText(R.string.speakerControlText1);
            //speakerSwitch.setTextColor(getActivity().getResources().getColor(R.color.brightgreen));
            speakerButton.setImageResource(R.drawable.speaker_button_on);

            //If Speaker Control Switch is Checked Off, Change the Text to "OFF" and the color to Red.
        } else {
            databaseReference = firebaseDatabase.getReference().child(userKey + userID + speakerKey);
            databaseReference.setValue("OFF");

            speakerSwitch.setChecked(false);
            speakerSwitch.setText(R.string.speakerControlText2);
            //speakerSwitch.setTextColor(getActivity().getResources().getColor(R.color.brightred));
            speakerButton.setImageResource(R.drawable.speaker_button_off);
        }
    }

    //Refactored LED Control from OnCreate Method to its own Method ledControl()
    private void ledControl(boolean isChecked) {
        //If LED Control Switch is Checked On, Change the Text to "ON" and the color to Green.
        if (isChecked) {
            databaseReference = firebaseDatabase.getReference().child(userKey + userID + ledKey);
            databaseReference.setValue("ON");

            ledSwitch.setChecked(true);
            ledSwitch.setText(R.string.ledControlText1);
            //ledSwitch.setTextColor(getResources().getColor(R.color.brightgreen));
            ledButton.setImageResource(R.drawable.led_button_on);
        } else {
            //If LED Control Switch is Checked Off, Change the Text to "OFF" and the color to Red.
            databaseReference = firebaseDatabase.getReference().child(userKey + userID + ledKey);
            databaseReference.setValue("OFF");

            ledSwitch.setChecked(false);
            ledSwitch.setText(R.string.ledControlText2);
            //ledSwitch.setTextColor(getResources().getColor(R.color.brightred));
            ledButton.setImageResource(R.drawable.led_button_off);
        }
    }

    //Refactored Fan Control from OnCreate Method to its own Method fanControl()
    private void fanControl(boolean isChecked) {
        //If Fan Control Switch is Checked On, Change the Text to "ON" and the color to Green.
        if (isChecked) {
            databaseReference = firebaseDatabase.getReference().child(userKey + userID + fanKey);
            databaseReference.setValue("ON");

            fanSwitch.setChecked(true);
            fanSwitch.setText(R.string.fanControlText3);
            //fanSwitch.setTextColor(getResources().getColor(R.color.brightgreen));
            fanButton.setImageResource(R.drawable.fan_button_on);
            //If Fan Control Switch is Checked Off, Change the Text to "OFF" and the color to Red.
        } else {
            databaseReference = firebaseDatabase.getReference().child(userKey + userID + fanKey);
            databaseReference.setValue("OFF");

            fanSwitch.setChecked(false);
            fanSwitch.setText(R.string.fanControlText4);
            //fanSwitch.setTextColor(getResources().getColor(R.color.brightred));
            fanButton.setImageResource(R.drawable.fan_button_off);
        }
    }

    //Refactored Voice Control from OnCreate Method to its own Method voiceControl()
    private void voiceControl(boolean isChecked) {
        //If Voice Control Switch is Checked On, Change the Text to "ON" and the color to Green.
        if (isChecked) {
            databaseReference = firebaseDatabase.getReference().child(userKey + userID + voiceKey);
            databaseReference.setValue("ON");

            voiceSwitch.setChecked(true);
            voiceSwitch.setText(R.string.voiceControlText1);
            //voiceSwitch.setTextColor(getResources().getColor(R.color.brightgreen));
            voiceButton.setImageResource(R.drawable.voice_button_on);
        } else {
            //If Voice Control Switch is Checked Off, Change the Text to "OFF" and the color to Red.;
            databaseReference = firebaseDatabase.getReference().child(userKey + userID + voiceKey);
            databaseReference.setValue("OFF");

            voiceSwitch.setChecked(false);
            voiceSwitch.setText(R.string.voiceControlText2);
            //voiceSwitch.setTextColor(getResources().getColor(R.color.brightred));
            voiceButton.setImageResource(R.drawable.voice_button_off);
        }
    }
}