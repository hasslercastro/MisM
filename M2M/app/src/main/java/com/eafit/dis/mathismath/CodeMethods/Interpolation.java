package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.util.ArrayList;

import methods.Newton;
import methods.NewtonPolinomio;

/**
 * Created by Hassler on 10/05/2018.
 */

public class Interpolation extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interpolation);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        int Size = Integer.parseInt(getIntent().getStringExtra("size"));
        final ArrayList<String> x_array = new ArrayList<>();
        final ArrayList<String> y_array = new ArrayList<>();
        {

            final double [] x = new double[Size];
            final double [] y = new double[Size];


            final TextView polinomio = (TextView) findViewById(R.id.polinomio);
            //Setear el texto dps
            final TableLayout table = (TableLayout) findViewById(R.id.data);
            //final EditText x = (EditText) findViewById(R.id.x);
            //final EditText y = (EditText) findViewById(R.id.y);

            TableRow row = new TableRow(Interpolation.this);
            EditText ord = new EditText(Interpolation.this);
            row.addView(ord);
            row.addView(ord);

            for(int i = 0 ; i < Size ; i++ ){
                table.addView(row);
            }
            /**
            Button add = (Button) findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    x_array.add(x.getText().toString());
                    y_array.add(y.getText().toString());
                    TableRow row = new TableRow(Interpolation.this);
                    EditText value = new EditText(Interpolation.this);
                    value.setText(x.getText().toString() + " , " + y.getText().toString());
                    row.addView(value);
                    table.addView(row);

                }
            });*/
            Button newton = (Button) findViewById(R.id.newton);
            newton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for(int i = 0 ; i < x.length ; i++){
                        TableRow row = (TableRow) table.getChildAt(i);
                        EditText temp = (EditText) row.getChildAt(0);
                        EditText temp2 = (EditText) row.getChildAt(1);
                        x[i] = Double.parseDouble(temp.getText().toString());
                        y[i] = Double.parseDouble(temp2.getText().toString());
                        NewtonPolinomio np = new NewtonPolinomio(x,y);
                        polinomio.setText(np.getPolinomio());
                    }

                }
            });


        }


    }
}
