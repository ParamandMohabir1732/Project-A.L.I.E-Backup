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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.TheSecondDawnTextView5);
        register.setOnClickListener(this);

        signIn = findViewById(R.id.TheSecondDawnButton1);
        signIn.setOnClickListener(this);

        editTextEmail = findViewById(R.id.TheSecondDawnEditText1);
        editTextPassword = findViewById(R.id.TheSecondDawnEditText2);

        progressBar = findViewById(R.id.TheSecondDawnProgressBar1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TheSecondDawnTextView5:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.TheSecondDawnButton1:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email Address is Required!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please Enter a Valid Email Address:");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is Required!");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Your Password Should be at Least 6 Characters Long!");
            editTextPassword.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                assert user != null;
                if (user.isEmailVerified()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    progressBar.setVisibility(View.GONE);
                } else {
                    user.sendEmailVerification();
                    Toast.makeText(LoginActivity.this, "Please Check Your Email to Verify Your Account!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            } else {
                Toast.makeText(LoginActivity.this, "Failed to Login! Please Check your Credentials!", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}