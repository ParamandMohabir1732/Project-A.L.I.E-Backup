package ca.theseconddawn.it.a.l.i.e;
/*Vladislav Vassilyev, N01436627 10/09/2022*/
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SmartFanFrag extends Fragment {
    private int STORAGE_PERMISSION = 1;

    public SmartFanFrag() {
        // Required empty public constructor
    }
//Code for prompting for runtime permission
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Button requestPermission = (Button) getView().findViewById(R.id.permission);
        requestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getActivity(), "The permision was granted", Toast.LENGTH_SHORT).show();
                }
                else{ requestStoragePermission(); }


            }
        });

        return inflater.inflate(R.layout.fan_frag, container, false);
    }
    private void requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission Needed")
                    .setMessage("permission needed for proper functionality")
                    .setPositiveButton("Grant permission", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);

                        }
                    })
                    .setNegativeButton("Deny permission", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION){
            if(grantResults.length > 0 && grantResults[0]  == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getActivity(),"Permission Granted", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity(),"Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
