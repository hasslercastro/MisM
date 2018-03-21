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

import java.util.ArrayList;

import methods.Bisection;
import methods.FalsePosition;

/**
 * Created by Hassler on 15/03/2018.
 */

public class falsePositionView extends Activity {

    // textView10;        //error
    // textView9;       //Root
    //editText3;      //A
    // editText4;       //B

    //editText; //fuction
    //editText1; //Tolerance

    EditText limA,limB,function,tolerance;
    TextView root;
    Double limATo,limBTo,Tolerance;

    ArrayList<ArrayList<Double>> toTable =  new ArrayList<>();
    ArrayList<String> arrayN = new ArrayList();
    ArrayList<String> arrayXn = new ArrayList();
    ArrayList<String> arrayGXn = new ArrayList();
    ArrayList<String> arrayError = new ArrayList();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.false_position);

        function = (EditText) findViewById(R.id.editText2);
        tolerance = (EditText) findViewById(R.id.editText);
        limA = (EditText) findViewById(R.id.editText3);
        limB = (EditText) findViewById(R.id.editText4);


        root = (TextView) findViewById(R.id.textView9);


        final AlertDialog alertDialog = new AlertDialog.Builder(falsePositionView.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Input Error");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        Button bisection = (Button) findViewById(R.id.button);
        bisection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    limATo = Double.parseDouble(limA.getText().toString());
                    limBTo = Double.parseDouble(limB.getText().toString());
                    Tolerance = Double.parseDouble(tolerance.getText().toString());
                    FalsePosition fp = new FalsePosition(function.getText().toString(), limATo, limBTo, Tolerance, 100);
                    toTable = fp.eval();
                    root.setText(fp.getMessage());
                }catch (Exception e){
                    alertDialog.show();
                }

            }
        });

        Button clear = (Button) findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                root.setText("Root");
                limA.setText("");
                limB.setText("");
                function.setText("");
                tolerance.setText("");


            }
        });

        Button table = (Button) findViewById(R.id.table);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayN.clear();
                arrayN.clear();
                arrayGXn.clear();
                arrayError.clear();
                for(int i = 0 ; i < toTable.size() ; i++ ){
                    arrayN.add(String.valueOf(toTable.get(i).get(0).intValue()));
                    arrayXn.add(toTable.get(i).get(3).toString());
                    arrayGXn.add(toTable.get(i).get(4).toString());
                    arrayError.add(toTable.get(i).get(5).toString());
                }
                Intent t = new Intent(falsePositionView.this, TableBiseccionFalsePosition.class);
                t.putExtra("iterations", arrayN);
                t.putExtra("xm", arrayXn);
                t.putExtra("fxm", arrayGXn);
                t.putExtra("error", arrayError);
                startActivity(t);



            }
        });




    }




}

