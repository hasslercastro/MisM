package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.text.InputFilter;

import com.eafit.dis.mathismath.R;

/**
 * Created by Hassler on 5/05/2018.
 */

public class IterativeMethods extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iterative_methods);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final EditText size = (EditText) findViewById(R.id.sizeIterative);
        size.setFilters(new InputFilter[]{new CustomRangeInputFilter(1, 50)});
        size.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

        Button Jacobi = (Button) findViewById(R.id.Jacobi);
        Jacobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(IterativeMethods.this, LinearEquationIterative.class);
                intent.putExtra("method", "Jacobi");
                intent.putExtra("help","→ The standard convergence condition is when the spectral radius of the iteration matrix is less than 1\n\n→The matrix A is strictly or irreducibly diagonally dominant. Strict row diagonal dominance means that for each row, the absolute value of the diagonal term is greater than the sum of absolute values of other terms.\n\n→The Jacobi method sometimes converges even if these conditions are not satisfied.\n\n→The Jacobi method does not converge for every symmetric positive-definite matrix.\n");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button GaussSeidel = (Button) findViewById(R.id.GaussSeidel);
        GaussSeidel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(IterativeMethods.this, LinearEquationIterative.class);
                intent.putExtra("method", "Gauss Seidel");
                intent.putExtra("help","The convergence properties of the Gauss–Seidel method are dependent on the matrix A. Namely, the procedure is known to converge if either:\n\n→ A is symmetric positive-definite, or\n\n→ A is strictly or irreducibly diagonally dominant.\n\n→The standard convergence condition is when the spectral radius of the iteration matrix is less than 1.\n\n→The Gauss–Seidel method sometimes converges even if these conditions are not satisfied.\n");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button RelaxedJacobi = (Button) findViewById(R.id.Jacobi_Relaxed);
        RelaxedJacobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(IterativeMethods.this, LinearEquationIterativeRelaxed.class);
                intent.putExtra("method", "Relaxed Jacobi");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });

        Button RelaxedGaussSeidel = (Button) findViewById(R.id.GaussSeidel_Relaxed);
        RelaxedGaussSeidel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int nSize = Integer.parseInt(size.getText().toString());
                Intent intent = new Intent(IterativeMethods.this, LinearEquationIterativeRelaxed.class);
                intent.putExtra("method", "Relaxed Gauss Seidel");
                intent.putExtra("size"  ,nSize);
                startActivity(intent);
            }
        });
    }
}
