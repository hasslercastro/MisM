package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eafit.dis.mathismath.R;

import methods.GaussSeidel;
import methods.Jacobi;
import methods.RelaxedGauss;
import methods.RelaxedJacobi;

/**
 * Created by root on 5/9/18.
 */

public class Iterative_methodsView extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iterative_methods);

        Button jacobi = (Button) findViewById(R.id.Jacobi);
        jacobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Iterative_methodsView.this,  Grapher.class));
            }
        });

        Button gauss_seidel = (Button) findViewById(R.id.GaussSeidel);
        gauss_seidel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Iterative_methodsView.this,  Grapher.class));
            }
        });

        Button relaxed_jacobi = (Button) findViewById(R.id.Jacobi_Relaxed);
        relaxed_jacobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Iterative_methodsView.this,  Grapher.class));
            }
        });

        Button relaxed_gauss_seidel = (Button) findViewById(R.id.GaussSeidel_Relaxed);
        relaxed_gauss_seidel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Iterative_methodsView.this,  Grapher.class));
            }
        });


    }
}
