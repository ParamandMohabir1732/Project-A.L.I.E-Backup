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

import yuku.ambilwarna.AmbilWarnaDialog;

public class LEDFrag extends Fragment {

    private final int ACCESS_LOCATION_CODE = 1;

    private LinearLayout LEDLayout;
    private int LEDDefaultColor;
    private ImageView LEDImage;
    private Button LEDButton, buttonRequest;
    private AmbilWarnaDialog colorPicker;
    private SwitchCompat LEDPower, LEDMode;

    private static final String LED = "LED";
    private static final String LED_COLOR = "LED Color";
    private static final String LED_MODE = "LED Mode";
    private static final String LED_MODE2 = "LED Mode2";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //main function
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_l_e_d, container, false);

        LEDLayout = view.findViewById(R.id.TheSecondDawnLEDLayout);
        LEDDefaultColor = ContextCompat.getColor(requireActivity(), com.google.android.material.R.color.design_default_color_on_primary);
        LEDImage = view.findViewById(R.id.TheSecondDawnImageView38);

        LEDPower = view.findViewById(R.id.TheSecondDawnSwitch5);
        LEDPower.setOnCheckedChangeListener((compoundButton, isChecked) -> LEDPower(isChecked));

        LEDMode = view.findViewById(R.id.TheSecondDawnSwitch6);
        LEDMode.setOnCheckedChangeListener((compoundButton, isChecked) -> LEDMode(isChecked));

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

    private void LEDPower(boolean isChecked) {
        if (isChecked) {
            //If LED Power Button is Checked On, set the Text to On and Switch the Image on
            editor = requireActivity().getSharedPreferences(LED, MODE_PRIVATE).edit();
            editor.putBoolean(LED_MODE, true);
            editor.apply();

            LEDPower.setChecked(true);
            LEDPower.setText(R.string.ledPowerOn);
            LEDPower.setTextColor(requireActivity().getResources().getColor(R.color.brightgreen));
            LEDImage.setVisibility(View.VISIBLE);
            LEDButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
            LEDButton.setTextColor(getResources().getColor(R.color.brightgreen));
            LEDButton.setClickable(true);
            LEDMode.setTextColor(getResources().getColor(R.color.brightgreen));
            LEDMode.setEnabled(true);
        } else {
            //If LED Power Button is Checked Off, set the text to Off and Switch the Image Off
            editor = requireActivity().getSharedPreferences(LED, MODE_PRIVATE).edit();
            editor.putBoolean(LED_MODE, false);
            editor.apply();

            LEDPower.setChecked(false);
            LEDPower.setText(R.string.ledPowerOff);
            LEDPower.setTextColor(requireActivity().getResources().getColor(R.color.brightred));
            LEDImage.setVisibility(View.INVISIBLE);
            LEDButton.setBackgroundColor(getResources().getColor(R.color.grey));
            LEDButton.setTextColor(getResources().getColor(R.color.brightred));
            LEDButton.setClickable(false);
            LEDMode.setTextColor(getResources().getColor(R.color.brightred));
            LEDMode.setChecked(false);
            LEDMode.setEnabled(false);
        }
    }

    private void LEDMode(boolean isChecked) {
        if (isChecked) {
            //If LED Button is Checked, change background color to RGB Rainbow and display Snackbar
            editor = requireActivity().getSharedPreferences(LED, MODE_PRIVATE).edit();
            editor.putBoolean(LED_MODE2, true);
            editor.apply();

            LEDMode.setChecked(true);
            LEDLayout.setBackground(getResources().getDrawable(R.drawable.gradient_led));

            Snackbar snackbar = Snackbar.make(requireView(), R.string.snackbar8, Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(getResources().getColor(R.color.brightgreen));
            snackbar.setTextColor(getResources().getColor(R.color.black));
            snackbar.show();
        } else {
            //If LED Button is unChecked, change background color to White and display Snackbar
            editor = requireActivity().getSharedPreferences(LED, MODE_PRIVATE).edit();
            editor.putBoolean(LED_MODE2, false);
            editor.apply();

            LEDMode.setChecked(false);
            LEDLayout.setBackgroundColor(getResources().getColor(R.color.white));

            Snackbar snackbar = Snackbar.make(requireView(), R.string.snackbar9, Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(getResources().getColor(R.color.white));
            snackbar.setTextColor(getResources().getColor(R.color.black));
            snackbar.show();
        }
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
                editor = requireActivity().getSharedPreferences(LED, MODE_PRIVATE).edit();
                editor.putInt(LED_COLOR, color);
                editor.apply();

                LEDDefaultColor = color;
                LEDLayout.setBackgroundColor(LEDDefaultColor);
            }
        });
        colorPicker.show();
    }

    private void retrieveLEDSharedPref() {
        sharedPreferences = requireActivity().getSharedPreferences(LED, MODE_PRIVATE);
        LEDLayout.setBackgroundColor(sharedPreferences.getInt(LED_COLOR, 0));
    }
}