package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.util.ArrayList;

/**
 * Created by Hassler on 17/03/2018.
 */

public class IterativeMatrixMethodsTable extends Activity {



    ArrayList<String> iterations,delta,x;
    int size, sizeAux;
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

    public void init(){
        Log.d("size", "init: "+size);
        TableLayout ll = (TableLayout) findViewById(R.id.myTableLayout);
        int i = 0;
        sizeAux = size;
            for (int j = 0; j < iterations.size(); j++){
                TableRow row= new TableRow(this);
                TextView vistaN = new TextView(this);
                vistaN.setGravity(0x00000001);
                vistaN.setText(iterations.get(j));
                Log.d("iteraciones table", iterations.get(j));
                row.addView(vistaN);

                while(i < size ){
                    Log.d("estoy entrando aqui???", "xd");
                    TextView vistaXi = new TextView(this);
                    vistaXi.setGravity(0x00000001);
                    vistaXi.setText(x.get(i));
                    Log.d("x table", x.get(i));
                    row.addView(vistaXi);
                    i = i + 1;
                }

                Log.d("mierda", "init: "+i);
                size+=sizeAux;

                TextView vistaXn = new TextView(this);
                vistaXn.setGravity(0x00000001);
                vistaXn.setText(delta.get(j));
                Log.d("delta table", delta.get(j));
                row.addView(vistaXn);




                ll.addView(row,j+1);

            }
    }
}
