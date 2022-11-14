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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    private TextView mStatusBluetooth, mPairedSpeaker;
    private ImageView mBluetoothImg;
    private Button mBlueBtnOn, mBlueBtnOff, mBlueBtnDiscoverable, mBlueBtnPaired;

    private LinearLayout linearLayout;
    private BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        linearLayout = findViewById(R.id.TheSecondDawnBluetoothLayout);

        mStatusBluetooth = findViewById(R.id.TheSecondDawnTextView21);
        mPairedSpeaker = findViewById(R.id.TheSecondDawnTextView22);
        mBluetoothImg = findViewById(R.id.TheSecondDawnImageView39);

        mBlueBtnOn = findViewById(R.id.TheSecondDawnButton9);
        mBlueBtnOff = findViewById(R.id.TheSecondDawnButton10);
        mBlueBtnDiscoverable = findViewById(R.id.TheSecondDawnButton11);
        mBlueBtnPaired = findViewById(R.id.TheSecondDawnButton12);

        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBlueAdapter == null) {
            mStatusBluetooth.setText(R.string.bluetoothStatus1);
        } else {
            mStatusBluetooth.setText(R.string.bluetoothStatus2);
        }

        if (mBlueAdapter.isEnabled()) {
            mBluetoothImg.setImageResource(R.drawable.ic_bluetoothon);

        } else {
            mBluetoothImg.setImageResource(R.drawable.ic_bluetoothoff);
        }

        mBlueBtnOn.setOnClickListener(view -> {
            if (!mBlueAdapter.isEnabled()) {
                Toast.makeText(this, R.string.toastMessage21, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
            } else {
                Toast.makeText(this, R.string.toastMessage22, Toast.LENGTH_SHORT).show();
            }
        });

        mBlueBtnDiscoverable.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED)
                if (!mBlueAdapter.isDiscovering()) {
                    Toast.makeText(this, R.string.toastMessage23, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);

                }
        });

        mBlueBtnOff.setOnClickListener(view -> {
            if (mBlueAdapter.isEnabled()) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
                    mBlueAdapter.disable();
                Toast.makeText(this, R.string.toastMessage24, Toast.LENGTH_SHORT).show();
                mBluetoothImg.setImageResource(R.drawable.ic_bluetoothoff);
            } else {
                Toast.makeText(this, R.string.toastMessage25, Toast.LENGTH_SHORT).show();
            }
        });

        mBlueBtnPaired.setOnClickListener(view -> {

            if (mBlueAdapter.isEnabled()) {
                mBlueBtnPaired.setText(R.string.bluetoothPaired1);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                }
                Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
                for (BluetoothDevice device : devices) {
                    mBlueBtnPaired.append(getString(R.string.bluetoothPaired2) + device.getName() + "," + device);
                }
            } else {

                Toast.makeText(this, R.string.toastMessage26, Toast.LENGTH_SHORT).show();
            }
        });
    }

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