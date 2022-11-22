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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton1) {
            finish();
        }
        if (view.getId() == R.id.TheSecondDawnButton2) {
            registerUser();
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

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
            return;
        }

        if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            editTextConfirmPassword.setError(getString(R.string.confirmPasswordError1));
            editTextConfirmPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                UserClass user = new UserClass(email);

                FirebaseDatabase.getInstance().getReference(getString(R.string.firebaseReference1)).child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(user).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
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