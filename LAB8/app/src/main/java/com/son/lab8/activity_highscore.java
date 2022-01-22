package com.son.lab8;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class activity_highscore extends Activity {
    TextView Txt1;
    int HighScore;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        Txt1 = (TextView)findViewById(R.id.TxtHighscore);
        LoadHighScore();
        Txt1.setText(""+ HighScore);
    }
    void LoadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if (sharedPreferences !=null){
            HighScore = sharedPreferences.getInt("H",0);
        }
    }

}
