package com.eafit.dis.mathismath.CodeMethods;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.util.ArrayList;

/**
 * Created by Hassler on 6/05/2018.
 */

public class PopSolve extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_solve);

        ArrayList<String> solution = getIntent().getStringArrayListExtra("solution");
        TableLayout x_vector = (TableLayout) findViewById(R.id.x_vector);
        Log.d("size",  String.valueOf(solution.size()));

        for(int i = 0 ; i < solution.size() ; i++){
            TableRow row= new TableRow(PopSolve.this);
            TextView value  = new TextView(PopSolve.this);
            value.setText(solution.get(i));
            row.addView(value);
            x_vector.addView(row);

        }


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8) , (int) (height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

    }
}
