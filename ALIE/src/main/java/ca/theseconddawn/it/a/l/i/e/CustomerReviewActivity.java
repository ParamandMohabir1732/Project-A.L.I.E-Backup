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
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton leftArrow;
    private TextView deviceModel;
    private RatingBar ratingBar;
    private EditText editTextName, editTextEmail, editTextPhoneNumber, editTextMessage;
    private Button submitReview;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review);

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
            registerReviewUser();
        }
    }

    private void registerReviewUser() {
        float rating = ratingBar.getRating();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhoneNumber.getText().toString();
        String review = editTextMessage.getText().toString();

        if (name.isEmpty()) {
            editTextName.setError("Your Name is Required!");
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email Address is Required!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please Enter a Valid Email Address!");
            editTextEmail.requestFocus();
            return;
        }


        if (phone.isEmpty()) {
            editTextPhoneNumber.setError("Your Phone Number is Required!");
            editTextPhoneNumber.requestFocus();
            return;
        }

        if (!Patterns.PHONE.matcher(phone).matches()) {
            editTextPhoneNumber.setError("Please Enter a Valid Phone Number!");
            editTextPhoneNumber.requestFocus();
            return;
        }

        if (review.isEmpty()) {
            editTextMessage.setError("Your Review is Required!");
            editTextMessage.requestFocus();
            return;
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(getString(R.string.firebaseReference3));

        UserReviewClass reviewClass = new UserReviewClass(rating, name, email, phone, review);

        databaseReference.child(name).setValue(reviewClass);
        Toast.makeText(this, R.string.toastMessage34, Toast.LENGTH_SHORT).show();
    }
}