package ca.theseconddawn.it.a.l.i.e;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;
    private ImageButton leftArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton12);
        leftArrow.setOnClickListener(this);

        // initialize imageView
        // with method findViewById()
        imageView = findViewById(R.id.imageView4);

        // Apply OnClickListener  to imageView to
        // switch from one activity to another
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(FAQActivity.this, ContactUsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton12) {
            finish();
        }
    }
}

