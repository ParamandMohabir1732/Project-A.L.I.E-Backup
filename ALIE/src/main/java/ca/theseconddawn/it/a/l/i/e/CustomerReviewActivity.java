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
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton leftArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review);

        leftArrow = findViewById(R.id.TheSecondDawnImageButton10);
        leftArrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.TheSecondDawnImageButton10) {
            finish();
        }
    }
}