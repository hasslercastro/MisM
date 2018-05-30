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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final int rows = getIntent().getExtras().getInt("size");
        final int columns = getIntent().getExtras().getInt("size");
        ;

        final String method = getIntent().getExtras().getString("method");
        final String[] helpText = new String[2];
        helpText[1] = getIntent().getExtras().getString("help");

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(method);
        title.setTextSize(30);
        title.setGravity(Gravity.CENTER);
        final TableLayout A = (TableLayout) findViewById(R.id.matrixT);
        final TableLayout b = (TableLayout) findViewById(R.id.b);
        final TableLayout x = (TableLayout) findViewById(R.id.x);



        switch (method) {
            case "Simple Gauss":

                helpText[0]= "Simple Gaussian elimination will fail:\n\n" +
                        "- If the determinant is zero" +
                        "- If any row is a linear combination of the remaining rows" +
                        "\n" +
                        "If you have checked the conditions above and still \n having problems , Partial pivoting might help";
                break;

            case "Partial Pivoting":
                helpText[0]= "Gaussian elimination with Partial pivoting will fail:\n\n" +
                        "- If the determinant is zero" +
                        "- If any row is a linear combination of the remaining rows" +
                        "\n" +
                        "If you have checked the conditions above and still \n having problems , Total pivoting might help";
                break;

            case "Total Pivoting":

                helpText[0]= "Gaussian elimination with Total Pivoting will fail:\n\n" +
                        "- If the determinant is zero" +
                        "- If any row is a linear combination of the remaining rows" +
                        "\n" ;

                break;

            case "Doolittle":
                helpText[0]= "Doolitle\n\n" +
                        "Any square matrix A admits an LUP factorization. If A is invertible, \n" +
                        "then it admits an LU (or LDU) factorization if and only if all its leading \n" +
                        "principal minors are non-zero. If A is a singular matrix of rank k,\n" +
                        " then it admits an LU factorization if the first k leading principal \n" +
                        "minors are non-zero, although the converse is not true.";
                break;

            case "Crout":


                helpText[0]= "Crout\n\n" +
                        "Any square matrix A admits an LUP factorization. If A is invertible, \n" +
                        "then it admits an LU (or LDU) factorization if and only if all its leading \n" +
                        "principal minors are non-zero. If A is a singular matrix of rank k,\n" +
                        " then it admits an LU factorization if the first k leading principal \n" +
                        "minors are non-zero, although the converse is not true.";

                break;

            case "Cholesky":

                helpText[0]= "Cholesky\n\n" +
                        "Any square matrix A admits an LUP factorization. If A is invertible, \n" +
                        "then it admits an LU (or LDU) factorization if and only if all its leading \n" +
                        "principal minors are non-zero. If A is a singular matrix of rank k,\n" +
                        " then it admits an LU factorization if the first k leading principal \n" +
                        "minors are non-zero, although the converse is not true.";
                break;

            default:

                helpText[0] = "Visit the webpage for help";
        }





        final AlertDialog alertDialog = new AlertDialog.Builder(LinearEquation.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Input Error\n, Check if your system has a solution , or if it is a valid input");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        for(int i = 0 ; i < rows ; i++){
            TableRow row= new TableRow(this);
            EditText position = new EditText(this);
            position.setText(" 1 ");
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
                position.setText(String.valueOf("0"));
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
                try {
                    x.removeAllViews();
                    sol.clear();
                    double[] solution;
                    double[][] A_matrix = new double[rows][columns];
                    double[] vect_b = new double[rows];

                    double[][] A_temp, L_temp, U_temp;

                    for (int i = 0; i < rows; i++) {
                        TableRow row_r = (TableRow) b.getChildAt(i);
                        EditText temp = (EditText) row_r.getChildAt(0);
                        vect_b[i] = Double.valueOf(temp.getText().toString());
                    }
                    for (int i = 0; i < rows; i++) {
                        TableRow row = (TableRow) A.getChildAt(i);
                        for (int j = 0; j < rows; j++) {
                            EditText temp = (EditText) row.getChildAt(j);
                            A_matrix[i][j] = Double.valueOf(temp.getText().toString());
                        }
                    }

                    switch (method) {
                        case "Simple Gauss":
                            SimpleGauss sg = new SimpleGauss(A_matrix, vect_b);
                            solution = sg.getSolution();
                            A_temp = sg.getElimination();
                            for (int i = 0; i < A_temp.length; i++) {
                                for (int j = 0; j < A_temp.length; j++) {
                                    res_A[i][j] = A_temp[i][j];
                                }
                            }

                            for (int i = 0; i < solution.length; i++) {
                                sol.add(String.valueOf(solution[i]));
                            }

                            helpText[0]= "Gaussian elimination will fail:\n" +
                                    "- If the determinant is zero" +
                                    "- If any row is a linear combination of the remaining rows" +
                                    "\n" +
                                    "If you have checked the conditions above and still \n having problems , Partial pivoting might help";

                            break;
                        case "Partial Pivoting":
                            PartialPivoting pp = new PartialPivoting(A_matrix, vect_b);
                            solution = pp.getSolution();

                            A_temp = pp.getElimination();
                            for (int i = 0; i < A_temp.length; i++) {
                                for (int j = 0; j < A_temp.length; j++) {
                                    res_A[i][j] = A_temp[i][j];
                                }
                            }

                            for (int i = 0; i < solution.length; i++) {
                                sol.add(String.valueOf(solution[i]));
                            }
                            helpText[0]= "Gaussian elimination will fail:\n" +
                                    "- If the determinant is zero" +
                                    "- If any row is a linear combination of the remaining rows" +
                                    "\n" +
                                    "If you have checked the conditions above and still \n having problems , Total pivoting might help";
                            break;

                        case "Total Pivoting":
                            TotalPivoting tp = new TotalPivoting(A_matrix, vect_b);
                            solution = tp.getSolution();

                            A_temp = tp.getElimination();
                            for (int i = 0; i < A_temp.length; i++) {
                                for (int j = 0; j < A_temp.length; j++) {
                                    res_A[i][j] = A_temp[i][j];
                                }
                            }

                            for (int i = 0; i < solution.length; i++) {
                                sol.add(String.valueOf(solution[i]));
                            }

                            helpText[0]= "Gaussian elimination will fail:\n" +
                                    "- If the determinant is zero" +
                                    "- If any row is a linear combination of the remaining rows" +
                                    "\n" ;

                            break;

                        case "Doolittle":
                            Doolittle doo = new Doolittle(A_matrix, vect_b);
                            solution = doo.getSolution();
                            L_temp = doo.getL();
                            U_temp = doo.getU();
                            for (int i = 0; i < L.length; i++) {
                                for (int j = 0; j < L.length; j++) {
                                    L[i][j] = L_temp[i][j];
                                    U[i][j] = U_temp[i][j];
                                }
                            }
                            for (int i = 0; i < solution.length; i++) {
                                sol.add(String.valueOf(solution[i]));
                            }

                            helpText[0]= "Any square matrix A admits an LUP factorization. If A is invertible, \n" +
                                    "then it admits an LU (or LDU) factorization if and only if all its leading \n" +
                                    "principal minors are non-zero. If A is a singular matrix of rank k,\n" +
                                    " then it admits an LU factorization if the first k leading principal \n" +
                                    "minors are non-zero, although the converse is not true.";
                            break;

                        case "Crout":
                            Crout cr = new Crout(A_matrix, vect_b);
                            solution = cr.getSolution();
                            L_temp = cr.getL();
                            U_temp = cr.getU();
                            for (int i = 0; i < L.length; i++) {
                                for (int j = 0; j < L.length; j++) {
                                    L[i][j] = L_temp[i][j];
                                    U[i][j] = U_temp[i][j];
                                }
                            }
                            for (int i = 0; i < solution.length; i++) {
                                sol.add(String.valueOf(solution[i]));
                            }


                            helpText[0]= "Any square matrix A admits an LUP factorization. If A is invertible, \n" +
                                    "then it admits an LU (or LDU) factorization if and only if all its leading \n" +
                                    "principal minors are non-zero. If A is a singular matrix of rank k,\n" +
                                    " then it admits an LU factorization if the first k leading principal \n" +
                                    "minors are non-zero, although the converse is not true.";

                            break;

                        case "Cholesky":
                            Cholesky ch = new Cholesky(A_matrix, vect_b);
                            solution = ch.getSolution();
                            L_temp = ch.getL();
                            U_temp = ch.getU();
                            for (int i = 0; i < L.length; i++) {
                                for (int j = 0; j < L.length; j++) {
                                    L[i][j] = L_temp[i][j];
                                    U[i][j] = U_temp[i][j];
                                }
                            }
                            for (int i = 0; i < solution.length; i++) {
                                sol.add(String.valueOf(solution[i]));
                            }


                            helpText[0]= "Any square matrix A admits an LUP factorization. If A is invertible, \n" +
                                    "then it admits an LU (or LDU) factorization if and only if all its leading \n" +
                                    "principal minors are non-zero. If A is a singular matrix of rank k,\n" +
                                    " then it admits an LU factorization if the first k leading principal \n" +
                                    "minors are non-zero, although the converse is not true.";
                            break;

                        default:
                            solution = new double[]{-1, -1, -1};
                    }

                    for (int i = 0; i < rows; i++) {
                        TableRow row = new TableRow(LinearEquation.this);
                        EditText value = new EditText(LinearEquation.this);
                        value.setText(String.valueOf(solution[i]));
                        value.setEnabled(false);
                        row.addView(value);
                        x.addView(row, i);
                    }
                }catch (Exception e){
                    alertDialog.show();
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
                intent.putExtra("help", helpText[1] + helpText[0]);
                startActivity(intent);
            }


        });

    }


    public static double determinante(double[][] matriz)
    {
        double det;
        if(matriz.length==2)
        {
            det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }
        double suma=0;
        for(int i=0; i<matriz.length; i++){
            double[][] nm=new double[matriz.length-1][matriz.length-1];
            for(int j=0; j<matriz.length; j++){
                if(j!=i){
                    for(int k=1; k<matriz.length; k++){
                        int indice=-1;
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;
                        nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0)
                suma+=matriz[i][0] * determinante(nm);
            else
                suma-=matriz[i][0] * determinante(nm);
        }
        return suma;
    }
}
