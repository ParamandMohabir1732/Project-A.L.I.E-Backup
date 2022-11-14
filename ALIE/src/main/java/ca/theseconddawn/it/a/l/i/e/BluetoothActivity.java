package ca.theseconddawn.it.a.l.i.e;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT=0;
    private static final int REQUEST_DISCOVER_BT= 1;

    TextView mStatusBluetooth, mPairedSpeaker;
    ImageView mBluetoothImg;
    Button mBlueBtnOn, mBlueBtnOff, mBlueBtnDiscoverable, mBlueBtnPaired, mBlueBtnDevices ;

    BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        mStatusBluetooth = findViewById(R.id.StatusBluetooth);
        mPairedSpeaker =findViewById(R.id.PairedSpeaker);
        mBluetoothImg =findViewById(R.id.BluetoothImg);
        mBlueBtnOn =findViewById(R.id.BlueBtnOn);
        mBlueBtnOff =findViewById(R.id.BlueBtnOff);
        mBlueBtnDiscoverable =findViewById(R.id.BlueBtnDiscoverable);
        mBlueBtnPaired =findViewById(R.id.BlueBtnPaired);
        mBlueBtnDevices =findViewById(R.id.BlueBtnDevices);

        mBlueAdapter =BluetoothAdapter.getDefaultAdapter();

        if(mBlueAdapter == null) {

            mStatusBluetooth.setText("Bluetooth is not available");
        }
        else{
            mStatusBluetooth.setText("Bluetooth is available");
            }



        }


    }
