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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import yuku.ambilwarna.AmbilWarnaDialog;

public class LEDFrag extends Fragment {
    private final int NETWORK_PERMISSION_CODE = 1;
    ConstraintLayout LEDLayout;
    int LEDDefaultColor;
    Button LEDButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_l_e_d, container, false);

        LEDLayout = view.findViewById(R.id.Layout);
        LEDButton = view.findViewById(R.id.button);
        LEDDefaultColor = ContextCompat.getColor(getActivity(), com.google.android.material.R.color.design_default_color_on_primary);
        LEDButton.setOnClickListener(view1 -> openColorPicker());

        Button buttonRequest = view.findViewById(R.id.permissionsBtn);
        buttonRequest.setOnClickListener(view12 -> {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "you have already granted this permission! ", Toast.LENGTH_SHORT).show();
            } else {
                requestInternetPermission();
            }
        });
        return view;
    }


    private void requestInternetPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.BLUETOOTH_SCAN)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("A.L.I.E");
            builder.setMessage("This permission is needed to allow led control to access your wifi connection");
            builder.setPositiveButton("ok", (dialogInterface, which) -> ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_WIFI_STATE}, NETWORK_PERMISSION_CODE));
            builder.setNegativeButton("Cancel", (dialogInterface, which) -> dialogInterface.dismiss()).create().show();
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.BLUETOOTH_SCAN}, NETWORK_PERMISSION_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == NETWORK_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), LEDDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
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