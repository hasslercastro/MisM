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

        //final int rows= getIntent().getExtras().getInt("size");
        //final int columns= getIntent().getExtras().getInt("size");;
        final int rows =10;
        final int columns=10;
        final String method = getIntent().getExtras().getString("method");
        final String helpText = getIntent().getExtras().getString("help");

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
            position.setText(" 1 ");
            position.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            row.addView(position);
            b.addView(row,i);

        }



        double[][] ileg = {{9.1622 , 0.4505 ,   0.1067 ,   0.4314 ,   0.8530 ,   0.4173  ,  0.7803 ,   0.2348  ,  0.5470  ,  0.5470},
                {0.7943 ,   9.0838   , 0.9619   , 0.9106   , 0.6221 ,   0.0497    ,0.3897  ,  0.3532   , 0.2963   , 0.7757},
                {0.3112  ,  0.2290   , 9.0046  ,  0.1818  ,  0.3510 ,   0.9027   , 0.2417  ,  0.8212  ,  0.7447   , 0.4868},
                {0.5285    ,0.9133   , 0.7749    ,9.2638  ,  0.5132  ,  0.9448   , 0.4039   , 0.0154  ,  0.1890  ,  0.4359},
                {0.1656   , 0.1524   , 0.8173    ,0.1455  ,  9.4018  ,  0.4909   , 0.0965   , 0.0430  ,  0.6868  ,  0.4468},
                {0.6020   , 0.8258   , 0.8687    ,0.1361  ,  0.0760  ,  9.4893   , 0.1320  ,  0.1690   , 0.1835  ,  0.3063},
                {0.2630   , 0.5383   , 0.0844   , 0.8693  ,  0.2399  ,  0.3377   , 9.9421  ,  0.6491   , 0.3685  ,  0.5085},
                {0.6541   , 0.9961    ,0.3998   , 0.5797  ,  0.1233  ,  0.9001   , 0.9561  ,  9.7317   , 0.6256  ,  0.5108},
                { 0.6892   , 0.0782   , 0.2599   , 0.5499  ,  0.1839  ,  0.3692  ,  0.5752  ,  0.6477   , 9.7802  ,  0.8176},
                {10.0000   , 0.4427 ,   0.8001   , 0.1450 ,   0.2400 ,   0.1112  ,  0.0598 ,   0.4509   , 0.0811 ,  20.0000}};

        for (int i=0;i<rows;i++)
        {
            TableRow row= new TableRow(this);
            for (int j=0;j<columns;j++)
            {
                EditText position = new EditText(this);
                position.setText(String.valueOf(ileg[i][j]));
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
                    //Log.d("Valooooor puto el que lo lea xd",String.valueOf(Double.valueOf(temp.getText().toString())));
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
                intent.putExtra("A" , res_A);
                bundle.putSerializable("L" , L);
                intent.putExtra("L" , L);
                bundle.putSerializable("U" , U);
                intent.putExtra("U" , U);
                intent.putExtra("solution", sol);
                intent.putExtra("type", method);
                startActivity(intent);
            }


        });

        Button help = (Button) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),  PopHelp.class);
                intent.putExtra("msg",helpText);
                startActivity(intent);
            }


        });


    }
}