package com.androidplot.demos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Hassler on 14/03/2018.
 */

public class OneVariableMethods extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_one_variable);


        Button bisection = (Button) findViewById(R.id.bisection);
        bisection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OneVariableMethods.this, bisectionView.class));
            }
        });

    }


}
