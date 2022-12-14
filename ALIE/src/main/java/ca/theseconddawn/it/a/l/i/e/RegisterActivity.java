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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextFullName, editTextEmail, editTextPassword, editTextConfirmPassword, editTextPhoneNumber;
    private Button registerUser;
    private ProgressBar progressBar;
    private ImageButton leftArrow;

    private FirebaseAuth mAuth;
    private final String accountKey = "/Account Information";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerUser = findViewById(R.id.TheSecondDawnButton2);
        registerUser.setOnClickListener(this);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton1);
        leftArrow.setOnClickListener(this);

        editTextFullName = findViewById(R.id.TheSecondDawnEditText11);
        editTextEmail = findViewById(R.id.TheSecondDawnEditText3);
        editTextPassword = findViewById(R.id.TheSecondDawnEditText4);
        editTextConfirmPassword = findViewById(R.id.TheSecondDawnEditText5);
        editTextPhoneNumber = findViewById(R.id.TheSecondDawnEditText12);

        progressBar = findViewById(R.id.TheSecondDawnProgressBar2);
    }

    //sets clicking properties of what items are clicked
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton1) {
            finish();
        }
        if (view.getId() == R.id.TheSecondDawnButton2) {
            registerUser();
        }
    }

    //used for registering the user
    private void registerUser() {
        String name = editTextFullName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String phone = editTextPhoneNumber.getText().toString().trim();
        //to check if edit text of the name is empty if so will notify user of error
        if (name.isEmpty()) {
            editTextFullName.setError(getString(R.string.emptyNameError2));
            editTextFullName.requestFocus();
            return;
        }
        //to check if edit text of the email is empty if so will notify user of error
        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.emptyEmailError2));
            editTextEmail.requestFocus();
            return;
        }
        //to notify user if email entered is not valid
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.matchEmailError2));
            editTextEmail.requestFocus();
            return;
        }
        //to check if edit text of the password is empty if so will notify user of error
        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.emptyPasswordError2));
            editTextPassword.requestFocus();
            return;
        }
        //if password is lower then 6 values error
        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.lengthPasswordError2));
            editTextPassword.requestFocus();
            return;
        }
        //if password does not contain both text and numbers error
        if (!password.matches(".*[a-zA-Z0-9].*")) {
            editTextPassword.setError(getString(R.string.matchPasswordErrorAlphabetNum));
            editTextPassword.requestFocus();
            return;
        }
        //if password does not contain one special minimum error occurs
        if (!password.matches(".*[!@#$%^&*+=].*")) {
            editTextPassword.setError(getString(R.string.matchPasswordError1Char));
            editTextPassword.requestFocus();
            return;
        }
        //if password does not contain one uppercase letter error occurs
        if (!password.matches(".*[A-Z].*")) {
            editTextPassword.setError(getString(R.string.matchPasswordError1UpperLetter));
            editTextPassword.requestFocus();
            return;
        }
        //if password does not contain at least one digit error occurs
        if (!password.matches(".*[0-9].*")) {
            editTextPassword.setError(getString(R.string.matchPasswordError1Digit));
            editTextPassword.requestFocus();
            return;
        }
        //if passwords do not match each other error occurs
        if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            editTextConfirmPassword.setError(getString(R.string.confirmPasswordError1));
            editTextConfirmPassword.requestFocus();
            return;
        }
        //if user has not entered phone number error occurs
        if (phone.isEmpty()) {
            editTextPhoneNumber.setError(getString(R.string.emptyPhoneError2));
            editTextPhoneNumber.requestFocus();
            return;
        }
        //if users phone number does not contain 10 values error occurs
        if (phone.length() < 10) {
            editTextPhoneNumber.setError(getString(R.string.lengthPhoneError2));
            editTextPhoneNumber.requestFocus();
            return;
        }
        //not entering a valid phone receives a error
        if (!Patterns.PHONE.matcher(phone).matches()) {
            editTextPhoneNumber.setError(getString(R.string.matchPhoneError2));
            editTextPhoneNumber.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //creates firebase data with users email and password
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                UserClass user = new UserClass(name, email, phone);

                //create firebase data structure called users
                FirebaseDatabase.getInstance().getReference(getString(R.string.firebaseReference1)).child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid() + accountKey).setValue(user).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {

                        //if users registers successfully user will be notified of it
                        Toast.makeText(RegisterActivity.this, R.string.toastMessage12, Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, R.string.toastMessage13, Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            } else {
                Toast.makeText(RegisterActivity.this, R.string.toastMessage14, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}