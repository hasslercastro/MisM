package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.util.ArrayList;

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


            final double [] x  = new double[Size];
            final double [] y = new double[Size];


            final TextView polinomio = (TextView) findViewById(R.id.polinomio);
            //Setear el texto dps
            final TableLayout table = (TableLayout) findViewById(R.id.data);


            Log.d("K", String.valueOf(Size));
            for(int i = 0 ; i < Size ; i++ ){
                Log.d("s", ":V:V:V");
                TableRow row = new TableRow(this);
                EditText x_e = new EditText(this);
                EditText y_e = new EditText(this);
                x_e.setText("0");
                y_e.setText("0");
                row.addView(x_e);
                row.addView(y_e);
                table.addView(row,i);
            }


            Button newton = (Button) findViewById(R.id.newton);
            newton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for(int i = 0 ; i < x.length ; i++){

                        TableRow row = (TableRow) table.getChildAt(i);
                        EditText tempO = (EditText) row.getChildAt(0);
                        //Log.d("J", String.valueOf( Double.valueOf(tempO.getText().toString())) );
                        EditText temp2 = (EditText) row.getChildAt(1);
                        x[i] = Double.parseDouble(tempO.getText().toString());
                        y[i] = Double.parseDouble(temp2.getText().toString());
                    }
                    NewtonPolinomio np = new NewtonPolinomio(x,y);
                    np.eval();
                    polinomio.setText(np.getPolinomio());
                }
            });
        }
    }

