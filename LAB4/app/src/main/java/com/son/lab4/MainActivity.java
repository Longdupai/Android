package com.son.lab4;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText EdtKQ;
    double Sotruoc =0, Sosau=0, KQ=0;
    String Toantu="", ToanHang="0";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdtKQ = (EditText) findViewById(R.id.EdtKQ);
        int [] Arr = {R.id.Bnt0,R.id.Bnt1,R.id.Bnt2,R.id.Bnt3,R.id.Bnt4,R.id.Bnt5,
                R.id.Bnt6,R.id.Bnt7,R.id.Bnt8,R.id.Bnt9,R.id.BntBang,R.id.BntTru,
                R.id.BntCham,R.id.BntChia,R.id.BntCong,R.id.BntNhan,R.id.BntDel};
        for (int id:Arr){
            View v = (View)findViewById(id);
            v.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.BntCong:
                Toantu = "+";
                Sotruoc = Double.parseDouble(ToanHang);
                ToanHang = "0";
                break;
            case R.id.BntTru: Toantu = "-";
                Sotruoc = Double.parseDouble(ToanHang);
                ToanHang = "0";
                break;
            case R.id.BntNhan: Toantu = "*";
                Sotruoc = Double.parseDouble(ToanHang);
                ToanHang = "0";
                break;
            case R.id.BntChia: Toantu = "/";
                Sotruoc = Double.parseDouble(ToanHang);
                ToanHang = "0";
                break;
            case R.id.BntBang: Sosau = Double.parseDouble(ToanHang);
                if (Toantu =="+")
                    KQ = Sotruoc + Sosau;
                if (Toantu =="-")
                    KQ = Sotruoc - Sosau;
                if (Toantu =="*")
                    KQ = Sotruoc * Sosau;
                if (Toantu =="/"){
                    if (Sosau ==0)
                        KQ=0;
                    else
                        KQ = Sotruoc / Sosau;
                }
                ToanHang = Double.toString(KQ);
                EdtKQ.setText(ToanHang);
                break;
            case R.id.BntDel: EdtKQ.setText("");
                ToanHang = "0";
                Sotruoc = Sosau = KQ = 0;
                break;
            default: if (ToanHang.equals("0"))
                ToanHang ="";
                ToanHang += ((Button)v).getText().toString();
                EdtKQ.setText(ToanHang);
        }

        }
    }
