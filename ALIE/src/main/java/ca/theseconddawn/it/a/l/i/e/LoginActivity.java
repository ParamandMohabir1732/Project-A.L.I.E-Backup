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

import androidx.appcompat.app.AlertDialog;
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
            editTextEmail.setError(getString(R.string.emptyEmailError1));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.matchEmailError1));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.emptyPasswordError1));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.lengthPasswordError1));
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                assert user != null;
                if (user.isEmailVerified()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, R.string.toastMessage9, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                } else {
                    user.sendEmailVerification();
                    Toast.makeText(LoginActivity.this, R.string.toastMessage10, Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            } else {
                Toast.makeText(LoginActivity.this, R.string.toastMessage11, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(R.string.builderTitle);
        builder.setMessage(R.string.builderMessage);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setCancelable(true);
        builder.setNegativeButton(R.string.builderNegativeButton, ((dialogInterface, i) -> dialogInterface.cancel()));
        builder.setPositiveButton(R.string.builderPositiveButton, ((dialogInterface, i) -> finish()));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}