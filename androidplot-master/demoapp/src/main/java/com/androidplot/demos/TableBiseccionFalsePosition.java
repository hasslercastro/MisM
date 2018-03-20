package com.androidplot.demos;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hassler on 18/03/2018.
 */

public class TableBiseccionFalsePosition  extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bisection_false_position_table);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        ArrayList<String> iterations= getIntent().getExtras().getStringArrayList("iterations");
        ArrayList<String> xm= getIntent().getExtras().getStringArrayList("xm");
        ArrayList<String> error= getIntent().getExtras().getStringArrayList("error");
        ArrayList<String> fxm= getIntent().getExtras().getStringArrayList("fxm");

        init(iterations, xm, error , fxm);


    }



    public void init(ArrayList<String> iter ,ArrayList<String> xm,ArrayList<String> error,ArrayList<String> fxm) {
        Log.d("mensaje", String.valueOf(iter.size()));


        TableLayout ll = (TableLayout) findViewById(R.id.TablelayoutBisection);
        TableRow rowHeaded = new TableRow(this);
        TextView iteration = new TextView(this);
        iteration.setWidth(250);
        iteration.setHeight(80);
        iteration.setGravity(0x00000001);
        iteration.setText("Iterations");
        rowHeaded.addView(iteration);

        TextView xM = new TextView(this);
        xM.setWidth(250);
        xM.setHeight(80);
        xM.setGravity(0x00000001);
        xM.setText("xm");
        rowHeaded.addView(xM);

        TextView fXm = new TextView(this);
        fXm.setWidth(250);
        fXm.setHeight(80);
        fXm.setGravity(0x00000001);
        fXm.setText("f(xm)");
        rowHeaded.addView(fXm);

        TextView err = new TextView(this);
        err.setWidth(250);
        err.setHeight(80);
        err.setGravity(0x00000001);
        err.setText("error");
        rowHeaded.addView(err);

        ll.addView(rowHeaded,0);




        for (int j = 0; j < iter.size(); j++){
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
            vistaXn.setText("   " +xm.get(j));
            row.addView(vistaXn);

            TextView vistaGxn = new TextView(this);
            vistaGxn.setWidth(250);
            vistaGxn.setHeight(80);
            vistaGxn.setGravity(0x00000001);
            vistaGxn.setText("   " +fxm.get(j));
            row.addView(vistaGxn);

            TextView vistaError = new TextView(this);
            vistaError.setWidth(250);
            vistaError.setHeight(80);
            vistaError.setGravity(0x00000001);
            vistaError.setText("   " +error.get(j));
            row.addView(vistaError);

            ll.addView(row,j+1);

        }

    }




}
