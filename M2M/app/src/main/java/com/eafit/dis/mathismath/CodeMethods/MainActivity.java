/*
 * Copyright 2015 AndroidPlot.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.eafit.dis.mathismath.R;


public class MainActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Button plot = (Button) findViewById(R.id.fxPlotExample);
        plot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Grapher.class));
            }
        });

        Button method =  (Button) findViewById(R.id.method);
        method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

               startActivity(new Intent(MainActivity.this, Method.class));
            }
        });

        ImageButton info = (ImageButton) findViewById(R.id.information);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,InformationView.class);
                intent.putExtra("text","\nMath is Math (M^M) is an application developed for make easier the understanding and compute of several numerical methods.\n\nDevelopers:\n\n→ Hassler Castro Cuesta\n     (hcastro@eafit.edu.co)\n\n→ Edwin Rengifo Villa\n     (erengif1@eafit.edu.co)\n\n→ Camilo Villa Restrepo\n     (cvillar4@eafit.edu.co)\n\n→ Joshua Sánchez Álvarez\n     (jsanch90@eafit.edu.co)\n\n\nSupport: https://sites.google.com/site/analisisnumerico20181/ \n\nSource code: https://github.com/mathismath/MisM\n\nExternal libraries:\n\n → Evalex: https://github.com/uklimaschewski/EvalEx\n\n→ GraphView:http://www.android-graphview.org/\n ");
                startActivity(intent);
            }
        });

        ImageButton help = (ImageButton) findViewById(R.id.helpButton);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,InformationView.class);
                intent.putExtra("text","\nDuring the use of the application you will find different fields that must be filled with the following format:\n\n→ Tolerance: it is a decimal numerical value (double precision). If you want to use scientific notation you should use 'E' which means (some value)x10^(some value).\n\nExample: 1x10^-5 = 1E-5.\n\n→ Functions: The powers are represented by the symbol '^'.\n\nExample: x^2.\n\nThe natural logarithm function ln is represented as log so ln(x) = log(x)\n\nThe trigonometric functions and the exponential function are written as usual.\n\nExample: sin(x),  cos(x),  tan(x),  e^x\n\nThe coefficients that accompany the variables must be written with '*'.\n\nExample: 2x must be written as 2*x");
                startActivity(intent);
            }
        });
    }
}
