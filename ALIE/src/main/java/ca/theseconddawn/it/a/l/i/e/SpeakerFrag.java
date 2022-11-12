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
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SpeakerFrag extends Fragment {

    private Button playBtn, pauseBtn;

    private Switch aSwitch;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker, container, false);

        aSwitch = view.findViewById(R.id.switch1);
        playBtn = view.findViewById(R.id.playButton);
        pauseBtn = view.findViewById(R.id.pauseButton);

        aSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Toast.makeText(getActivity().getBaseContext(), "ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity().getBaseContext(), "OFF", Toast.LENGTH_SHORT).show();
            }
        });

        playBtn.setOnClickListener(view1 -> play());
        pauseBtn.setOnClickListener(view2 -> pause());

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sample);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBar = view.findViewById(R.id.volumeSeekbar);
        seekBar.setMax(maxVolume);
        seekBar.setProgress(currentVolume);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        return view;
    }

    public void play() {
        mediaPlayer.start();

    }

    public void pause() {
        mediaPlayer.pause();
    }

}
