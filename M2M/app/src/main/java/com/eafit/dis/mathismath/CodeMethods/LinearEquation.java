package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;

import methods.Cholesky;
import methods.Crout;
import methods.Doolittle;
import methods.GaussSeidel;
import methods.Jacobi;
import methods.SimpleGauss;
import methods.PartialPivoting;
import methods.TotalPivoting;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import methods.SimpleGauss;

/**
 * Created by Hassler on 25/04/2018.
 */

public class LinearEquation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final int rows= getIntent().getExtras().getInt("size");
        final int columns= getIntent().getExtras().getInt("size");;
        final String method = getIntent().getExtras().getString("method");
    /**
        // GET THE MATRIX DIMENSIONS
        int rows=4;
        int columns=4;

        // INITIALISE YOUR GRID
        GridView grid=(GridView)findViewById(R.id.view);
        grid.setNumColumns(columns);

        // CREATE A LIST OF MATRIX OBJECT
        List<Matrix> matrixList=new ArrayList<>();

        // ADD SOME CONTENTS TO EACH ITEM
        for (int i=0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
            {
                matrixList.add(new Matrix(i,j));
            }
        }

        // CREATE AN ADAPTER  (MATRIX ADAPTER)
        MatrixAdapter adapter=new MatrixAdapter(getApplicationContext(),matrixList);

        // ATTACH THE ADAPTER TO GRID
        grid.setAdapter(adapter);


        adapter.getView(0 , grid, grid);
        //grid.getChildAt(0).findViewById(0).;


    } */
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(method);
        title.setTextSize(30);
        title.setGravity(Gravity.CENTER);
        final TableLayout A = (TableLayout) findViewById(R.id.matrixT);
        final TableLayout b = (TableLayout) findViewById(R.id.b);
        final TableLayout x = (TableLayout) findViewById(R.id.x);



        for(int i = 0 ; i < rows ; i++){
            TableRow row= new TableRow(this);
            EditText position = new EditText(this);
            position.setText(" 0 ");
            position.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            row.addView(position);
            b.addView(row,i);

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


        Button run = (Button) findViewById(R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x.removeAllViews();
                sol.clear();
                double[] solution;
                double[][] A_matrix = new double[rows][columns];
                double[] vect_b = new double[rows];

                double[][] A_temp,L_temp,U_temp;

                //double[][] res_A = new double[rows][columns];
                //double[][] L = new double[rows][columns];
                //double[][] U = new double[rows][columns];


                for(int i = 0 ; i < rows ; i++){
                    TableRow row_r = (TableRow) b.getChildAt(i);
                    EditText temp = (EditText) row_r.getChildAt(0);
                    vect_b[i] = Double.valueOf(temp.getText().toString());
                    Log.d("Valoooor",String.valueOf(Double.valueOf(temp.getText().toString())));
                }
                for(int i = 0 ; i < rows ; i++){
                    TableRow row = (TableRow) A.getChildAt(i);
                    //vect_b[i] = Double.valueOf(temp.getText().toString());
                    //Log.d("Valooooor",String.valueOf(Double.valueOf(temp.getText().toString())));
                    for(int j = 0 ; j < rows ; j++){
                        EditText temp = (EditText) row.getChildAt(j);
                        Log.d("Valor",String.valueOf(Double.valueOf(temp.getText().toString())));
                        A_matrix[i][j] = Double.valueOf(temp.getText().toString());
                    }
                }
                switch (method){
                    case "Simple Gauss":
                        SimpleGauss sg = new SimpleGauss(A_matrix, vect_b);
                        solution = sg.getSolution();
                        A_temp = sg.getElimination();
                        for(int i = 0; i < A_temp.length; i++) {
                            for (int j = 0; j  < A_temp.length ; j++){
                                res_A[i][j] = A_temp[i][j];
                            }
                        }

                        for (int i = 0 ; i < solution.length ; i++){
                            sol.add(String.valueOf(solution[i]));
                        }
                        break;
                    case "Partial Pivoting":
                        PartialPivoting pp = new PartialPivoting(A_matrix, vect_b);
                        solution = pp.getSolution();

                        A_temp = pp.getElimination();
                        for(int i = 0; i < A_temp.length; i++) {
                            for (int j = 0; j  < A_temp.length ; j++){
                                res_A[i][j] = A_temp[i][j];
                            }
                        }

                        for (int i = 0 ; i < solution.length ; i++){
                            sol.add(String.valueOf(solution[i]));
                        }
                        break;
                    case "Total Pivoting":
                        TotalPivoting tp = new TotalPivoting(A_matrix, vect_b);
                        solution = tp.getSolution();

                        A_temp = tp.getElimination();
                        for(int i = 0; i < A_temp.length; i++) {
                            for (int j = 0; j  < A_temp.length ; j++){
                                res_A[i][j] = A_temp[i][j];
                            }
                        }

                        for (int i = 0 ; i < solution.length ; i++){
                            sol.add(String.valueOf(solution[i]));
                        }
                        break;
                    case "Doolittle":
                        Doolittle doo = new Doolittle(A_matrix, vect_b);
                        solution = doo.getSolution();
                        L_temp = doo.getL();
                        U_temp = doo.getU();
                        for(int i = 0; i < L.length; i++) {
                            for (int j = 0; j  < L.length ; j++){
                                L[i][j] = L_temp[i][j];
                                U[i][j] = U_temp[i][j];
                            }
                        }
                        for (int i = 0 ; i < solution.length ; i++){
                            sol.add(String.valueOf(solution[i]));
                        }
                        break;
                    case "Crout":
                        Crout cr = new Crout(A_matrix, vect_b);
                        solution = cr.getSolution();
                        L_temp = cr.getL();
                        U_temp = cr.getU();
                        for(int i = 0; i < L.length; i++) {
                            for (int j = 0; j  < L.length ; j++){
                                L[i][j] = L_temp[i][j];
                                U[i][j] = U_temp[i][j];
                            }
                        }
                        for (int i = 0 ; i < solution.length ; i++){
                            sol.add(String.valueOf(solution[i]));
                        }
                        break;
                    case "Cholesky":
                        Cholesky ch = new Cholesky(A_matrix, vect_b);
                        solution = ch.getSolution();
                        L_temp = ch.getL();
                        U_temp = ch.getU();
                        for(int i = 0; i < L.length; i++) {
                            for (int j = 0; j  < L.length ; j++){
                                L[i][j] = L_temp[i][j];
                                U[i][j] = U_temp[i][j];
                            }
                        }
                        for (int i = 0 ; i < solution.length ; i++){
                            sol.add(String.valueOf(solution[i]));
                        }
                        break;

                    /**
                    case "Jacobi":
                        Jacobi jab = new Jacobi(A_matrix, vect_b);
                        solution = jab.getSolution();
                        for (int i = 0 ; i < solution.length ; i++){
                            sol.add(String.valueOf(solution[i]));
                        }
                        break;
                    case "Gauss Seidel":
                        GaussSeidel ga = new GaussSeidel(A_matrix, vect_b);
                        solution = ga.getSolution();
                        for (int i = 0 ; i < solution.length ; i++){
                            sol.add(String.valueOf(solution[i]));
                        }
                        break;*/
                    default:
                        solution = new double[] {-1, -1, -1};
                }

                for(int i = 0 ; i < rows ; i++){
                    TableRow row= new TableRow(LinearEquation.this);
                    EditText value  = new EditText(LinearEquation.this);
                    value.setText(String.valueOf(solution[i]));
                    value.setEnabled(false);
                    row.addView(value);
                    x.addView(row,i);
                }
            }
        });

        Button pop = (Button) findViewById(R.id.solve);
        pop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),  PopSolve.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("A" , res_A);
                intent.putExtra("A" , bundle);
                bundle.putSerializable("L" , L);
                intent.putExtra("L" , bundle);
                bundle.putSerializable("U" , U);
                intent.putExtra("U" , bundle);
                intent.putExtra("solution", sol);


                startActivity(intent);
            }


        });
    }
}