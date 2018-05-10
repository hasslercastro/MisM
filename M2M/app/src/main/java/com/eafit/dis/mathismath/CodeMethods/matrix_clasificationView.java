package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eafit.dis.mathismath.R;

/**
 * Created by root on 5/9/18.
 */

public class matrix_clasificationView extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix_clasification);

        Button direct_methods = (Button) findViewById(R.id.direct_methods);
        direct_methods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(matrix_clasificationView.this,  MatrixMethods.class));
            }
        });

        Button iterative_methods = (Button) findViewById(R.id.iterative_methods);
        iterative_methods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(matrix_clasificationView.this,  Iterative_methodsView.class));
            }
        });
    }




    }
