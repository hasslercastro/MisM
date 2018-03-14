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

package com.androidplot.demos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.crittercism.app.Crittercism;
import com.squareup.haha.perflib.Main;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    // DO *NOT* CHANGE THIS LINE! (CI-MATCH-POPULATE)
    private static final String CRITTERCISM_APP_ID = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (CRITTERCISM_APP_ID != null) {
            Log.d(TAG, "Crittercism initialized.");
            Crittercism.initialize(getApplicationContext(), CRITTERCISM_APP_ID);
        }

        setContentView(R.layout.main);


        // f(x) plot
        Button fxPlotExampleButton = (Button) findViewById(R.id.fxPlotExample);
        fxPlotExampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FXPlotExampleActivity.class));
            }
        });

        Button method =  (Button) findViewById(R.id.method);
        method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

               startActivity(new Intent(MainActivity.this, Method.class));
            }
        });
    }
}
