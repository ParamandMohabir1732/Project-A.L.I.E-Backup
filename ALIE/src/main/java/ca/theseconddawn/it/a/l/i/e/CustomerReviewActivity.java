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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton leftArrow;
    private TextView deviceModel;
    private RatingBar ratingBar;
    private EditText editTextName, editTextEmail, editTextPhoneNumber, editTextMessage;
    private Button submitReview;

    private FirebaseDatabase rootNode;
    private DatabaseReference dataReference;

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review);

        mAuth = FirebaseAuth.getInstance();

        ratingBar = findViewById(R.id.TheSecondDawnRatingBar);
        editTextName = findViewById(R.id.TheSecondDawnEditText7);
        editTextEmail = findViewById(R.id.TheSecondDawnEditText8);
        editTextPhoneNumber = findViewById(R.id.TheSecondDawnEditText9);
        editTextMessage = findViewById(R.id.TheSecondDawnEditText10);

        submitReview = findViewById(R.id.TheSecondDawnButton13);
        submitReview.setOnClickListener(this);

        deviceModel = findViewById(R.id.TheSecondDawnTextView28);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton10);
        leftArrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton10) {
            finish();
        }
        if(view.getId() == R.id.TheSecondDawnButton13) {

        }
    }
}