package com.androidplot.demos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import methods.IncrementalSearch;

/**
 * Created by Hassler on 14/03/2018.
 */

public class OneVariableMethods extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_one_variable);


        Button bisection = (Button) findViewById(R.id.bisection);
        bisection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OneVariableMethods.this, bisectionView.class));
            }
        });

        Button incrementaLSearch = (Button) findViewById(R.id.Incremental);
        incrementaLSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OneVariableMethods.this, incSearch.class));
            }
        });

        Button falsePosition = (Button) findViewById(R.id.FalsePosition);
        falsePosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OneVariableMethods.this, falsePositionView.class));
            }
        });

        Button fixedPoint = (Button) findViewById(R.id.FixedPoint);
        fixedPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OneVariableMethods.this, fixedPointView.class));
            }
        });

        Button newton = (Button) findViewById(R.id.newton);
        newton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OneVariableMethods.this, newtonView.class));
            }
        });

        Button secant = (Button) findViewById(R.id.secant);
        secant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OneVariableMethods.this, secantView.class));
            }
        });

        Button mRoots = (Button) findViewById(R.id.mroots);
        mRoots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OneVariableMethods.this, multipleRoots.class));
            }
        });

    }


}
