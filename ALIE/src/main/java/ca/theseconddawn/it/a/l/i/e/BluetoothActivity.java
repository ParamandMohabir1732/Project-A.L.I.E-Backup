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
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    private TextView mStatusBluetooth, mPairedDevices;
    private ImageView mBluetoothImg;
    private ImageButton leftArrow;
    private Button mBlueBtnOn, mBlueBtnOff, mBlueBtnDiscoverable, mBlueBtnPaired;

    private BluetoothAdapter mBlueAdapter;


    // main function/declaring variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        mStatusBluetooth = findViewById(R.id.TheSecondDawnTextView21);
        mPairedDevices = findViewById(R.id.TheSecondDawnTextView22);
        mBluetoothImg = findViewById(R.id.TheSecondDawnImageView39);

        mBlueBtnOn = findViewById(R.id.TheSecondDawnButton9);
        mBlueBtnOn.setOnClickListener(this);

        mBlueBtnOff = findViewById(R.id.TheSecondDawnButton10);
        mBlueBtnOff.setOnClickListener(this);

        mBlueBtnDiscoverable = findViewById(R.id.TheSecondDawnButton11);
        mBlueBtnDiscoverable.setOnClickListener(this);

        mBlueBtnPaired = findViewById(R.id.TheSecondDawnButton12);
        mBlueBtnPaired.setOnClickListener(this);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton9);
        leftArrow.setOnClickListener(this);

        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBlueAdapter == null) {
            mStatusBluetooth.setText(R.string.bluetoothStatus1);
            mStatusBluetooth.setTextColor(getColor(R.color.grey));
        } else {
            mStatusBluetooth.setText(R.string.bluetoothStatus2);
            mStatusBluetooth.setTextColor(getColor(R.color.brightblue));
        }

        if (mBlueAdapter.isEnabled()) {
            mBluetoothImg.setImageResource(R.drawable.ic_bluetoothon);

        } else {
            mBluetoothImg.setImageResource(R.drawable.ic_bluetoothoff);
        }
    }


    @Override
    public void onClick(View view) {
        //left image button/back button
        if (view.getId() == R.id.TheSecondDawnImageButton9) {
            finish();
        }
        //turn on bluetooth when button clicked
        if (view.getId() == R.id.TheSecondDawnButton9) {
            if (!mBlueAdapter.isEnabled()) {
                Toast.makeText(this, R.string.toastMessage21, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
                mStatusBluetooth.setTextColor(getColor(R.color.brightgreen));
            } else {
                Toast.makeText(this, R.string.toastMessage22, Toast.LENGTH_SHORT).show();
            }
        }
        //turns off bluetooth when clicking off button
        if (view.getId() == R.id.TheSecondDawnButton10) {
            if (mBlueAdapter.isEnabled()) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
                    mBlueAdapter.disable();
                Toast.makeText(this, R.string.toastMessage24, Toast.LENGTH_SHORT).show();
                mBluetoothImg.setImageResource(R.drawable.ic_bluetoothoff);
                mStatusBluetooth.setTextColor(getColor(R.color.brightred));
            } else {
                Toast.makeText(this, R.string.toastMessage25, Toast.LENGTH_SHORT).show();
            }
        }
        //makes this device discoverable with bluetooth via button
        if (view.getId() == R.id.TheSecondDawnButton11) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED)
                if (!mBlueAdapter.isDiscovering()) {
                    Toast.makeText(this, R.string.toastMessage23, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);
                }
        }
        //shows paired devices on bluetooth
        if (view.getId() == R.id.TheSecondDawnButton12) {
            if (mBlueAdapter.isEnabled()) {
                mPairedDevices.setText(R.string.bluetoothPaired1);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
                    for (BluetoothDevice device : devices) {
                        mPairedDevices.append(getString(R.string.bluetoothPaired2) + device.getName() + "," + device);
                    }
                } else {
                    Toast.makeText(this, R.string.toastMessage26, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    //changes on/off icon for bluetooth
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                mBluetoothImg.setImageResource(R.drawable.ic_bluetoothon);
                Toast.makeText(this, R.string.toastMessage27, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.toastMessage28, Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(resultCode, resultCode, data);
    }
}