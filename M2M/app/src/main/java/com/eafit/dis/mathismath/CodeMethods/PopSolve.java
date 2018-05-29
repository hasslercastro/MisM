package com.eafit.dis.mathismath.CodeMethods;


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
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Hassler on 6/05/2018.
 */

public class PopSolve extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_solve);

        ArrayList<String> solution = getIntent().getStringArrayListExtra("solution");
        Bundle bundle = getIntent().getExtras();
        double[][] A = (double[][]) bundle.getSerializable("A");
        double[][] L = (double[][]) bundle.getSerializable("L");
        double[][] U = (double[][]) bundle.getSerializable("U");
        MathContext mc = new MathContext(4);
        String type = getIntent().getStringExtra("type");
        TableLayout x_vector = (TableLayout) findViewById(R.id.x_vector);
        int titleSize = 30;
        int elementSize = 20;

        if (type.equals("Simple Gauss") || type.equals("Partial Pivoting") || type.equals("Total Pivoting")) {

            TextView title_A = new TextView(this);
            title_A.setTextSize(titleSize);
            title_A.setText("A Matrix");
            x_vector.addView(title_A);
            BigDecimal value;
            String ans1 = "";
            for (int i = 0; i < solution.size(); i++) {
                TableRow row = new TableRow(this);
                String concat = "";
                for (int j = 0; j < solution.size(); j++) {
                    value = new BigDecimal(A[i][j]);
                    value = value.round(mc);
                    ans1 = String.format("%9.4f", value.floatValue());
                    concat = concat + ans1;
                }
                TextView tv = new TextView(this);
                tv.setText(concat);
                tv.setTextSize(elementSize);
                row.addView(tv);
                x_vector.addView(row);
            }

        } else {

            TextView title_L = new TextView(this);
            title_L.setTextSize(titleSize);
            title_L.setText("L Matrix");
            x_vector.addView(title_L);
            BigDecimal value2;
            String ans = "";
            for (int i = 0; i < solution.size(); i++) {
                TableRow row = new TableRow(this);
                String concat = "";

                for (int j = 0; j < solution.size(); j++) {
                    value2 = new BigDecimal(L[i][j]);
                    value2 = value2.round(mc);
                    ans = String.format("%9.4f", value2.floatValue());
                    concat = concat + ans;
                }
                TextView tv = new TextView(this);
                tv.setText(concat);
                tv.setTextSize(elementSize);
                row.addView(tv);
                x_vector.addView(row);

            }

            TextView title_U = new TextView(this);
            title_U.setTextSize(titleSize);
            title_U.setText("U Matrix");
            x_vector.addView(title_U);

            for (int i = 0; i < solution.size(); i++) {
                TableRow row = new TableRow(this);
                String concat = "";
                for (int j = 0; j < solution.size(); j++) {
                    value2 = new BigDecimal(U[i][j]);
                    value2 = value2.round(mc);
                    ans = String.format("%9.4f", value2.floatValue());
                    concat = concat + ans;
                }
                TextView tv = new TextView(this);
                tv.setText(concat);
                tv.setTextSize(elementSize);
                row.addView(tv);
                x_vector.addView(row);

            }
        }

        Log.d("size", String.valueOf(solution.size()));

        TextView x_vect = new TextView(this);
        x_vect.setTextSize(titleSize);
        x_vect.setText("Solution");
        x_vector.addView(x_vect);
        for (int i = 0; i < solution.size(); i++) {
            TableRow row = new TableRow(PopSolve.this);
            TextView value = new TextView(PopSolve.this);
            value.setTextSize(elementSize);
            value.setText("x" + i + ": " + solution.get(i));
            row.addView(value);
            x_vector.addView(row);
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

    }
}
