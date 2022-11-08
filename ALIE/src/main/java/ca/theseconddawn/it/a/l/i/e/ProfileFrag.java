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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFrag extends Fragment {

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;

    private String userID;
    private Button signOut, reset;
    private ProgressBar progressBar;
    private TextView emailTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();

        reset = view.findViewById(R.id.TheSecondDawnButton4);
        reset.setOnClickListener(this::onClick);

        signOut = view.findViewById(R.id.TheSecondDawnButton5);
        signOut.setOnClickListener(this::onClick);

        emailTextView = view.findViewById(R.id.TheSecondDawnTextView15);

        progressBar = view.findViewById(R.id.TheSecondDawnProgressBar3);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserClass userProfile = snapshot.getValue(UserClass.class);

                if (userProfile != null) {
                    String email = userProfile.email;

                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Failed to Retrieve!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TheSecondDawnButton4:
                resetPassword();
                break;
            case R.id.TheSecondDawnButton5:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    private void resetPassword() {
        String email = emailTextView.getText().toString().trim();

        if (email.isEmpty()) {
            emailTextView.setError("Email Address is Required!");
            emailTextView.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextView.setError("Please Enter a Valid Email Address:");
            emailTextView.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getActivity(), "Please Check Your Email to Reset your Password!", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            } else {
                Toast.makeText(getActivity(), "An Error has Occurred, Please Try Again!", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}