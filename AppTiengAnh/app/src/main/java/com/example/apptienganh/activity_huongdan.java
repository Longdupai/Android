package com.example.apptienganh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;


public class activity_huongdan extends AppCompatActivity {
    private Button bt,LamBai,DiemCao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huongdan);



        bt = (Button) findViewById(R.id.BtnTroVe);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LamBai = (Button) findViewById(R.id.BtnHDLamBai);
        LamBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_huongdan.this, activity_hdlambai.class);
                startActivity(intent);
            }
        });

        DiemCao = (Button) findViewById(R.id.BtnHDDiemCao);
        DiemCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_huongdan.this, activity_hddiemcao.class);
                startActivity(intent);
            }
        });



    }


}