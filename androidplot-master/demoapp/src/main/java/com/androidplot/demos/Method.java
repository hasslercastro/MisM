package com.androidplot.demos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Hassler on 13/03/2018.
 */

public class Method extends Activity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.methods);


        Button oneVariable = (Button) findViewById(R.id.OneVariable);
        oneVariable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Method.this, OneVariableMethods.class));
            }
        });

    }

}
