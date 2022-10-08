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

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private Button registerUser;
    private EditText editTextUsername, editTextDate, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        title = findViewById(R.id.TheSecondDawnTextView7);
        title.setOnClickListener(this);

        registerUser = findViewById(R.id.TheSecondDawnButton2);
        registerUser.setOnClickListener(this);

        editTextUsername = findViewById(R.id.TheSecondDawnEditText3);
        editTextDate = findViewById(R.id.TheSecondDawnEditText4);
        editTextEmail = findViewById(R.id.TheSecondDawnEditText5);
        editTextPassword = findViewById(R.id.TheSecondDawnEditText6);

        progressBar = findViewById(R.id.TheSecondDawnProgressBar2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TheSecondDawnTextView7:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.TheSecondDawnButton2:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(username.isEmpty()) {
            editTextUsername.setError("Username is Required!");
            editTextUsername.requestFocus();
            return;
        }

        if(username.length() < 6) {
            editTextUsername.setError("Your Username Should be at Least 6 Characters Long!");
            editTextUsername.requestFocus();
            return;
        }

        if(date.isEmpty()) {
            editTextDate.setError("Date of Birth is Required!");
            editTextDate.requestFocus();
            return;
        }

        if(email.isEmpty()) {
            editTextEmail.setError("Email Address is Required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please Enter a Valid Email Address:");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextPassword.setError("Password is Required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTextPassword.setError("Your Password Should be at Least 6 Characters Long!");
            editTextPassword.requestFocus();
        }
    }
}