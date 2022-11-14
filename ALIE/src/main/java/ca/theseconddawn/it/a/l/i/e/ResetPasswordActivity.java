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

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private Button reset;
    private ProgressBar progressBar;
    private ImageButton leftArrow;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.TheSecondDawnEditText6);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton2);
        leftArrow.setOnClickListener(this);

        reset = findViewById(R.id.TheSecondDawnButton6);
        reset.setOnClickListener(this);

        progressBar = findViewById(R.id.TheSecondDawnProgressBar4);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnButton6) {
            resetPassword();
        }
        if (view.getId() == R.id.TheSecondDawnImageButton2) {
            finish();
        }
    }

    private void resetPassword() {
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.emptyEmailError3));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.matchEmailError3));
            editTextEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, R.string.toastMessage15, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                finish();
            } else {
                Toast.makeText(this, R.string.toastMessage16, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}