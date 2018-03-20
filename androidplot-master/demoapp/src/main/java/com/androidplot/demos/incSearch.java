package com.androidplot.demos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import methods.IncrementalSearch;

/**
 * Created by Hassler on 17/03/2018.
 */

public class incSearch extends Activity {

    //    editText9 Initial point
    // editText5 function
    //editText8 step
    //Text view  20 B
    //Text view 21 A

    //Buton4 run


    ArrayList<ArrayList<Double>> toTable =  new ArrayList<>();
    ArrayList<String> arrayN = new ArrayList();
    ArrayList<String> arrayXn = new ArrayList();
    ArrayList<String> arrayGXn = new ArrayList();
    ArrayList<String> arrayError = new ArrayList();

    EditText initialPoint,function,step,nIter;
    TextView msg;
    Double stepTo;
    BigDecimal initialPointTo;
    int nIterTo;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incremental_search);

        function = (EditText) findViewById(R.id.editText5);
        initialPoint = (EditText) findViewById(R.id.editText9);
        step = (EditText) findViewById(R.id.editText8);
        nIter = (EditText) findViewById(R.id.editText10);


        msg = (TextView) findViewById(R.id.textView20);


        Button incremental = (Button) findViewById(R.id.button4);
        incremental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialPointTo = BigDecimal.valueOf(Double.parseDouble(initialPoint.getText().toString()));
                stepTo = Double.parseDouble(step.getText().toString());
                nIterTo = Integer.parseInt(nIter.getText().toString());
                IncrementalSearch icSearch = new IncrementalSearch(function.getText().toString(), initialPointTo, stepTo, nIterTo);
                icSearch.incrementalSearch();
                msg.setMovementMethod(new ScrollingMovementMethod());
                msg.setText(icSearch.getMessage());

            }
        });

        Button clear = (Button) findViewById(R.id.button5);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                function.setText("");
                initialPoint.setText("");
                step.setText("");
                nIter.setText("");
                msg.setText("Answer");

            }
        });


        final Button table = (Button) findViewById(R.id.button7);
        table.setEnabled(false);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayN.clear();
                arrayN.clear();
                arrayGXn.clear();
                arrayError.clear();
                for(int i = 0 ; i < toTable.size() ; i++ ){
                    arrayN.add(toTable.get(i).get(0).toString());
                    arrayXn.add(toTable.get(i).get(1).toString());
                    arrayGXn.add(toTable.get(i).get(2).toString());
                    arrayError.add(toTable.get(i).get(3).toString());
                }
                Intent t = new Intent(incSearch.this, TableBiseccionFalsePosition.class);
                t.putExtra("n", arrayN);
                t.putExtra("Xn", arrayXn);
                t.putExtra("GXn", arrayGXn);
                t.putExtra("error", arrayError);
                startActivity(t);

            }
        });




    }
}
