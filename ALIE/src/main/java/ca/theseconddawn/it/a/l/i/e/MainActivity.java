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

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

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

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openNavigationDrawer, R.string.closeNavigationDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
