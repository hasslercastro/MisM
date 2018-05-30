package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.MathContext;

import methods.RelaxedGauss;
import methods.RelaxedJacobi;

public class LinearEquationIterativeRelaxed extends Activity {

    int iterationsM;
    Double toleranceM;
    Double weightM;
    ArrayList<ArrayList<Double>> resultTable = new ArrayList<>();
    ArrayList<String> arrayIterations = new ArrayList<>();
    ArrayList<String> arrayDelta = new ArrayList<>();
    ArrayList<String> arrayX = new ArrayList<>();
    BigDecimal value;
    MathContext mc = new MathContext(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iterative_matrix_relaxed);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final int rows = getIntent().getExtras().getInt("size");
        final int columns = getIntent().getExtras().getInt("size");
        final String method = getIntent().getExtras().getString("method");

        final AlertDialog alertDialog = new AlertDialog.Builder(LinearEquationIterativeRelaxed.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Input Error");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        TextView title = (TextView) findViewById(R.id.title_iterative_r);
        title.setText(method);
        title.setTextSize(30);
        title.setGravity(Gravity.CENTER);
        final TableLayout A = (TableLayout) findViewById(R.id.matrixT_iterative);
        final TableLayout b = (TableLayout) findViewById(R.id.b_iterative_r);
        final TableLayout x = (TableLayout) findViewById(R.id.x);
        final TableLayout x0 = (TableLayout) findViewById(R.id.x0_iterative_r);
        final EditText tolerance = (EditText) findViewById(R.id.editTextTolerance);
        final EditText iterations = (EditText) findViewById(R.id.editTextIterations);
        final EditText weight = (EditText) findViewById(R.id.editTextWeight);


        for (int i = 0; i < rows; i++) {
            TableRow row = new TableRow(this);
            EditText position = new EditText(this);
            position.setText(" 0 ");
            position.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            row.addView(position);
            b.addView(row, i);

        }

        for (int i = 0; i < rows; i++) {
            TableRow row = new TableRow(this);
            EditText position = new EditText(this);
            position.setText(" 0 ");
            position.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            row.addView(position);
            x0.addView(row, i);

        }


        for (int i = 0; i < rows; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < columns; j++) {
                EditText position = new EditText(this);
                position.setText(" 0 ");
                position.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                row.addView(position);
            }
            A.addView(row, i);
        }

        final ArrayList<String> sol = new ArrayList<String>();
        final double[][] res_A = new double[rows][columns];
        final double[][] L = new double[rows][columns];
        final double[][] U = new double[rows][columns];

        final Button table = (Button) findViewById(R.id.table_iterative);
        table.setEnabled(false);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayIterations.clear();
                arrayDelta.clear();
                arrayX.clear();
                for (int i = 0; i < resultTable.size(); i++) {
                    arrayIterations.add(resultTable.get(i).get(0).toString());
                    arrayDelta.add(resultTable.get(i).get(resultTable.get(i).size() - 1).toString());
                    for (int j = 1; j < resultTable.get(i).size() - 1; j++) {
                        value = new BigDecimal(resultTable.get(i).get(j));
                        value = value.round(mc);
                        arrayX.add(value.toString());
                    }
                }
                Intent t = new Intent(LinearEquationIterativeRelaxed.this, IterativeMatrixMethodsTable.class);
                t.putExtra("iterations", arrayIterations);
                t.putExtra("delta", arrayDelta);
                t.putExtra("x", arrayX);
                t.putExtra("size", getIntent().getExtras().getInt("size"));
                startActivity(t);

            }
        });
        Button run = (Button) findViewById(R.id.run_iterative);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double[] solution;
                    double[][] A_matrix = new double[rows][columns];
                    double[] vect_b = new double[rows];
                    double[] vect_x0 = new double[rows];

                    for (int i = 0; i < rows; i++) {
                        TableRow row_r = (TableRow) b.getChildAt(i);
                        EditText temp = (EditText) row_r.getChildAt(0);
                        vect_b[i] = Double.valueOf(temp.getText().toString());
                    }
                    for (int i = 0; i < rows; i++) {
                        TableRow row_rx0 = (TableRow) x0.getChildAt(i);
                        EditText temp_x0 = (EditText) row_rx0.getChildAt(0);
                        vect_x0[i] = Double.valueOf(temp_x0.getText().toString());
                        Log.d("Valoooor de x0", String.valueOf(Double.valueOf(temp_x0.getText().toString())));
                    }
                    for (int i = 0; i < rows; i++) {
                        TableRow row = (TableRow) A.getChildAt(i);
                        for (int j = 0; j < rows; j++) {
                            EditText temp = (EditText) row.getChildAt(j);
                            A_matrix[i][j] = Double.valueOf(temp.getText().toString());
                        }
                    }
                    iterationsM = Integer.parseInt(iterations.getText().toString());
                    toleranceM = Double.parseDouble(tolerance.getText().toString());
                    weightM = Double.parseDouble(weight.getText().toString());
                    switch (method) {

                        case "Relaxed Jacobi":
                            RelaxedJacobi reja = new RelaxedJacobi(A_matrix, vect_b, vect_x0, toleranceM, iterationsM, weightM);
                            resultTable = reja.getTable();
                            table.setEnabled(true);
                            break;
                        case "Relaxed Gauss Seidel":
                            RelaxedGauss rega = new RelaxedGauss(A_matrix, vect_b, vect_x0, toleranceM, iterationsM, weightM);
                            resultTable = rega.getTable();
                            table.setEnabled(true);
                            break;
                        default:
                            solution = new double[]{-1, -1, -1};
                    }
                }catch (Exception e){
                    alertDialog.show();
                }

            }
        });
    }
}