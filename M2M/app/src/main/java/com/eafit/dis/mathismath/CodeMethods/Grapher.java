package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import methods.com.udojava.evalex.Expression;

import com.eafit.dis.mathismath.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Hassler on 21/03/2018.
 */

public class Grapher extends Activity {
    LineGraphSeries<DataPoint> series;
    EditText functionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grapher);
        functionText = (EditText) findViewById(R.id.function);

        Button sendGraph = (Button) findViewById(R.id.button2);
        sendGraph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                GraphView graph = (GraphView) findViewById(R.id.graph);
                double x, y;
                x = -100.0;
                series = new LineGraphSeries<DataPoint>();
                Expression expression = new Expression(functionText.getText().toString()).setPrecision(15);
                for (int i = 0; i < 5000; i++) {
                    x = x + 0.1;
                    try {
                        y = expression.setVariable("x", String.valueOf(x)).eval().doubleValue();

                        series.appendData(new DataPoint(x, y), true, 5000);
                        // set manual X bounds
                    }catch (Exception e){
                        Log.d("Error", e.toString());

                    }
                }
                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinY(-150);
                graph.getViewport().setMaxY(150);

                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(-80);
                graph.getViewport().setMaxX(80);

                // enable scaling and scrolling
                graph.getViewport().setScalable(true);
                graph.getViewport().setScalableY(true);

                graph.addSeries(series);
            }
        });





        //graph.getViewport().setScalableY(true);
    }
}