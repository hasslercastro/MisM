package com.eafit.dis.mathismath.CodeMethods;

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

import com.eafit.dis.mathismath.R;

import java.math.BigDecimal;
import java.util.ArrayList;

import methods.Newton;
import methods.Secant;

/**
 * Created by Hassler on 21/03/2018.
 */

public class secantView extends Activity {
    ArrayList<ArrayList<Double>> toTable =  new ArrayList<>();
    ArrayList<String> n =  new ArrayList<>();
    ArrayList<String> x =  new ArrayList<>();
    ArrayList<String> fx =  new ArrayList<>();
    ArrayList<String> error =  new ArrayList<>();

    EditText function,tolerance,xZero,xOne,iters;
    TextView root;
    Double toleranceTo,xZeroTo,xOneTo, iterationTo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secant);

        function = (EditText) findViewById(R.id.editText11);
        tolerance = (EditText) findViewById(R.id.editText14);
        xOne = (EditText) findViewById(R.id.editText7);
        xZero =(EditText) findViewById(R.id.editText6);
        iters = (EditText) findViewById(R.id.editText12);
        root = (TextView) findViewById(R.id.textView14);


        final AlertDialog alertDialog = new AlertDialog.Builder(secantView.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Input Error");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        Button sec = (Button) findViewById(R.id.button3);
        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    iterationTo = Double.parseDouble(iters.getText().toString());
                    xZeroTo = Double.parseDouble(xZero.getText().toString());
                    xOneTo =Double.parseDouble(xOne.getText().toString());
                    toleranceTo = Double.parseDouble(tolerance.getText().toString());
                    Secant secan = new Secant(toleranceTo, xZeroTo, xOneTo, iterationTo, function.getText().toString());
                    toTable = secan.eval();
                    root.setText(secan.getMessage());
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
                xOne.setText("");
                xZero.setText("");
                iters.setText("");
                root.setText("Root");


            }
        });

        Button table = (Button) findViewById(R.id.table);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n.clear();
                x.clear();
                fx.clear();
                error.clear();
                n.add("iterations");
                x.add("X");
                fx.add("f(X)");
                error.add("error");
                for(int i = 0 ; i < toTable.size() ; i++ ){
                    n.add(String.valueOf(toTable.get(i).get(0).intValue()));
                    x.add(toTable.get(i).get(1).toString());
                    fx.add(toTable.get(i).get(2).toString());
                    error.add(toTable.get(i).get(3).toString());
                }
                Intent t = new Intent(secantView.this, secantTable.class);
                t.putExtra("iter", n);
                t.putExtra("x",x);
                t.putExtra("fx", fx);
                t.putExtra("error", error);
                startActivity(t);
            }
        });

        Button grph = (Button) findViewById(R.id.button8);
        grph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent t = new Intent(secantView.this, GraphFromMethods.class);
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



