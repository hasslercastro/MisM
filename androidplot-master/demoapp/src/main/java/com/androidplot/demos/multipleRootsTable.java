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

public class multipleRootsTable extends Activity {
    ArrayList<String> n, Xn, GXn, dfx, ddfx , error;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bisection_false_position_table);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        n = getIntent().getExtras().getStringArrayList("n");
        Xn = getIntent().getExtras().getStringArrayList("x");
        GXn = getIntent().getExtras().getStringArrayList("fx");
        dfx = getIntent().getExtras().getStringArrayList("dfx");
        ddfx = getIntent().getExtras().getStringArrayList("ddfx");
        error = getIntent().getExtras().getStringArrayList("error");


        init();

    }

    public void init() {

        TableLayout ll = (TableLayout) findViewById(R.id.TablelayoutBisection);

        for (int j = 0; j < n.size(); j++) {
            TableRow row = new TableRow(this);
            TextView vistaN = new TextView(this);
            vistaN.setWidth(250);
            vistaN.setHeight(70);
            vistaN.setGravity(0x00000001);
            vistaN.setText(n.get(j));
            row.addView(vistaN);

            TextView vistaXn = new TextView(this);
            vistaXn.setWidth(250);
            vistaXn.setHeight(70);
            vistaXn.setGravity(0x00000001);
            vistaXn.setText(Xn.get(j));
            row.addView(vistaXn);

            TextView vistaGxn = new TextView(this);
            vistaGxn.setWidth(250);
            vistaGxn.setHeight(70);
            vistaGxn.setGravity(0x00000001);
            vistaGxn.setText(GXn.get(j));
            row.addView(vistaGxn);

            TextView vistadFxn = new TextView(this);
            vistadFxn.setWidth(250);
            vistadFxn.setHeight(70);
            vistadFxn.setGravity(0x00000001);
            vistadFxn.setText(dfx.get(j));
            row.addView(vistadFxn);

            TextView vistaddFxn = new TextView(this);
            vistaddFxn.setWidth(250);
            vistaddFxn.setHeight(70);
            vistaddFxn.setGravity(0x00000001);
            vistaddFxn.setText(ddfx.get(j));
            row.addView(vistaddFxn);

            TextView vistaError = new TextView(this);
            vistaError.setWidth(250);
            vistaError.setHeight(70);
            vistaError.setGravity(0x00000001);
            vistaError.setText(error.get(j));
            row.addView(vistaError);

            ll.addView(row, j);

        }

    }

}