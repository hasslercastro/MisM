package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eafit.dis.mathismath.R;

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
                startActivity(new Intent(Method.this,  OneVariableMethods.class));
            }
        });



        Button equations = (Button) findViewById(R.id.Equations);
        equations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Method.this, MatrixMethods.class));
            }
        });



    }

}
