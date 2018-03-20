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
    ArrayList<String> arrayXi = new ArrayList();
    ArrayList<String> arrayXf = new ArrayList();
    ArrayList<String> arrayFXi = new ArrayList();
    ArrayList<String> arrayFXf = new ArrayList();

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
                IncrementalSearch iSearch = new IncrementalSearch(function.getText().toString(), initialPointTo, stepTo, nIterTo);
                iSearch.incrementalSearch();
                msg.setMovementMethod(new ScrollingMovementMethod());
                msg.setText(iSearch.getMessage());

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


        final Button table = (Button) findViewById(R.id.table);
        table.setEnabled(false);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayN.clear();
                arrayXi.clear();
                arrayXf.clear();
                arrayFXi.clear();
                arrayFXf.clear();
                for(int i = 0 ; i < toTable.size() ; i++ ){
                    arrayN.add(toTable.get(i).get(0).toString());
                    arrayXi.add(toTable.get(i).get(1).toString());
                    arrayXf.add(toTable.get(i).get(2).toString());
                    arrayFXi.add(toTable.get(i).get(3).toString());
                    arrayFXf.add(toTable.get(i).get(4).toString());
                }
                Intent t = new Intent(incSearch.this, IncrementalTable.class);
                t.putExtra("n", arrayN);
                t.putExtra("Xi", arrayXi);
                t.putExtra("Xf", arrayXf);
                t.putExtra("FXi", arrayFXi);
                t.putExtra("FXf", arrayFXf);
                startActivity(t);

            }
        });
    }
}
