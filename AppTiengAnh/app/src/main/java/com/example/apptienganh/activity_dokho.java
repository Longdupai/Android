package com.example.apptienganh;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.view.View;


public class activity_dokho extends Activity{
    Button bt,De,Trung,Kho;
    String st;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokho);
        bt = (Button) findViewById(R.id.BtnTroVe);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.batdau);
        De = (Button)findViewById(R.id.BtnDe);
        De.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_dokho.this,activity_de.class);

                st = getIntent().getExtras().getString("Value");
                intent.putExtra("ValueDe",st);
                mediaPlayer.start();
                startActivity(intent);
            }
        });

        Trung = (Button)findViewById(R.id.BtnTrung);
        Trung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_dokho.this, activity_trung.class);
                st = getIntent().getExtras().getString("Value");
                intent.putExtra("ValueDe",st);
                mediaPlayer.start();
                startActivity(intent);

            }
        });

        Kho = (Button)findViewById(R.id.BtnKho);
        Kho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_dokho.this, activity_kho.class);
                st = getIntent().getExtras().getString("Value");
                intent.putExtra("ValueDe",st);
                mediaPlayer.start();
                startActivity(intent);
                finish();
            }
        });



    }
}