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

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SpeakerFrag extends Fragment {

    private Button mediaBtn;
    private SeekBar seekBar;
    private TextView textView;
    private TextView textViewOne;
    private ImageButton openFileBtn;

    private SwitchCompat aSwitch;
    String duration;
    private MediaPlayer mediaPlayer;
    ScheduledExecutorService timer;
    public static final int PICK_FILE =99;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker, container, false);

        mediaBtn = view.findViewById(R.id.TheSecondDawnButton14);
        seekBar = view.findViewById(R.id.TheSecondDawnSeekBar1);
        textView = view.findViewById(R.id.TheSecondDawnTextView60);
        textViewOne = view.findViewById(R.id.TheSecondDawnTextView61);
        aSwitch = view.findViewById(R.id.TheSecondDawnSwitch7);
        openFileBtn = view.findViewById(R.id.TheSecondDawnImageButton11);

        aSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Toast.makeText(getActivity(), R.string.toastMessage29, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), R.string.toastMessage30, Toast.LENGTH_SHORT).show();
            }
        });

        openFileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("audio/*");
            startActivityForResult(intent, PICK_FILE);
        });

        mediaBtn.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    mediaBtn.setText(R.string.mediaBtn1);
                    mediaBtn.setBackgroundColor(getResources().getColor(R.color.brightgreen));
                    timer.shutdown();
                } else {
                    mediaPlayer.start();
                    mediaBtn.setText(R.string.mediaBtn2);
                    mediaBtn.setBackgroundColor(getResources().getColor(R.color.brightred));

                    timer = Executors.newScheduledThreadPool(1);
                    timer.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            if (mediaPlayer != null) {
                                if (!seekBar.isPressed()) {
                                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                }
                            }
                        }
                    }, 10, 10, TimeUnit.MILLISECONDS);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null){
                    int millis = mediaPlayer.getCurrentPosition();
                    long total_secs = TimeUnit.SECONDS.convert(millis, TimeUnit.MILLISECONDS);
                    long mins = TimeUnit.MINUTES.convert(total_secs, TimeUnit.SECONDS);
                    long secs = total_secs - (mins*60);
                    textViewOne.setText(mins + ":" + secs + " / " + duration);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });

        mediaBtn.setEnabled(false);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE && resultCode == RESULT_OK){
            if (data != null){
                Uri uri = data.getData();
                createMediaPlayer(uri);
            }
        }
    }

    public void createMediaPlayer(Uri uri){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(getActivity(), uri);
            mediaPlayer.prepare();

            textView.setText(getNameFromUri(uri));
            mediaBtn.setEnabled(true);

            int millis = mediaPlayer.getDuration();
            long total_secs = TimeUnit.SECONDS.convert(millis, TimeUnit.MILLISECONDS);
            long mins = TimeUnit.MINUTES.convert(total_secs, TimeUnit.SECONDS);
            long secs = total_secs - (mins*60);
            duration = mins + ":" + secs;
            textViewOne.setText("00:00 / " + duration);
            seekBar.setMax(millis);
            seekBar.setProgress(0);

            mediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());
        } catch (IOException e){
            textView.setText(e.toString());
        }
    }

    @SuppressLint("Range")
    public String getNameFromUri(Uri uri){
        String fileName = "";
        Cursor cursor = null;
        cursor = getActivity().getContentResolver().query(uri, new String[]{
                MediaStore.Images.ImageColumns.DISPLAY_NAME
        }, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));
        }
        if (cursor != null) {
            cursor.close();
        }
        return fileName;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer(){
        if (timer != null) {
            timer.shutdown();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaBtn.setEnabled(false);
        textView.setText(R.string.mediaTitle1);
        textViewOne.setText(R.string.medaTitle2);
        seekBar.setMax(100);
        seekBar.setProgress(0);
    }

}



