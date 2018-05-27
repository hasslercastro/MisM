package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

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

        final EditText size = (EditText) findViewById(R.id.size);
        size.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

        Button newton_lagrange = (Button) findViewById(R.id.newton_lagrange);
        newton_lagrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pre_interpolation_view.this, Interpolation.class);
                intent.putExtra("size", size.getText().toString());
                startActivity(intent);
                //startActivity(new Intent(pre_interpolation_view.this, Interpolation.class));

            }
        });

        Button splines = (Button) findViewById(R.id.splines);
        splines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pre_interpolation_view.this, splinesView.class);
                intent.putExtra("size", size.getText().toString());
                startActivity(intent);
            }
        });
    }
}
