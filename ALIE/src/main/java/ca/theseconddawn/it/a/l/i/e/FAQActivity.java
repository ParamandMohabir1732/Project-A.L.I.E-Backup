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
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView messageButton;
    private ImageButton leftArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton12);
        leftArrow.setOnClickListener(this);

        // initialize imageView
        // with method findViewById()
        messageButton = findViewById(R.id.TheSecondDawnImageView52);

        // Apply OnClickListener  to imageView to
        // switch from one activity to another
        messageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton12) {
            finish();
        }
        if (view.getId() == R.id.TheSecondDawnImageView52) {
            Intent intent = new Intent(FAQActivity.this, ContactUsActivity.class);
            startActivity(intent);
        }
    }
}

