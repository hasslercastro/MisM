package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.eafit.dis.mathismath.R;

/**
 * Created by Joshua on 5/18/18.
 */

public class pre_interpolation_view extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_interpolation);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Button newton_lagrange = (Button) findViewById(R.id.newton_lagrange);
        newton_lagrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pre_interpolation_view.this, Interpolation.class));

            }
        });

        Button splines = (Button) findViewById(R.id.splines);
        splines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pre_interpolation_view.this, splinesView.class));

            }
        });
    }
}
