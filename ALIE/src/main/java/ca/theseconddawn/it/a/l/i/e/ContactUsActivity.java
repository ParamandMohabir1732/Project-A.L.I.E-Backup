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

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton leftArrow;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText editTextName, editTextEmail, editTextNum, editTextMsg;
    private Button contactUsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton13);
        leftArrow.setOnClickListener(this);
        editTextName = findViewById(R.id.TheSecondDawnEditText13);
        editTextEmail = findViewById(R.id.TheSecondDawnEditText14);
        editTextNum = findViewById(R.id.TheSecondDawnEditText15);
        editTextMsg= findViewById(R.id.TheSecondDawnEditText16);
        contactUsBtn = findViewById(R.id.TheSecondDawnButton17);

        contactUsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton13) {
            finish();
        }
        if (view.getId() == R.id.TheSecondDawnButton17) {
            finish();
        }
    }
    private void contactUsUser(){
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String num = editTextNum.getText().toString().trim();
        String msg = editTextMsg.getText().toString().trim();

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
        if (num.isEmpty()) {
            editTextNum.setError(getString(R.string.emptyPhoneError1));
            editTextNum.requestFocus();
            return;
        }

        // If Phone Number is less than 10 Digits
        if (num.length() < 10) {
            editTextNum.setError(getString(R.string.lengthPhoneError1));
            editTextNum.requestFocus();
            return;
        }

        // If Phone Pattern is Incorrect and Doesn't Match the Proper Format
        if (!Patterns.PHONE.matcher(num).matches()) {
            editTextNum.setError(getString(R.string.matchPhoneError1));
            editTextNum.requestFocus();
            return;
        }

        // If Review Field is Empty
        if (msg.isEmpty()) {
            editTextMsg.setError(getString(R.string.emptyReviewError1));
            editTextMsg.requestFocus();
            return;
        }

    }
}