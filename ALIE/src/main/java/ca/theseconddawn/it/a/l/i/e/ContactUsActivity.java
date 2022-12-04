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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton leftArrow;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText editTextName, editTextEmail, editTextNumber, editTextMessage;
    private Button contactUsBtn;
    private AlertDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton13);
        leftArrow.setOnClickListener(this);
        editTextName = findViewById(R.id.TheSecondDawnEditText13);
        editTextEmail = findViewById(R.id.TheSecondDawnEditText14);
        editTextNumber = findViewById(R.id.TheSecondDawnEditText15);
        editTextMessage= findViewById(R.id.TheSecondDawnEditText16);
        contactUsBtn = findViewById(R.id.TheSecondDawnButton17);

        contactUsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton13) {
            finish();
        }
        if (view.getId() == R.id.TheSecondDawnButton17) {
            contactUsUser();
        }
    }
    private void contactUsUser() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String number = editTextNumber.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

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
        if (number.isEmpty()) {
            editTextNumber.setError(getString(R.string.emptyPhoneError1));
            editTextNumber.requestFocus();
            return;
        }

        // If Phone Number is less than 10 Digits
        if (number.length() < 10) {
            editTextNumber.setError(getString(R.string.lengthPhoneError1));
            editTextNumber.requestFocus();
            return;
        }

        // If Phone Pattern is Incorrect and Doesn't Match the Proper Format
        if (!Patterns.PHONE.matcher(number).matches()) {
            editTextNumber.setError(getString(R.string.matchPhoneError1));
            editTextNumber.requestFocus();
            return;
        }

        // If Message Field is Empty
        if (message.isEmpty()) {
            editTextMessage.setError(getString(R.string.emptyMessageError1));
            editTextMessage.requestFocus();
            return;
        }

        // Starting the Progress Dialog
        startingProgressDialog();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Customer Messages");

        UserContactUsClass userContactUsClass = new UserContactUsClass(name, email, number, message);
        databaseReference.child(name).setValue(userContactUsClass);
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
                Toast.makeText(getApplicationContext(), "Your Message has Been Sent to The Second Dawn! Thank You!", Toast.LENGTH_SHORT).show();
            }
        }, 3000); // Setting the Delay Timer of the Progress Bar to 3 Seconds or 3000 Milliseconds
    }

    private void dismissProgressDialog() {
        // Dismiss Progress Dialog
        progressDialog.dismiss();
    }
}