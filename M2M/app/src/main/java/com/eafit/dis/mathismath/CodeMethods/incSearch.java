package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.math.BigDecimal;
import java.util.ArrayList;

import methods.IncrementalSearch;

public class incSearch extends Activity {

    ArrayList<ArrayList<Double>> toTable = new ArrayList<>();
    ArrayList<String> arrayN = new ArrayList();
    ArrayList<String> arrayXi = new ArrayList();
    ArrayList<String> arrayXf = new ArrayList();
    ArrayList<String> arrayFXi = new ArrayList();
    ArrayList<String> arrayFXf = new ArrayList();

    EditText initialPoint, function, step, nIter;
    TextView msg;
    Double stepTo;
    BigDecimal initialPointTo;
    int nIterTo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incremental_search);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        function = (EditText) findViewById(R.id.editText5);
        initialPoint = (EditText) findViewById(R.id.editText9);
        initialPoint.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        step = (EditText) findViewById(R.id.editText8);
        step.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        nIter = (EditText) findViewById(R.id.editText10);
        nIter.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        msg = (TextView) findViewById(R.id.textView20);

        final AlertDialog alertDialog = new AlertDialog.Builder(incSearch.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Input Error");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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
                for (int i = 0; i < toTable.size(); i++) {
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

        Button incremental = (Button) findViewById(R.id.button4);
        incremental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    initialPointTo = BigDecimal.valueOf(Double.parseDouble(initialPoint.getText().toString()));
                    stepTo = Double.parseDouble(step.getText().toString());
                    nIterTo = Integer.parseInt(nIter.getText().toString());
                    IncrementalSearch iSearch = new IncrementalSearch(function.getText().toString(), initialPointTo, stepTo, nIterTo);
                    toTable = iSearch.incrementalSearch();
                    msg.setMovementMethod(new ScrollingMovementMethod());
                    msg.setText(iSearch.getMessage());
                    table.setEnabled(true);
                } catch (Exception e) {
                    alertDialog.show();
                }

            }
        });

        Button grph = (Button) findViewById(R.id.button8);
        grph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent t = new Intent(incSearch.this, GraphFromMethods.class);
                    t.putExtra("function", function.getText().toString());
                    startActivity(t);
                } catch (Exception e) {
                    Log.d("This is the error", e.toString());
                    alertDialog.show();
                }
            }
        });

        Button help = findViewById(R.id.help_button);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(incSearch.this, PopHelp.class);
                intent.putExtra("help", "To guarantee the existence of a root the function must fulfill 2 conditions:\n\n\n" +
                        "→The function must be continuous in the interval [a, b].\n\n" +
                        "→The function evaluated at the extremes of the interval must have a sign change (f (a) * f (b) <0).\n");
                startActivity(intent);
            }
        });
    }
}
