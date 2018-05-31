package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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


import methods.Lagrange;
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
        final AlertDialog alertDialog = new AlertDialog.Builder(Interpolation.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Input Error");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        final double [] x  = new double[Size];
        final double [] y = new double[Size];


        final TextView polinomio = (TextView) findViewById(R.id.polinomio);
        //Setear el texto dps
        final TableLayout table = (TableLayout) findViewById(R.id.data);

        final EditText eval = (EditText) findViewById(R.id.editText20);


        Log.d("K", String.valueOf(Size));
        for(int i = 0 ; i < Size ; i++ ){
            TableRow row = new TableRow(this);
            EditText x_e = new EditText(this);
            x_e.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            EditText y_e = new EditText(this);
            y_e.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
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
                try {
                    for (int i = 0; i < x.length; i++) {
                        TableRow row = (TableRow) table.getChildAt(i);
                        EditText tempO = (EditText) row.getChildAt(0);
                        //Log.d("J", String.valueOf( Double.valueOf(tempO.getText().toString())) );
                        EditText temp2 = (EditText) row.getChildAt(1);
                        x[i] = Double.parseDouble(tempO.getText().toString());
                        y[i] = Double.parseDouble(temp2.getText().toString());
                    }
                    NewtonPolinomio np = new NewtonPolinomio(x, y);
                    np.eval();
                    if (np.getPolinomio().contains("NaN"))throw  new Exception();
                    polinomio.setText(np.getPolinomio());
                }catch (Exception e){
                    alertDialog.show();
                }
            }
        });


        Button executer = (Button) findViewById(R.id.execute);
        executer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    for (int i = 0; i < x.length; i++) {
                        TableRow row = (TableRow) table.getChildAt(i);
                        EditText tempO = (EditText) row.getChildAt(0);
                        //Log.d("J", String.valueOf( Double.valueOf(tempO.getText().toString())) );
                        EditText temp2 = (EditText) row.getChildAt(1);
                        x[i] = Double.parseDouble(tempO.getText().toString());
                        y[i] = Double.parseDouble(temp2.getText().toString());
                    }
                    NewtonPolinomio np = new NewtonPolinomio(x, y);
                    np.eval();
                    polinomio.setText(polinomio.getText() + " Evaluated at " + eval.getText().toString() + " is equals to " + np.getSolution(Double.parseDouble(eval.getText().toString())));
                } catch (Exception e){
                    alertDialog.show();
                }

            }

        });

        Button lagrange = (Button) findViewById(R.id.button12);
        lagrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    for (int i = 0; i < x.length; i++) {
                        TableRow row = (TableRow) table.getChildAt(i);
                        EditText tempO = (EditText) row.getChildAt(0);
                        //Log.d("J", String.valueOf( Double.valueOf(tempO.getText().toString())) );
                        EditText temp2 = (EditText) row.getChildAt(1);
                        x[i] = Double.parseDouble(tempO.getText().toString());
                        y[i] = Double.parseDouble(temp2.getText().toString());
                    }
                    //NewtonPolinomio np = new NewtonPolinomio(x,y);
                    Lagrange lg = new Lagrange(x, y);
                    if (lg.getPolinomio().contains("NaN"))throw  new Exception();
                    polinomio.setText("Polynomial:\n" + lg.getPolinomio());
                    //+ " Evaluated at " +eval.getText().toString() + " is equals to " + lg.getSolution(Double.parseDouble(eval.getText().toString())));
                }catch (Exception e){
                    alertDialog.show();
                }

            }

        });
        Button help = findViewById(R.id.help_button);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Interpolation.this, PopHelp.class);
                intent.putExtra("help", " Given a sequence\n" +
                        "of (n +1) data points and a function f, the aim is to determine an n-th degree polynomial which interpolates\n" +
                        "f at these points.\n\n-Be careful to don't repeat any x, else it won't be a function");
                startActivity(intent);
            }
        });
    }
}