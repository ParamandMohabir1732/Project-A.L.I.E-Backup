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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton leftArrow;
    private Button callUs;
    private ImageButton contactUs, aboutUs, faq;
    SwitchCompat switchCompat;
    SharedPreferences sharedPreferences = null;



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

        switchCompat = findViewById(R.id.nightmode_switch);
        sharedPreferences = getSharedPreferences("night", 0);
        Boolean booleanValue = sharedPreferences.getBoolean("night_mode", true);
        if (booleanValue){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switchCompat.setChecked(false);
    }
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switchCompat.setChecked(true);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",true);
                    editor.apply();
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switchCompat.setChecked(false);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",false);
                    editor.apply();

                }
            }
        });

    }
}







