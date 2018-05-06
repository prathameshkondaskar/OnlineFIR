package com.example.prathamesh.policeapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by PRATHAMESH on 4/14/2018.
 */

public class InfoView extends Activity {

    static String viewdetails;
    TextView t1,t2,t3,t4,t5,t6,t7;
     static int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoview);

        String[] details = viewdetails.split("%");

        setTitle("Information");
        Log.d("Details ",viewdetails);

        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);
        t6=(TextView)findViewById(R.id.t6);
        t7=(TextView)findViewById(R.id.t7);

        t1.setText(details[0]);
        t2.setText(details[1]);
        t3.setText(details[2]);
        t4.setText(details[3]);
        t5.setText(details[4]);
        t6.setText(details[5]);
        t7.setText(details[6]);




    }
}
