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

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class LEDFrag extends Fragment {

    private final int ACCESS_LOCATION_CODE = 1;

    private LinearLayout LEDLayout;
    private int LEDDefaultColor;
    private ImageView ledImage;
    private Button LEDButton, buttonRequest;
    private AmbilWarnaDialog colorPicker;
    private SwitchCompat ledPower;

    private static final String LED = "LED";
    private static final String LED_COLOR = "LED Color";
    public static String LEDStatus;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //main function
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_l_e_d, container, false);

        LEDLayout = view.findViewById(R.id.TheSecondDawnLEDLayout);
        LEDDefaultColor = ContextCompat.getColor(requireActivity(), com.google.android.material.R.color.design_default_color_on_primary);
        ledImage = view.findViewById(R.id.TheSecondDawnImageView38);

        ledPower = view.findViewById(R.id.TheSecondDawnSwitch5);
        ledPower.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                ledPower.setChecked(true);
                ledPower.setText("Lights: ON");
                ledPower.setTextColor(requireActivity().getResources().getColor(R.color.brightgreen));
                ledImage.setVisibility(View.VISIBLE);
                LEDButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                LEDButton.setTextColor(getResources().getColor(R.color.brightgreen));
                LEDButton.setClickable(true);

            } else {
                ledPower.setChecked(false);
                ledPower.setText("Lights: OFF");
                ledPower.setTextColor(requireActivity().getResources().getColor(R.color.brightred));
                ledImage.setVisibility(View.INVISIBLE);
                LEDButton.setBackgroundColor(getResources().getColor(R.color.grey));
                LEDButton.setTextColor(getResources().getColor(R.color.brightred));
                LEDButton.setClickable(false);
            }
        });

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
        retrieveLEDSharedPref();
        return view;
    }

    //used to gain internet permissions from user
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

    //toast message alert info receive and given to user
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

    //used to open and select color with RGB picking options
    private void openColorPicker() {
        colorPicker = new AmbilWarnaDialog(getActivity(), LEDDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                editor = getActivity().getSharedPreferences(LED, MODE_PRIVATE).edit();
                editor.putInt(LED_COLOR, color);
                editor.apply();

                LEDDefaultColor = color;
                LEDLayout.setBackgroundColor(LEDDefaultColor);
            }
        });
        colorPicker.show();
    }

    private void retrieveLEDSharedPref() {
        sharedPreferences = getActivity().getSharedPreferences(LED, MODE_PRIVATE);
        LEDLayout.setBackgroundColor(sharedPreferences.getInt(LED_COLOR, 0));
    }
}