package com.androidplot.demos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.math.BigDecimal;
import java.util.ArrayList;


import methods.FalsePosition;
import methods.Newton;

/**
 * Created by Hassler on 15/03/2018.
 */


public class newtonView extends Activity{
    ArrayList<ArrayList<Double>> toTable =  new ArrayList<>();
    ArrayList<String> iter =  new ArrayList<>();
    ArrayList<String> x =  new ArrayList<>();
    ArrayList<String> fx =  new ArrayList<>();
    ArrayList<String> dfx =  new ArrayList<>();
    ArrayList<String> error =  new ArrayList<>();

    EditText function,tolerance,initial,derivative,iteration;
    TextView root;
    Double toleranceTo;
    BigDecimal initialTo;
    int iterationTo;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newton);

        function = (EditText) findViewById(R.id.editText7);
        tolerance = (EditText) findViewById(R.id.editText11);
        initial = (EditText) findViewById(R.id.editText12);
        derivative = (EditText) findViewById(R.id.editText13);
        iteration = (EditText) findViewById(R.id.editText6);
        root = (TextView) findViewById(R.id.textView14);


        final AlertDialog alertDialog = new AlertDialog.Builder(newtonView.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Input Error");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        Button newt = (Button) findViewById(R.id.button3);
        newt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    iterationTo = Integer.parseInt(iteration.getText().toString());
                    initialTo = new BigDecimal(initial.getText().toString());
                    toleranceTo = Double.parseDouble(tolerance.getText().toString());
                    Newton newton = new Newton(toleranceTo, initialTo, iterationTo, function.getText().toString(), derivative.getText().toString());
                    toTable = newton.eval();
                    root.setText(newton.getMsg());
                }catch (Exception e){
                    alertDialog.show();
                }

            }
        });

        Button clear = (Button) findViewById(R.id.button6);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                function.setText("");
                tolerance.setText("");
                derivative.setText("");
                iteration.setText("");
                initial.setText("");
                root.setText("Root");


            }
        });

        Button table = (Button) findViewById(R.id.table);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iter.clear();
                x.clear();
                fx.clear();
                dfx.clear();
                error.clear();
                for(int i = 0 ; i < toTable.size() ; i++ ){
                    iter.add(String.valueOf(toTable.get(i).get(0).intValue()));
                    x.add(toTable.get(i).get(1).toString());
                    fx.add(toTable.get(i).get(2).toString());
                    dfx.add(toTable.get(i).get(3).toString());
                    error.add(toTable.get(i).get(4).toString());
                }
                Intent t = new Intent(newtonView.this, TableNewton.class);
                t.putExtra("iter", iter);
                t.putExtra("x",x);
                t.putExtra("fx", fx);
                t.putExtra("dfx", dfx);
                t.putExtra("error", error);
                startActivity(t);



            }
        });

        Button grph = (Button) findViewById(R.id.button8);
        grph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent t = new Intent(newtonView.this, GraphFromMethods.class);
                    t.putExtra("function", function.getText().toString());
                    startActivity(t);
                }catch (Exception e){
                    Log.d("This is the error", e.toString());
                    alertDialog.show();
                }
            }
        });




    }




}



