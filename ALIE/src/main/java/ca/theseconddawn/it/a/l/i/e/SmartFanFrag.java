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
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

public class SmartFanFrag extends Fragment {

    //defining local controls
    private Button requestPermission;
    private final int STORAGE_PERMISSION = 1;
    private SwitchCompat fanControl;
    private Slider fanSpeed;
    private RadioGroup radGroup;
    private RadioButton low;
    private RadioButton med;
    private RadioButton high;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fan, container, false);

        //define local variables and link them to their ID's
        fanControl = view.findViewById(R.id.TheSecondDawnSwitch8);
        fanSpeed = view.findViewById(R.id.TheSecondDawnSlider1);
        radGroup = view.findViewById(R.id.TheSecondDawnRadioGroup);
        low = view.findViewById(R.id.TheSecondDawnRadioButton1);
        med = view.findViewById(R.id.TheSecondDawnRadioButton2);
        high = view.findViewById(R.id.TheSecondDawnRadioButton3);

        requestPermission = view.findViewById(R.id.TheSecondDawnButton3);
        //Request user permission for data usage
        //this potion of code is based on the dependency injection design principle since the code for rquesting the storage is not here
        //This is just an instance of the request storage permission. There could be multiple instances created with the same function
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

        //disable buttons if the fan is off
        fanSpeed.setEnabled(false);
        low.setClickable(false);
        med.setClickable(false);
        high.setClickable(false);

        //Enable buttons if the fan is on
        //Design pattern of this portion of code is composite pattern
        //It is a logical operation with a strong hierarchy of elements within it
        //If the fan is off you cannot control it
        //If the fan is off no radio button should be selected
        //if the fans is on then It should not get to the max level of rotation and so on
        fanControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                fanSpeed.setEnabled(fanControl.isChecked());
                low.setClickable(fanControl.isChecked());
                med.setClickable(fanControl.isChecked());
                high.setClickable(fanControl.isChecked());
                radGroup.clearCheck();
                fanSpeed.setValue(10);
            }
        });
        //Predefined values for radio buttons
        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(low.isChecked()){
                    fanSpeed.setValue(25);
                }
                else if(med.isChecked()){
                    fanSpeed.setValue(50);
                }
                else if(high.isChecked()){
                    fanSpeed.setValue(75);
                }
            }
        });

        return view;
    }

    //request permission
    //Dependency injection class that
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
