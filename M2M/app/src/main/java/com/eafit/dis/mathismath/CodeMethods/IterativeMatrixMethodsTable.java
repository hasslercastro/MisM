package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class IterativeMatrixMethodsTable extends Activity {


    ArrayList<String> iterations, delta, x;
    int size, sizeAux;
    BigDecimal value;
    MathContext mc = new MathContext(4);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iterative_matrix_methods_table);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        iterations = getIntent().getExtras().getStringArrayList("iterations");
        delta = getIntent().getExtras().getStringArrayList("delta");
        x = getIntent().getExtras().getStringArrayList("x");
        size = getIntent().getExtras().getInt("size");
        init();

    }

    public void init() {
        TableLayout ll = (TableLayout) findViewById(R.id.myTableLayout);
        int i = 0;
        sizeAux = size;

        TableRow rowAux = new TableRow(this);
        TextView textIterations = new TextView(this);
        textIterations.setWidth(250);
        textIterations.setHeight(100);
        textIterations.setGravity(0x00000001);
        textIterations.setText("Iterations");
        rowAux.addView(textIterations);

        //Add all Xi
        for(int k = 0; k < sizeAux; k++){
            TextView textXi = new TextView(this);
            textXi.setWidth(250);
            textXi.setHeight(100);
            textXi.setGravity(0x00000001);
            textXi.setText("X"+ String.valueOf(k+1));
            rowAux.addView(textXi);
        }

        TextView textDelta = new TextView(this);
        textDelta.setWidth(250);
        textDelta.setHeight(100);
        textDelta.setGravity(0x00000001);
        textDelta.setText("δ");
        rowAux.addView(textDelta);

        ll.addView(rowAux, 0);

        for (int j = 0; j < iterations.size(); j++) {
            TableRow row = new TableRow(this);
            TextView vistaN = new TextView(this);
            vistaN.setWidth(250);
            vistaN.setHeight(100);
            vistaN.setGravity(0x00000001);
            vistaN.setText(iterations.get(j));
            row.addView(vistaN);

            while (i < size) {
                TextView vistaXi = new TextView(this);
                vistaXi.setWidth(250);
                vistaXi.setHeight(100);
                vistaXi.setGravity(0x00000001);
                value = new BigDecimal(x.get(i));
                value = value.round(mc);
                vistaXi.setText(value.toString());
                row.addView(vistaXi);
                i = i + 1;
            }

            size += sizeAux;

            TextView vistaXn = new TextView(this);
            vistaXn.setWidth(250);
            vistaXn.setHeight(100);
            vistaXn.setGravity(0x00000001);
            value = new BigDecimal(delta.get(j));
            value = value.round(mc);
            vistaXn.setText(value.toString());
            row.addView(vistaXn);

            ll.addView(row, j + 1);

        }

    }
}
