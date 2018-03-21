package com.androidplot.demos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import methods.MultipleRoots;
import methods.Secant;

/**
 * Created by Hassler on 21/03/2018.
 */

//iterations , x , f(x), derivada , segunda, error
public class multipleRoots extends Activity{
    ArrayList<ArrayList<Double>> toTable =  new ArrayList<>();
    ArrayList<String> n =  new ArrayList<>();
    ArrayList<String> x =  new ArrayList<>();
    ArrayList<String> fx =  new ArrayList<>();
    ArrayList<String> dfx =  new ArrayList<>();
    ArrayList<String> ddfx =  new ArrayList<>();
    ArrayList<String> error =  new ArrayList<>();

    EditText function,tolerance,iterations, firstD, secondD, initialPoint;
    TextView root;
    Double toleranceTo;
    int iterTo ;
    BigDecimal initial;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_roots);

        function = (EditText) findViewById(R.id.editText11);
        tolerance = (EditText) findViewById(R.id.editText15);
        firstD = (EditText) findViewById(R.id.editText14);
        secondD = (EditText) findViewById(R.id.editText6);
        iterations = (EditText) findViewById(R.id.editText12);
        initialPoint =  (EditText) findViewById(R.id.editText7);
        root = (TextView) findViewById(R.id.textView13);


        final AlertDialog alertDialog = new AlertDialog.Builder(multipleRoots.this).create();
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
                    initial = new BigDecimal(initialPoint.getText().toString());
                    iterTo = Integer.parseInt(iterations.getText().toString());
                    toleranceTo = Double.parseDouble(tolerance.getText().toString());
                    MultipleRoots mroot = new MultipleRoots(toleranceTo, initial, iterTo, function.getText().toString(), firstD.getText().toString(), secondD.getText().toString());
                    toTable = mroot.eval();
                    root.setText(mroot.getMsg());
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
                initialPoint.setText("");
                iterations.setText("");
                firstD.setText("");
                secondD.setText("");
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
                dfx.clear();
                ddfx.clear();
                error.clear();
                n.add("iterations");
                x.add("X");
                fx.add("f(X)");
                dfx.add("f'(X)");
                ddfx.add("f''(X)");
                error.add("error");
                for(int i = 0 ; i < toTable.size() ; i++ ){
                    n.add(String.valueOf(toTable.get(i).get(0).intValue()));
                    x.add(toTable.get(i).get(1).toString());
                    fx.add(toTable.get(i).get(2).toString());
                    dfx.add(toTable.get(i).get(3).toString());
                    ddfx.add(toTable.get(i).get(4).toString());
                    error.add(toTable.get(i).get(5).toString());
                }

                Intent t = new Intent(multipleRoots.this, multipleRootsTable.class);
                t.putExtra("n", n);
                t.putExtra("x",x);
                t.putExtra("fx", fx);
                t.putExtra("dfx", dfx);
                t.putExtra("ddfx", ddfx);
                t.putExtra("error", error);
                startActivity(t);
            }
        });
    }
}

