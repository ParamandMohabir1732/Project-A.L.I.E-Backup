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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private ImageView googleImage;
    private EditText editTextEmail, editTextPassword;
    private CheckBox rememberMeCheckBox;
    private Button signIn;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private GoogleSignInOptions mOptions;
    private GoogleSignInClient mClient;
    private final static int RC_SIGN_IN = 1;
    private static final String FILE_EMAIL = "Remember Me";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.TheSecondDawnTextView5);
        register.setOnClickListener(this);

        signIn = findViewById(R.id.TheSecondDawnButton1);
        signIn.setOnClickListener(this);

        googleImage = findViewById(R.id.TheSecondDawnImageView29);
        googleImage.setOnClickListener(this);

        editTextEmail = findViewById(R.id.TheSecondDawnEditText1);
        editTextPassword = findViewById(R.id.TheSecondDawnEditText2);

        rememberMeCheckBox = findViewById(R.id.TheSecondDawnCheckBox);
        sharedPreferences = getSharedPreferences(FILE_EMAIL, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String email = sharedPreferences.getString("svEmail", "");
        String password = sharedPreferences.getString("svPassword", "");

        rememberMeCheckBox.setChecked(sharedPreferences.contains("checked") && sharedPreferences.getBoolean("checked", false));
        editTextEmail.setText(email);
        editTextPassword.setText(password);

        progressBar = findViewById(R.id.TheSecondDawnProgressBar1);

        createRequest();
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
            case R.id.TheSecondDawnImageView29:
                googleSignIn();
                break;
        }
    }

    private void createRequest() {
        mOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mClient = GoogleSignIn.getClient(this, mOptions);
    }

    private void googleSignIn() {
        Intent googleSignIn = mClient.getSignInIntent();
        startActivityForResult(googleSignIn, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                googleAuth(account);
            } catch (ApiException e) {
                Toast.makeText(this, "Sign In Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void googleAuth(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
            } else {
                Toast.makeText(LoginActivity.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (rememberMeCheckBox.isChecked()) {
            editor.putBoolean("checked", true);
            editor.apply();
            StoreDataUsingSharedPref(email, password);

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
        } else {
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
            else {
                getSharedPreferences(FILE_EMAIL, MODE_PRIVATE).edit().clear().apply();
            }
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

    private void StoreDataUsingSharedPref(String email, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(FILE_EMAIL, MODE_PRIVATE).edit();
        editor.putString("svEmail", email);
        editor.putString("svPassword", password);
        editor.apply();
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