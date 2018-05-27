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
 * Created by Hassler on 5/05/2018.
 */

public class IterativeMethods extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iterative_methods);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final EditText size = (EditText) findViewById(R.id.sizeIterative);
        size.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

        Button Jacobi = (Button) findViewById(R.id.Jacobi);
        Jacobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(IterativeMethods.this, LinearEquationIterative.class);
                intent.putExtra("method", "Jacobi");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button GaussSeidel = (Button) findViewById(R.id.GaussSeidel);
        GaussSeidel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(IterativeMethods.this, LinearEquationIterative.class);
                intent.putExtra("method", "Gauss Seidel");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button RelaxedJacobi = (Button) findViewById(R.id.Jacobi_Relaxed);
        RelaxedJacobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(IterativeMethods.this, LinearEquationIterativeRelaxed.class);
                intent.putExtra("method", "Relaxed Jacobi");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button RelaxedGaussSeidel = (Button) findViewById(R.id.GaussSeidel_Relaxed);
        RelaxedGaussSeidel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(IterativeMethods.this, LinearEquationIterativeRelaxed.class);
                intent.putExtra("method", "Relaxed Gauss Seidel");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });
    }
}
