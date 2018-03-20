package com.androidplot.demos;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hassler on 20/03/2018.
 */

public class IncrementalTable extends Activity {

    ArrayList<String> arrayN, arrayXi, arrayXf ,arrayFXi ,arrayFXf;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bisection_false_position_table);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        arrayN = getIntent().getExtras().getStringArrayList("n");;
        arrayXi = getIntent().getExtras().getStringArrayList("Xi");
        arrayXf = getIntent().getExtras().getStringArrayList("Xf");
        arrayFXi = getIntent().getExtras().getStringArrayList("FXi");
        arrayFXf = getIntent().getExtras().getStringArrayList("FXf");
        init();


    }



    public void init() {


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
        xi.setText("xi");
        rowHeaded.addView(xi);

        TextView xf = new TextView(this);
        xf.setWidth(250);
        xf.setHeight(80);
        xf.setGravity(0x00000001);
        xf.setText("xf");
        rowHeaded.addView(xf);

        TextView FXi = new TextView(this);
        FXi.setWidth(250);
        FXi.setHeight(80);
        FXi.setGravity(0x00000001);
        FXi.setText("FXi");
        rowHeaded.addView(FXi);


        TextView FXf = new TextView(this);
        FXf.setWidth(250);
        FXf.setHeight(80);
        FXf.setGravity(0x00000001);
        FXf.setText("FXf");
        rowHeaded.addView(FXf);

        ll.addView(rowHeaded,0);




        for (int j = 0; j < arrayN.size(); j++){
            TableRow row= new TableRow(this);
            TextView vistaN = new TextView(this);
            vistaN.setWidth(250);
            vistaN.setHeight(80);
            vistaN.setGravity(0x00000001);
            vistaN.setText("   " +arrayN.get(j));
            row.addView(vistaN);

            TextView vistaXn = new TextView(this);
            vistaXn.setWidth(250);
            vistaXn.setHeight(80);
            vistaXn.setGravity(0x00000001);
            vistaXn.setText("   " +arrayXi.get(j));
            row.addView(vistaXn);

            TextView vistaGxn = new TextView(this);
            vistaGxn.setWidth(250);
            vistaGxn.setHeight(80);
            vistaGxn.setGravity(0x00000001);
            vistaGxn.setText("   " +arrayXf.get(j));
            row.addView(vistaGxn);

            TextView vistaError = new TextView(this);
            vistaError.setWidth(250);
            vistaError.setHeight(80);
            vistaError.setGravity(0x00000001);
            vistaError.setText("   " +arrayFXi.get(j));
            row.addView(vistaError);


            TextView vistaFXf = new TextView(this);
            vistaFXf.setWidth(250);
            vistaFXf.setHeight(80);
            vistaFXf.setGravity(0x00000001);
            vistaFXf.setText("   " +arrayFXf.get(j));
            row.addView(vistaFXf);

            ll.addView(row,j+1);

        }

    }




}


