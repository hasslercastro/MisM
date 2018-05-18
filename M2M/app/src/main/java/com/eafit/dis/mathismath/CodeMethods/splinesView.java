package com.eafit.dis.mathismath.CodeMethods;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import com.eafit.dis.mathismath.R;


/**
 * Created by root on 5/18/18.
 */

public class splinesView extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splines);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}

