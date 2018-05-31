package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.eafit.dis.mathismath.R;

/**
 * Created by josh on 5/30/18.
 */

public class InformationView extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_view);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(getIntent().getExtras().getString("text"));
    }
}
