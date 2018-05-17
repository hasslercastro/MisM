package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.util.ArrayList;

import methods.FixedPoint;

/**
 * Created by Hassler on 15/03/2018.
 */




public class fixedPointView extends Activity {

    EditText function, initialPoint, tolerance, niter;
    TextView root;
    Double toleranceTo;
    //BigDecimal initialPointTo;
    int niterTo;
    ArrayList<ArrayList<Double>> toTable =  new ArrayList<>();
    ArrayList<String> arrayN = new ArrayList();
    ArrayList<String> arrayXn = new ArrayList();
    ArrayList<String> arrayGXn = new ArrayList();
    ArrayList<String> arrayError = new ArrayList();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixed_point);

        function = (EditText) findViewById(R.id.editText6);
        initialPoint = (EditText) findViewById(R.id.editText7);
        tolerance = (EditText) findViewById(R.id.editText11);
        niter = (EditText) findViewById(R.id.editText12);


        root = (TextView) findViewById(R.id.textView14);


        final Button table = (Button) findViewById(R.id.button7);
        table.setEnabled(false);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayN.clear();
                arrayN.clear();
                arrayGXn.clear();
                arrayError.clear();
                for(int i = 0 ; i < toTable.size() ; i++ ){
                    arrayN.add(toTable.get(i).get(0).toString());
                    arrayXn.add(toTable.get(i).get(1).toString());
                    arrayGXn.add(toTable.get(i).get(2).toString());
                    arrayError.add(toTable.get(i).get(3).toString());
                }
                Intent t = new Intent(fixedPointView.this, TablaNewtonFixedPoint.class);
                t.putExtra("n", arrayN);
                t.putExtra("Xn", arrayXn);
                t.putExtra("GXn", arrayGXn);
                t.putExtra("error", arrayError);
                startActivity(t);

            }
        });



        final AlertDialog alertDialog = new AlertDialog.Builder(fixedPointView.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Input Error");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        final Button fixed = (Button) findViewById(R.id.button3);
        fixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                niterTo = Integer.parseInt(niter.getText().toString());
                toleranceTo = Double.parseDouble(tolerance.getText().toString());
                FixedPoint fx = new FixedPoint(toleranceTo, initialPoint.getText().toString() , niterTo, function.getText().toString());
                toTable = fx.eval();
                root.setText(fx.getMessage());
                table.setEnabled(true);
                }catch(Exception e){
                    alertDialog.show();
                }
            }
        });

        Button clear = (Button) findViewById(R.id.button6);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                table.setEnabled(false);
                function.setText("");
                initialPoint.setText("");
                tolerance.setText("");
                niter.setText("");
                root.setText("Root");

            }
        });

        Button grph = (Button) findViewById(R.id.button8);
        grph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent t = new Intent(fixedPointView.this, GraphFromMethods.class);
                    t.putExtra("function", function.getText().toString());
                    startActivity(t);
                }catch (Exception e){
                    Log.d("This is the error", e.toString());
                    alertDialog.show();
                }
            }
        });

        Button help = findViewById(R.id.help_button);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fixedPointView.this, PopHelp.class);
                intent.putExtra("help", "A good fixed point function must meet 3 conditions to ensure that it converges to a single fixed point:\n\n" +
                        "\n" +
                        "→ g must be a continuous function in the interval [a, b].\n\n" +
                        "→ g (x) ∈ [a, b] for all x ∈ [a, b].\n\n" +
                        "→ g'(x) exists in every element of \t[a, b] with the property |g'(x)| ≤ k < 1 for all x ∈ [a, b].");
                startActivity(intent);
            }
        });
    }
}
