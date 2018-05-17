package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;
import com.eafit.dis.mathismath.R;

/**
 * Created by Joshua on 5/9/18.
 */

public class PopHelp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_help);
        String msg = getIntent().getStringExtra("help");
        TextView title_A = findViewById(R.id.helpText);
        title_A.setText(msg);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.8));
    }
}
