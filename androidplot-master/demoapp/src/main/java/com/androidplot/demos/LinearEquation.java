package com.androidplot.demos;

import android.app.Activity;
import methods.SimpleGauss;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import methods.SimpleGauss;

/**
 * Created by Hassler on 25/04/2018.
 */

public class LinearEquation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix);

        final int rows= getIntent().getExtras().getInt("size");
        int columns= getIntent().getExtras().getInt("size");;
        final String method = getIntent().getExtras().getString("method");
    /**
        // GET THE MATRIX DIMENSIONS
        int rows=4;
        int columns=4;

        // INITIALISE YOUR GRID
        GridView grid=(GridView)findViewById(R.id.view);
        grid.setNumColumns(columns);

        // CREATE A LIST OF MATRIX OBJECT
        List<Matrix> matrixList=new ArrayList<>();

        // ADD SOME CONTENTS TO EACH ITEM
        for (int i=0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
            {
                matrixList.add(new Matrix(i,j));
            }
        }

        // CREATE AN ADAPTER  (MATRIX ADAPTER)
        MatrixAdapter adapter=new MatrixAdapter(getApplicationContext(),matrixList);

        // ATTACH THE ADAPTER TO GRID
        grid.setAdapter(adapter);


        adapter.getView(0 , grid, grid);
        //grid.getChildAt(0).findViewById(0).;


    } */
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(method);
        title.setTextSize(30);
        title.setGravity(Gravity.CENTER);
        final TableLayout A = (TableLayout) findViewById(R.id.matrixT);
        final TableLayout b = (TableLayout) findViewById(R.id.b);
        final TableLayout x = (TableLayout) findViewById(R.id.x);



        for(int i = 0 ; i < rows ; i++){
            TableRow row= new TableRow(this);
            EditText position = new EditText(this);
            position.setText(" 0 ");
            position.setInputType(InputType.TYPE_CLASS_NUMBER);
            row.addView(position);
            b.addView(row,i);

        }


        for (int i=0;i<rows;i++)
        {
            TableRow row= new TableRow(this);
            for (int j=0;j<columns;j++)
            {
                EditText position = new EditText(this);
                position.setText(" 0 ");
                position.setInputType(InputType.TYPE_CLASS_NUMBER);
                row.addView(position);
            }
            A.addView(row,i);
        }
        final double[][] A_matrix = new double[rows][columns];
        final double[] vect_b = new double[rows];



        Button run = (Button) findViewById(R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x.removeAllViews();

                switch (method){
                    case "Simple Gauss":
                        for(int i = 0 ; i < rows ; i++){
                            TableRow row = (TableRow) A.getChildAt(i);
                            TableRow row_b = (TableRow) b.getChildAt(0);
                            EditText temp = (EditText) row.getChildAt(i);
                            vect_b[i] = Double.valueOf(temp.getText().toString());
                            for(int j = 0 ; j < rows ; j++){
                                temp = (EditText) row.getChildAt(j);
                                A_matrix[i][j] = Double.valueOf(temp.getText().toString());
                            }
                        }
                        SimpleGauss sg = new SimpleGauss(A_matrix, vect_b);
                        double[] solution = sg.getSolution();

                        for(int i = 0 ; i < rows ; i++){

                            TableRow row= new TableRow(LinearEquation.this);
                            EditText value  = new EditText(LinearEquation.this);
                            value.setText(String.valueOf(solution[i]));
                            value.setEnabled(false);
                            row.addView(value);
                            x.addView(row,i);
                        }






                }
            }
        });



    }
}