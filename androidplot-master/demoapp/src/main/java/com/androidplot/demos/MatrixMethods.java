package com.androidplot.demos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hassler on 5/05/2018.
 */

public class MatrixMethods extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equation_methods);

        final EditText size = (EditText) findViewById(R.id.size);



        Button SimpleGauss = (Button) findViewById(R.id.SimpleGauss);
        SimpleGauss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                intent.putExtra("method", "Simple Gauss");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button PartialPivoting = (Button) findViewById(R.id.PartialPivoting);
        PartialPivoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                intent.putExtra("method", "Partial Pivoting");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button TotalPivoting = (Button) findViewById(R.id.TotalPivoting);
        TotalPivoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                intent.putExtra("method", "Total Pivoting");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button Crout = (Button) findViewById(R.id.Crout);
        Crout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                intent.putExtra("method", "Crout");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button Doolittle = (Button) findViewById(R.id.Doolittle);
        Doolittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                intent.putExtra("method", "Doolittle");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button Cholesky = (Button) findViewById(R.id.Cholesky);
        Cholesky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                intent.putExtra("method", "Cholesky");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button Jacobi = (Button) findViewById(R.id.Jacobi);
        Jacobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
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
                Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                intent.putExtra("method", "Gauss Seidel");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });



    }
}
