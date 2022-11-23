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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFrag extends Fragment implements View.OnClickListener {

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;

    private Uri imageUri;
    private String userID;
    private CircleImageView profileImage;
    private Button signOut, reset;
    private ProgressBar progressBar;
    private TextView emailTextView, fullNameTextView, changeProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();

        reset = view.findViewById(R.id.TheSecondDawnButton4);
        reset.setOnClickListener(this);

        signOut = view.findViewById(R.id.TheSecondDawnButton5);
        signOut.setOnClickListener(this);

        profileImage = view.findViewById(R.id.TheSecondDawnImageView31);

        emailTextView = view.findViewById(R.id.TheSecondDawnTextView15);
        fullNameTextView = view.findViewById(R.id.TheSecondDawnTextView30);

        changeProfile = view.findViewById(R.id.TheSecondDawnTextView18);
        changeProfile.setOnClickListener(this);

        progressBar = view.findViewById(R.id.TheSecondDawnProgressBar3);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference(getString(R.string.firebaseReference2));
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserClass userProfile = snapshot.getValue(UserClass.class);

                if (userProfile != null) {
                    String name = userProfile.name;
                    String email = userProfile.email;

                    fullNameTextView.setText(name);
                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), R.string.toastMessage31, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnButton4) {
            resetPassword();
        }
        if (view.getId() == R.id.TheSecondDawnButton5) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        if (view.getId() == R.id.TheSecondDawnTextView18) {
            Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGallery, 1000);
        }
    }

    private void resetPassword() {
        String email = emailTextView.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getActivity(), R.string.toastMessage32, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            } else {
                Toast.makeText(getActivity(), R.string.toastMessage33, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                imageUri = data.getData();
                profileImage.setImageURI(imageUri);
            }
        }
    }
}
