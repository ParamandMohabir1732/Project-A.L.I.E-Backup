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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Button btn = (Button) findViewById(R.id.appCompatButton);
        btn.setOnClickListener(this);

        ImageButton contactUs = findViewById(R.id.imageView33);
        contactUs.setOnClickListener(this);

        ImageButton AboutUs = findViewById(R.id.imageView34);
        AboutUs.setOnClickListener(this);

        ImageButton FAQ = findViewById(R.id.imageView36);
        FAQ.setOnClickListener(this);



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

    }
}






