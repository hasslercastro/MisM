/**
 * Created by Hassler on 14/03/2018.
 */

package com.androidplot.demos;

import methods.Bisection;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bisectionView extends Activity {

   // textView10;        //error
   // textView9;       //Root
    //editText3;      //A
   // editText4;       //B

    //editText; //fuction
    //editText1; //Tolerance

    EditText limA,limB,function,tolerance;
    TextView error, root;
    Double limATo,limBTo,Tolerance;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bisection_cel_view);

        function = (EditText) findViewById(R.id.editText);
        tolerance = (EditText) findViewById(R.id.editText2);
        limA = (EditText) findViewById(R.id.editText3);
        limB = (EditText) findViewById(R.id.editText4);

        error = (TextView) findViewById(R.id.textView10);
        root = (TextView) findViewById(R.id.textView9);



        Button bisection = (Button) findViewById(R.id.button);
        bisection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limATo = Double.parseDouble(limA.getText().toString());
                limBTo = Double.parseDouble(limB.getText().toString());
                Tolerance = Double.parseDouble(tolerance.getText().toString());
                Bisection bisection = new Bisection(function.getText().toString(),  limATo , limBTo , Tolerance,100);
                String a = bisection.bisection();
                root.setText(String.valueOf(bisection.getRoot()));

            }
        });

        Button clear = (Button) findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                root.setText("");
                limA.setText("");
                limB.setText("");
                error.setText("_____");
                function.setText("_____");


            }
        });




    }




}
