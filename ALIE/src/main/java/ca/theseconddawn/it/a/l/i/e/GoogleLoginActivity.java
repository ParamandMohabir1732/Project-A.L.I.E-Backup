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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

public class GoogleLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private GoogleSignInOptions mOptions;
    private GoogleSignInClient mClient;
    private ImageView googleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        googleImage = findViewById(R.id.TheSecondDawnImageView29);
        googleImage.setOnClickListener(this);

        createRequest();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageView29) {
            googleSignIn();
        }
    }

    public void createRequest() {
        mOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mClient = GoogleSignIn.getClient(this, mOptions);
    }

    public void googleSignIn() {
        Intent googleSignIn = mClient.getSignInIntent();
        startActivityForResult(googleSignIn, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                googleFirebaseAuth(account);
            } catch (ApiException e) {
                Toast.makeText(this, "Sign In Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void googleFirebaseAuth(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                enterMainActivity();
            } else {
                Toast.makeText(this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void enterMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
