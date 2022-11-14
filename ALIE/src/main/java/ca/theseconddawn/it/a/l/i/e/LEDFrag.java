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
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import yuku.ambilwarna.AmbilWarnaDialog;

public class LEDFrag extends Fragment {

    private final int ACCESS_LOCATION_CODE = 1;

    private LinearLayout LEDLayout;
    private int LEDDefaultColor;
    private Button LEDButton, buttonRequest;
    private AmbilWarnaDialog colorPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_l_e_d, container, false);

        LEDLayout = view.findViewById(R.id.TheSecondDawnLEDLayout);

        LEDDefaultColor = ContextCompat.getColor(getActivity(), com.google.android.material.R.color.design_default_color_on_primary);

        LEDButton = view.findViewById(R.id.TheSecondDawnButton7);
        LEDButton.setOnClickListener(view1 -> openColorPicker());

        buttonRequest = view.findViewById(R.id.TheSecondDawnButton8);
        buttonRequest.setOnClickListener(view12 -> {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Snackbar snackbar = Snackbar.make(requireView(), R.string.snackbar3, Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(getResources().getColor(R.color.brightgreen));
                snackbar.setTextColor(getResources().getColor(R.color.black));
                snackbar.show();
            } else {
                requestInternetPermission();
            }
        });
        return view;
    }

    private void requestInternetPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.builder3Title);
            builder.setMessage(R.string.builder3Message);
            builder.setPositiveButton(R.string.builder3PositiveButton, (dialog, which) -> ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_CODE));
            builder.setNegativeButton(R.string.builder3NegativeButton, (dialog, which) -> dialog.dismiss()).create().show();
            Snackbar snackbar = Snackbar.make(requireView(), R.string.snackbar4, Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(getResources().getColor(R.color.brightred));
            snackbar.setTextColor(getResources().getColor(R.color.black));
            snackbar.show();
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACCESS_LOCATION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), R.string.toastMessage17, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), R.string.toastMessage18, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openColorPicker() {
        colorPicker = new AmbilWarnaDialog(getActivity(), LEDDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                LEDDefaultColor = color;
                LEDLayout.setBackgroundColor(LEDDefaultColor);
            }
        });
        colorPicker.show();
    }
}