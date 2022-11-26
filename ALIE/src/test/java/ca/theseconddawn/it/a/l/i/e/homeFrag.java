package ca.theseconddawn.it.a.l.i.e;

import static org.junit.Assert.assertEquals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(RobolectricTestRunner.class)
public class homeFrag extends Activity{
        @Override
        protected void onCreate(Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                final View button = findViewById(R.id.TheSecondDawnToolBarSettings);
                button.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                                startActivity(new Intent(homeFrag.this, ConfigurationActivity.class));
                        }
                });
        }
        }