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

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
    private AlertDialog progressDialog;

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
        getPhoneModel();

        leftArrow = findViewById(R.id.TheSecondDawnImageButton10);
        leftArrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton10) {
            finish();
        }
        if (view.getId() == R.id.TheSecondDawnButton13) {
            registerReviewUser();
        }
    }

    private void registerReviewUser() {
        // UserReview Class Objects
        float rating = ratingBar.getRating();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhoneNumber.getText().toString();
        String device = deviceModel.getText().toString();
        String review = editTextMessage.getText().toString();

        // If Name Field is Empty
        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.emptyNameError1));
            editTextName.requestFocus();
            return;
        }

        // If Email Field is Empty
        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.emptyEmailError4));
            editTextEmail.requestFocus();
            return;
        }

        // If Email Pattern is Incorrect and Doesn't Match the Proper Format
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.matchEmailError4));
            editTextEmail.requestFocus();
            return;
        }

        // If Phone is Empty
        if (phone.isEmpty()) {
            editTextPhoneNumber.setError(getString(R.string.emptyPhoneError1));
            editTextPhoneNumber.requestFocus();
            return;
        }

        // If Phone Number is less than 10 Digits
        if (phone.length() < 10) {
            editTextPhoneNumber.setError(getString(R.string.lengthPhoneError1));
            editTextPhoneNumber.requestFocus();
            return;
        }

        // If Phone Pattern is Incorrect and Doesn't Match the Proper Format
        if (!Patterns.PHONE.matcher(phone).matches()) {
            editTextPhoneNumber.setError(getString(R.string.matchPhoneError1));
            editTextPhoneNumber.requestFocus();
            return;
        }

        // If Review Field is Empty
        if (review.isEmpty()) {
            editTextMessage.setError(getString(R.string.emptyReviewError1));
            editTextMessage.requestFocus();
            return;
        }

        // Starting the Progress Dialog
        startingProgressDialog();

        // Creating the Customer Review Reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(getString(R.string.firebaseReference3));

        // Storing User Data from Objects in UserReviewClass
        UserReviewClass reviewClass = new UserReviewClass(rating, name, email, phone, device, review);

        // Set Values in Database Reference "Customer Reviews"
        databaseReference.child(name).setValue(reviewClass);
    }

    private void startingProgressDialog() {
        LayoutInflater inflater = this.getLayoutInflater();

        // Creating a new Alert Dialog and Inflating it with the Custom Dialog XML file
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflater.inflate(R.layout.activity_custom_dialog, null));
        builder.setCancelable(true);

        // Creating the Builder and Displaying it
        progressDialog = builder.create();
        progressDialog.show();

        // Calling the Progress Handler to Begin the Delay
        progressHandler();
    }

    private void progressHandler() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissProgressDialog();
                Toast.makeText(getApplicationContext(), R.string.toastMessage34, Toast.LENGTH_SHORT).show();
            }
        }, 3000); // Setting the Delay Timer of the Progress Bar to 3 Seconds or 3000 Milliseconds
    }

    private void dismissProgressDialog() {
        // Dismiss Progress Dialog
        progressDialog.dismiss();
    }

    private void getPhoneModel() {
        // Get Phone Model Number and Store into Device Model Text View
        String modelText = Build.MODEL;
        deviceModel.setText(modelText);
    }
}