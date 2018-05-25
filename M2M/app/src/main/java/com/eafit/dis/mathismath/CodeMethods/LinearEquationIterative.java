package com.eafit.dis.mathismath.CodeMethods;

import android.app.ActionBar;
import android.app.Activity;
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

import methods.Jacobi;
import methods.GaussSeidel;
import methods.RelaxedJacobi;
import methods.RelaxedGauss;

public class LinearEquationIterative extends Activity {

    int iterationsM;
    Double toleranceM;
    ArrayList<ArrayList<Double>> resultTable =  new ArrayList<>();
    ArrayList<String> arrayIterations = new ArrayList<>();
    ArrayList<String> arrayDelta = new ArrayList<>();
    ArrayList<String> arrayX = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iterative_matrix);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final int rows= getIntent().getExtras().getInt("size");
        final int columns= getIntent().getExtras().getInt("size");;
        final String method = getIntent().getExtras().getString("method");

        TextView title = (TextView) findViewById(R.id.title_iterative);
        title.setText(method);
        title.setTextSize(30);
        title.setGravity(Gravity.CENTER);
        final TableLayout A = (TableLayout) findViewById(R.id.matrixT_iterative);
        final TableLayout b = (TableLayout) findViewById(R.id.b_iterative);
        final TableLayout x = (TableLayout) findViewById(R.id.x);
        final TableLayout x0 = (TableLayout) findViewById(R.id.x0_iterative);
        final EditText tolerance = (EditText) findViewById(R.id.editTextTolerance);
        final EditText iterations = (EditText) findViewById(R.id.editTextIterations);


        for(int i = 0 ; i < rows ; i++){
            TableRow row= new TableRow(this);
            EditText position = new EditText(this);
            position.setText(" 0 ");
            position.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            row.addView(position);
            b.addView(row,i);

        }

        for(int i = 0 ; i < rows ; i++){
            TableRow row= new TableRow(this);
            EditText position = new EditText(this);
            position.setText(" 0 ");
            position.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            row.addView(position);
            x0.addView(row,i);

        }


        for (int i=0;i<rows;i++)
        {
            TableRow row= new TableRow(this);
            for (int j=0;j<columns;j++)
            {
                EditText position = new EditText(this);
                position.setText(" 0 ");
                position.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                row.addView(position);
            }
            A.addView(row,i);
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
                for(int i = 0 ; i < resultTable.size() ; i++ ){
                    arrayIterations.add(resultTable.get(i).get(0).toString());
                    arrayDelta.add(resultTable.get(i).get(resultTable.get(i).size()-1).toString());
                    for (int j = 1; j < resultTable.get(i).size()-1; j++){
                        arrayX.add(resultTable.get(i).get(j).toString());
                    }
                }
                Intent t = new Intent(LinearEquationIterative.this, IterativeMatrixMethodsTable.class);
                t.putExtra("iterations", arrayIterations);
                t.putExtra("delta", arrayDelta);
                t.putExtra("x", arrayX);
                //t.putExtra("size",arrayX.size());
                t.putExtra("size",getIntent().getExtras().getInt("size"));
                startActivity(t);

            }
        });
        Button run = (Button) findViewById(R.id.run_iterative);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double[] solution;
                double[][] A_matrix = new double[rows][columns];
                double[] vect_b = new double[rows];
                double[] vect_x0 = new double[rows];

                for(int i = 0 ; i < rows ; i++){
                    TableRow row_r = (TableRow) b.getChildAt(i);
                    EditText temp = (EditText) row_r.getChildAt(0);
                    vect_b[i] = Double.valueOf(temp.getText().toString());
                }
                for(int i = 0 ; i < rows ; i++){
                    TableRow row_rx0 = (TableRow) x0.getChildAt(i);
                    EditText temp_x0 = (EditText) row_rx0.getChildAt(0);
                    vect_x0[i] = Double.valueOf(temp_x0.getText().toString());
                    Log.d("Valoooor de x0",String.valueOf(Double.valueOf(temp_x0.getText().toString())));
                }
                for(int i = 0 ; i < rows ; i++){
                    TableRow row = (TableRow) A.getChildAt(i);
                    for(int j = 0 ; j < rows ; j++){
                        EditText temp = (EditText) row.getChildAt(j);
                        A_matrix[i][j] = Double.valueOf(temp.getText().toString());
                    }
                }
                iterationsM = Integer.parseInt(iterations.getText().toString());
                toleranceM = Double.parseDouble(tolerance.getText().toString());
                //temp borrar luego junto con los parametros de  gauss seidel y jacobi
                double[] arr = {1,2,3};
                switch (method){

                    case "Jacobi":
                        Jacobi jab = new Jacobi(A_matrix, vect_b, vect_x0, toleranceM, iterationsM);
                        resultTable = jab.getTable();
                        table.setEnabled(true);
                        break;
                    case "Gauss Seidel":
                        GaussSeidel gau = new GaussSeidel(A_matrix, vect_b, vect_x0, toleranceM, iterationsM);
                        resultTable = gau.getTable();
                        table.setEnabled(true);
                    default:
                        solution = new double[] {-1, -1, -1};
                }
            }
        });
    }
}