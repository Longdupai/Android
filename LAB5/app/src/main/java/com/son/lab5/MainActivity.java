package com.son.lab5;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button BT1,BT2,BT3,BT4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BT1 = (Button)findViewById(R.id.BtnBanhcanh);
        BT2 = (Button)findViewById(R.id.BtnChaoluon);
        BT3 = (Button)findViewById(R.id.BtnPho);
        BT4 = (Button)findViewById(R.id.BtnThoat);
        BT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, banhcanh.class);
                startActivity(intent);
            }
        });
        BT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, chaoluon.class);
                startActivity(intent);
            }
        });
        BT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, phobo.class);
                startActivity(intent);
            }
        });
        BT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}