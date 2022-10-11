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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private EditText editTextUsername, editTextDate, editTextEmail, editTextPassword;
    private Button registerUser;
    private ProgressBar progressBar;

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

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

        if (username.isEmpty()) {
            editTextUsername.setError(getString(R.string.emptyUserError1));
            editTextUsername.requestFocus();
            return;
        }

        if (username.length() < 6) {
            editTextUsername.setError(getString(R.string.lengthUserError1));
            editTextUsername.requestFocus();
            return;
        }

        if (date.isEmpty()) {
            editTextDate.setError(getString(R.string.emptyDateError1));
            editTextDate.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.emptyEmailError2));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.matchEmailError2));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.emptyPasswordError2));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.lengthPasswordError2));
            editTextPassword.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                UserClass user = new UserClass(username, date, email);

                FirebaseDatabase.getInstance().getReference(getString(R.string.firebaseReference)).child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(user).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, R.string.toastMessage12, Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
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