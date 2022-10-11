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

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class SmartFanFrag extends Fragment {

    private Button requestPermission;
    private final int STORAGE_PERMISSION = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fan, container, false);

        requestPermission = view.findViewById(R.id.TheSecondDawnButton3);
        requestPermission.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Snackbar snackbar = Snackbar.make(requireView(), R.string.snackbar1, Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(getResources().getColor(R.color.brightgreen));
                snackbar.setTextColor(getResources().getColor(R.color.black));
                snackbar.show();
            } else {
                requestStoragePermission();
            }
        });
        return view;
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.builder2Title);
            builder.setMessage(R.string.builder2Message);
            builder.setPositiveButton(R.string.builder2PositiveButton, (dialog, which) -> ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION));
            builder.setNegativeButton(R.string.builder2NegativeButton, (dialog, which) -> dialog.dismiss()).create().show();
            Snackbar snackbar = Snackbar.make(requireView(), R.string.snackbar2, Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(getResources().getColor(R.color.brightred));
            snackbar.setTextColor(getResources().getColor(R.color.black));
            snackbar.show();
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), R.string.toastMessage7, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), R.string.toastMessage8, Toast.LENGTH_LONG).show();
            }
        }
    }
}
