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

import static ca.theseconddawn.it.a.l.i.e.R.string.toastMessage1;
import static ca.theseconddawn.it.a.l.i.e.R.string.toastMessage2;
import static ca.theseconddawn.it.a.l.i.e.R.string.toastMessage3;
import static ca.theseconddawn.it.a.l.i.e.R.string.toastMessage4;
import static ca.theseconddawn.it.a.l.i.e.R.string.toastMessage5;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    public Toolbar toolbar;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.TheSecondDawnToolBar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.TheSecondDawnDrawerLayout);
        navigationView = findViewById(R.id.TheSecondDawnNavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openNavigationDrawer, R.string.closeNavigationDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.TheSecondDawnFragmentContainer, new HomeFrag()).commit();
            navigationView.setCheckedItem(R.id.TheSecondDawnNavHome);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.TheSecondDawnNavHome) {
            getSupportFragmentManager().beginTransaction().replace(R.id.TheSecondDawnFragmentContainer, new HomeFrag()).commit();
        }

        if (item.getItemId() == R.id.TheSecondDawnNavVolume) {
            getSupportFragmentManager().beginTransaction().replace(R.id.TheSecondDawnFragmentContainer, new SpeakerFrag()).commit();
        }

        if (item.getItemId() == R.id.TheSecondDawnNavFan) {
            getSupportFragmentManager().beginTransaction().replace(R.id.TheSecondDawnFragmentContainer, new SmartFanFrag()).commit();
        }

        if (item.getItemId() == R.id.TheSecondDawnNavLED) {
            getSupportFragmentManager().beginTransaction().replace(R.id.TheSecondDawnFragmentContainer, new LEDFrag()).commit();
        }

        if (item.getItemId() == R.id.TheSecondDawnNavProfile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.TheSecondDawnFragmentContainer, new ProfileFrag()).commit();
        }

        if (item.getItemId() == R.id.TheSecondDawnNavSettings) {
            startActivity(new Intent(this, ConfigurationActivity.class));
        }

        if (item.getItemId() == R.id.TheSecondDawnNavLogout) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.TheSecondDawnToolBarSettings) {
            Toast.makeText(this, toastMessage1, Toast.LENGTH_LONG).show();
            Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settings);
            return true;
        }

        if (item.getItemId() == R.id.TheSecondDawnToolBarHelp) {
            Toast.makeText(this, toastMessage2, Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        }

        if (item.getItemId() == R.id.TheSecondDawnToolBarVolume) {
            Toast.makeText(this, toastMessage3, Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.TheSecondDawnFragmentContainer, new SpeakerFrag()).commit();
        }

        if (item.getItemId() == R.id.TheSecondDawnToolBarBluetooth) {
            Toast.makeText(this, toastMessage4, Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, BluetoothActivity.class));
        }

        if (item.getItemId() == R.id.TheSecondDawnToolBarReview) {
            Toast.makeText(this, toastMessage5, Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, CustomerReviewActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        finish();
    }
}