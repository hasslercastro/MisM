package com.androidplot.demos;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hassler on 21/03/2018.
 */

public class TableNewton extends Activity {
    ArrayList<String> iter,x, fx, dfx, error;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bisection_false_position_table);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        iter = getIntent().getExtras().getStringArrayList("iter");
        x = getIntent().getExtras().getStringArrayList("x");
        fx = getIntent().getExtras().getStringArrayList("fx");
        dfx = getIntent().getExtras().getStringArrayList("dfx");
        error = getIntent().getExtras().getStringArrayList("error");


        init();

    }

    public void init(){

        TableLayout ll = (TableLayout) findViewById(R.id.TablelayoutBisection);
        TableRow rowHeaded = new TableRow(this);
        TextView iteration = new TextView(this);
        iteration.setWidth(250);
        iteration.setHeight(80);
        iteration.setGravity(0x00000001);
        iteration.setText("Iterations");
        rowHeaded.addView(iteration);

        TextView xi = new TextView(this);
        xi.setWidth(250);
        xi.setHeight(80);
        xi.setGravity(0x00000001);
        xi.setText("Xi");
        rowHeaded.addView(xi);

        TextView fXi = new TextView(this);
        fXi.setWidth(250);
        fXi.setHeight(80);
        fXi.setGravity(0x00000001);
        fXi.setText("f(Xi)");
        rowHeaded.addView(fXi);

        TextView dfxV = new TextView(this);
        dfxV.setWidth(250);
        dfxV.setHeight(80);
        dfxV.setGravity(0x00000001);
        dfxV.setText("dF(Xi)");
        rowHeaded.addView(dfxV);


        TextView errorV = new TextView(this);
        errorV.setWidth(250);
        errorV.setHeight(80);
        errorV.setGravity(0x00000001);
        errorV.setText("error");
        rowHeaded.addView(errorV);

        ll.addView(rowHeaded,0);




        for (int j = 0; j < x.size(); j++){
            TableRow row= new TableRow(this);
            TextView vistaN = new TextView(this);
            vistaN.setWidth(250);
            vistaN.setHeight(80);
            vistaN.setGravity(0x00000001);
            vistaN.setText("   " +iter.get(j));
            row.addView(vistaN);

            TextView vistaXn = new TextView(this);
            vistaXn.setWidth(250);
            vistaXn.setHeight(80);
            vistaXn.setGravity(0x00000001);
            vistaXn.setText("   " +x.get(j));
            row.addView(vistaXn);

            TextView vistaGxn = new TextView(this);
            vistaGxn.setWidth(250);
            vistaGxn.setHeight(80);
            vistaGxn.setGravity(0x00000001);
            vistaGxn.setText("   " +fx.get(j));
            row.addView(vistaGxn);

            TextView vistaError = new TextView(this);
            vistaError.setWidth(250);
            vistaError.setHeight(80);
            vistaError.setGravity(0x00000001);
            vistaError.setText("   " +dfx.get(j));
            row.addView(vistaError);


            TextView vistaFXf = new TextView(this);
            vistaFXf.setWidth(250);
            vistaFXf.setHeight(80);
            vistaFXf.setGravity(0x00000001);
            vistaFXf.setText("   " +error.get(j));
            row.addView(vistaFXf);

            ll.addView(row,j+1);

        }

    }







}


