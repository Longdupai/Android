package com.son.lab8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class activity_result extends Activity {
    Button BT;
    TextView KQ;
    @Override
    protected void onCreate(@Nullable Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_result);
        KQ = (TextView) findViewById(R.id.TxtKQ);
        BT = (Button)findViewById(R.id.BtnBack);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("MyPackage");
        KQ.setText(packageFromCaller.getInt("KQ")+"/"+ packageFromCaller.getInt("Socau"));
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
