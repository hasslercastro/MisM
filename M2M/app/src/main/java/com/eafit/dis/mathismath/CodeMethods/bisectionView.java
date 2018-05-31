/**
 * Created by Hassler on 14/03/2018.
 */

package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;

import methods.Bisection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.util.ArrayList;

public class bisectionView extends Activity {
    EditText limA, limB, function, tolerance;
    TextView root;
    Double limATo, limBTo, Tolerance;
    ArrayList<ArrayList<Double>> toTable = new ArrayList<>();
    ArrayList<String> arrayN = new ArrayList();
    ArrayList<String> arrayXn = new ArrayList();
    ArrayList<String> arrayGXn = new ArrayList();
    ArrayList<String> arrayError = new ArrayList();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bisection_cel_view);

        function = (EditText) findViewById(R.id.editText);
        tolerance = (EditText) findViewById(R.id.editText2);
        limA = (EditText) findViewById(R.id.editText3);
        limA.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        limB = (EditText) findViewById(R.id.editText4);
        limB.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        root = (TextView) findViewById(R.id.textView9);

        final AlertDialog alertDialog = new AlertDialog.Builder(bisectionView.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Input Error");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        Button clear = (Button) findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root.setText("");
                limA.setText("");
                limB.setText("");
                function.setText("");
                tolerance.setText("");
            }
        });

        final Button table = (Button) findViewById(R.id.table);
        table.setEnabled(false);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayN.clear();
                arrayN.clear();
                arrayGXn.clear();
                arrayError.clear();
                for (int i = 0; i < toTable.size(); i++) {
                    arrayN.add(String.valueOf(toTable.get(i).get(0).intValue()));
                    arrayXn.add(toTable.get(i).get(3).toString());
                    arrayGXn.add(toTable.get(i).get(4).toString());
                    arrayError.add(toTable.get(i).get(5).toString());
                }
                Intent t = new Intent(bisectionView.this, TableBiseccionFalsePosition.class);
                t.putExtra("iterations", arrayN);
                t.putExtra("xm", arrayXn);
                t.putExtra("fxm", arrayGXn);
                t.putExtra("error", arrayError);
                startActivity(t);
            }
        });

        final Button bisection = (Button) findViewById(R.id.button);
        bisection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    limATo = Double.parseDouble(limA.getText().toString());
                    limBTo = Double.parseDouble(limB.getText().toString());
                    Tolerance = Double.parseDouble(tolerance.getText().toString());
                    Bisection bisection = new Bisection(function.getText().toString(), limATo, limBTo, Tolerance, 100);
                    toTable = bisection.bisection();
                    root.setText(bisection.getMessage());
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
                    Intent t = new Intent(bisectionView.this, GraphFromMethods.class);
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
                Intent intent = new Intent(bisectionView.this, PopHelp.class);
                intent.putExtra("help", "To guarantee the existence of a root the function must fulfill 2 conditions:\n\n\n" +
                        "→The function must be continuous in the interval [a, b].\n\n" +
                        "→The function evaluated at the extremes of the interval must have a sign change (f (a) * f (b) <0).\n");
                startActivity(intent);
            }
        });

    }
}
