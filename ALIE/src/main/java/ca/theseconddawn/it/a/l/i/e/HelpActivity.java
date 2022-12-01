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
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton leftArrow;
    private Button callUs;
    private ImageButton contactUs, aboutUs, faq;

    private SwitchCompat switchCompat;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String NIGHT = "Night Mode";
    private static final String NIGHT_PREF = "Night Pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton15);
        leftArrow.setOnClickListener(this);

        callUs = findViewById(R.id.appCompatButton);
        callUs.setOnClickListener(this);

        contactUs = findViewById(R.id.imageView33);
        contactUs.setOnClickListener(this);

        aboutUs = findViewById(R.id.imageView34);
        aboutUs.setOnClickListener(this);

        faq = findViewById(R.id.imageView36);
        faq.setOnClickListener(this);

        switchCompat = findViewById(R.id.nightmode_switch);
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editor = getSharedPreferences(NIGHT, MODE_PRIVATE).edit();
                editor.putBoolean(NIGHT_PREF, true);
                editor.apply();

                switchCompat.setChecked(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                editor = getSharedPreferences(NIGHT, MODE_PRIVATE).edit();
                editor.putBoolean(NIGHT_PREF, false);
                editor.apply();

                switchCompat.setChecked(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        sharedPreferences = getSharedPreferences(NIGHT, MODE_PRIVATE);
        switchCompat.setChecked(sharedPreferences.getBoolean(NIGHT_PREF, false));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.appCompatButton) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(getString(R.string.intentPhoneUri)));
            startActivity(intent);
        }
        if (view.getId() == R.id.imageView33) {
            startActivity(new Intent(this, ContactUsActivity.class));
        }
        if (view.getId() == R.id.imageView34) {
            startActivity(new Intent(this, AboutUsActivity.class));
        }
        if (view.getId() == R.id.imageView36) {
            startActivity(new Intent(this, FAQActivity.class));
        }
        if (view.getId() == R.id.TheSecondDawnImageButton15) {
            finish();
        }
    }
}