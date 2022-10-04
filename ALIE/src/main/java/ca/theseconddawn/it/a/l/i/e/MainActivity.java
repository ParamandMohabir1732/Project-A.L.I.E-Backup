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

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

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
            Intent settings = new Intent(MainActivity.this, ConfigurationActivity.class);
            startActivity(settings);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed () {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.builderTitle);
        builder.setMessage(R.string.builderMessage);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setCancelable(true);
        builder.setNegativeButton(R.string.builderNegativeButton, ((dialogInterface, i) -> dialogInterface.cancel()));
        builder.setPositiveButton(R.string.builderPositiveButton, ((dialogInterface, i) -> finish()));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
