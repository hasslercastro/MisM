package com.androidplot.demos;

import android.app.Activity;
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

/**
 * Created by Hassler on 25/04/2018.
 */

public class LinearEquation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix);

        int rows= getIntent().getExtras().getInt("size");
        int columns= getIntent().getExtras().getInt("size");;
        String method = getIntent().getExtras().getString("method");
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
        TableLayout ll = (TableLayout) findViewById(R.id.matrixT);
        TableLayout b = (TableLayout) findViewById(R.id.b);

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
            ll.addView(row,i);
        }



 }
}