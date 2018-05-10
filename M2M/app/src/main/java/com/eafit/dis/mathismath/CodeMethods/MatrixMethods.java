package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.eafit.dis.mathismath.R;

/**
 * Created by Hassler on 5/05/2018.
 */

public class MatrixMethods extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equation_methods);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final EditText size = (EditText) findViewById(R.id.size);



        Button SimpleGauss = (Button) findViewById(R.id.SimpleGauss);
        SimpleGauss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                intent.putExtra("method", "Simple Gauss");
                intent.putExtra("size"  ,nSize);
                intent.putExtra("help","The method of gaussian elimination is used to solve systems of linear equations. This method has to steps. First it converts the original matrix to another equivalent trought a serie of transformations, this matrix is called the scalonated matrix,  this matrix is also an lower triangular matrix. And the final step is to replace variables from the last row to the first one.\n");
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

    }
}
