package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.util.ArrayList;

/**
 * Created by Hassler on 10/05/2018.
 */

public class Interpolation extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interpolation);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final ArrayList<String> x_array = new ArrayList<>();
        final ArrayList<String> y_array = new ArrayList<>();
        {


            TextView execute = (TextView) findViewById(R.id.execute);
            //Setear el texto dps
            final TableLayout table = (TableLayout) findViewById(R.id.data);
            final EditText x = (EditText) findViewById(R.id.x);
            final EditText y = (EditText) findViewById(R.id.y);


            Button add = (Button) findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    x_array.add(x.getText().toString());
                    y_array.add(y.getText().toString());
                    TableRow row = new TableRow(Interpolation.this);
                    EditText value = new EditText(Interpolation.this);
                    value.setText(x.getText().toString() + " , " + y.getText().toString());
                    row.addView(value);
                    table.addView(row);

                }
            });


        }


    }
}
