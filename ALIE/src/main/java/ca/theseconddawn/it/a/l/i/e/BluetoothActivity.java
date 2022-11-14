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

import com.google.android.material.snackbar.Snackbar;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    private TextView mStatusBluetooth, mPairedSpeaker;
    private ImageView mBluetoothImg;
    private Button mBlueBtnOn, mBlueBtnOff, mBlueBtnDiscoverable, mBlueBtnPaired, mBlueBtnDevices;

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
        mBlueBtnDevices = findViewById(R.id.TheSecondDawnButton13);

        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBlueAdapter == null) {
            mStatusBluetooth.setText("Bluetooth is not available");
        } else {
            mStatusBluetooth.setText("Bluetooth is available");
        }

        if (mBlueAdapter.isEnabled()) {
            mBluetoothImg.setImageResource(R.drawable.ic_bluetoothon);

        } else {
            mBluetoothImg.setImageResource(R.drawable.ic_bluetoothoff);
        }

        mBlueBtnOn.setOnClickListener(view -> {
            if (!mBlueAdapter.isEnabled()) {
                Toast.makeText(BluetoothActivity.this, "Turning on Bluetooth...", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                if (ActivityCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
            } else {
                Toast.makeText(BluetoothActivity.this, "Bluetooth is Already On!", Toast.LENGTH_SHORT).show();
            }
        });

        mBlueBtnDiscoverable.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            }
            if (!mBlueAdapter.isDiscovering()) {
                Toast.makeText(BluetoothActivity.this, "Making Device Discoverable!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(intent, REQUEST_DISCOVER_BT);

            }
        });

        mBlueBtnOff.setOnClickListener(view -> {
            if (mBlueAdapter.isEnabled()) {
                if (ActivityCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                }
                mBlueAdapter.disable();
                Toast.makeText(BluetoothActivity.this, "Bluetooth Off", Toast.LENGTH_SHORT).show();
                mBluetoothImg.setImageResource(R.drawable.ic_bluetoothoff);
            } else {
                Toast.makeText(BluetoothActivity.this, "Bluetooth is Already Off!", Toast.LENGTH_SHORT).show();
            }
        });

        mBlueBtnPaired.setOnClickListener(view -> {

            if (mBlueAdapter.isEnabled()) {
                mBlueBtnPaired.setText("Paired Devices");
                if (ActivityCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    Snackbar snackbar = Snackbar.make(linearLayout, R.string.snackbar1, Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(getResources().getColor(R.color.brightgreen));
                    snackbar.setTextColor(getResources().getColor(R.color.black));
                    snackbar.show();
                }
                Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
                for (BluetoothDevice device : devices) {
                    mBlueBtnPaired.append("\nDevice: " + device.getName() + "," + device);
                }
            } else {

                Toast.makeText(BluetoothActivity.this, "Turn on Bluetooth to Show Paired Devices!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                mBluetoothImg.setImageResource(R.drawable.ic_bluetoothon);
                Toast.makeText(BluetoothActivity.this, "Bluetooth is Now Turned On!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BluetoothActivity.this, "Unable to Turn on Bluetooth!", Toast.LENGTH_SHORT).show();

            }
        }
        super.onActivityResult(resultCode, resultCode, data);
    }

}


