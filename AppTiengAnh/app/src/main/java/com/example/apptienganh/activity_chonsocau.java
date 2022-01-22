package com.example.apptienganh;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class activity_chonsocau extends Activity {
    EditText socauhoi;
    Button bt;
    Button tieptheo;
    String st;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chonsocau);
        bt = (Button) findViewById(R.id.BtnTroVe);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        socauhoi = (EditText) findViewById(R.id.Edtsocau);


        tieptheo=(Button)findViewById(R.id.BtnTieptheo);
        tieptheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cauhoi = Integer.parseInt(socauhoi.getText().toString());
                if (cauhoi < 5 || cauhoi >10) {
                    socauhoi.setError("Vui lòng nhập từ 5 đến 10");
                    socauhoi.requestFocus();
                    return;
                }
                else {
                    Intent intent = new Intent(activity_chonsocau.this, activity_dokho.class);
                    st=socauhoi.getText().toString();
                    intent.putExtra("Value",st);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }
}