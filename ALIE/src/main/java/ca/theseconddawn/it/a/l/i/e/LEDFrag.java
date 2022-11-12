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

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import yuku.ambilwarna.AmbilWarnaDialog;

public class LEDFrag extends Fragment {
    ConstraintLayout LEDLayout;
    int LEDDefaultColor;
    Button LEDButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_l_e_d, container, false);


        LEDLayout = (ConstraintLayout) view.findViewById(R.id.Layout);
        LEDButton = view.findViewById(R.id.button);
        LEDDefaultColor = ContextCompat.getColor(getActivity(), com.google.android.material.R.color.design_default_color_on_primary);
        LEDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });
        return view;
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
        }
        );
        colorPicker.show();
    }
}

