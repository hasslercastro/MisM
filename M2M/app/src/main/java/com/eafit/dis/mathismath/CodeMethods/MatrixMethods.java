package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.eafit.dis.mathismath.R;

import methods.SimpleGauss;

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
        size.setRawInputType(InputType.TYPE_CLASS_NUMBER);



        final AlertDialog alertDialog = new AlertDialog.Builder(MatrixMethods.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Size must be a number");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        Button SimpleGauss = (Button) findViewById(R.id.SimpleGauss);
        SimpleGauss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nSize =0 ;
                try {
                    nSize = Integer.parseInt(size.getText().toString());
                    Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                    intent.putExtra("method", "Simple Gauss");
                    intent.putExtra("size"  ,nSize);
                    intent.putExtra("help","The method of gaussian elimination is used to solve systems of linear equations. This method has to steps. First it converts the original matrix to another equivalent trought a serie of transformations, this matrix is called the scalonated matrix,  this matrix is also an lower triangular matrix. And the final step is to replace variables from the last row to the first one.\n");
                    startActivity(intent);
                }catch (Exception e){
                    alertDialog.show();

                }

            }
        });

        Button PartialPivoting = (Button) findViewById(R.id.PartialPivoting);
        PartialPivoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nSize =0 ;
                try {
                    nSize = Integer.parseInt(size.getText().toString());
                    Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                    intent.putExtra("method", "Partial Pivoting");
                    intent.putExtra("size"  ,nSize);
                    intent.putExtra("help","In every stage k, this method find the biggest (in absolute value) of the k column that occupies the position akk.In other words, it looks for the biggest between |akk| with  k <= i <= n , and it swaps the rows in order to set the biggest chosen in the row k. These steps guarantee the next property.\n");
                    startActivity(intent);
                }catch (Exception e){
                    alertDialog.show();

                }
            }
        });

        Button TotalPivoting = (Button) findViewById(R.id.TotalPivoting);
        TotalPivoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nSize =0 ;
                try {
                    nSize = Integer.parseInt(size.getText().toString());
                    Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                    intent.putExtra("method", "Total Pivoting");
                    intent.putExtra("size"  ,nSize);
                    intent.putExtra("help","In every stage k, this method find the biggest (in absolute value) of the submatrix, that is the result of the elimination rows from F1 until Fk-1 and columns from C1 until Ck-1 in the augmented matrix Ab . When the maximum value is detected the method swaps rows and columns to locate this element in the position Ab(k,k). After apply the swaps you have to have in mind that this change the order of the system variables");
                    startActivity(intent);
                }catch (Exception e){
                    alertDialog.show();

                }

            }
        });

        Button Crout = (Button) findViewById(R.id.Crout);
        Crout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nSize =0 ;
                try {
                    nSize = Integer.parseInt(size.getText().toString());
                    Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                    intent.putExtra("method", "Crout");
                    intent.putExtra("size"  ,nSize);
                    intent.putExtra("help","");
                    startActivity(intent);
                }catch (Exception e){
                    alertDialog.show();

                }

            }
        });

        Button Doolittle = (Button) findViewById(R.id.Doolittle);
        Doolittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nSize =0 ;
                try {
                    nSize = Integer.parseInt(size.getText().toString());
                    Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                    intent.putExtra("method", "Doolittle");
                    intent.putExtra("size"  ,nSize);
                    intent.putExtra("help","");
                    startActivity(intent);
                }catch (Exception e){
                    alertDialog.show();

                }

            }
        });

        Button Cholesky = (Button) findViewById(R.id.Cholesky);
        Cholesky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nSize =0 ;
                try {
                    nSize = Integer.parseInt(size.getText().toString());
                    Intent intent = new Intent(MatrixMethods.this, LinearEquation.class);
                    intent.putExtra("method", "Cholesky");
                    intent.putExtra("size"  ,nSize);
                    intent.putExtra("help","");
                    startActivity(intent);
                }catch (Exception e){
                    alertDialog.show();

                }

            }
        });

    }
}
