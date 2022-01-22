package com.son.lab6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class activity_hoadon extends Activity {
    Button BT1;
    TextView KH, TH, SL, DG, TT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        BT1 = (Button) findViewById(R.id.BtnTrove);
        KH = (TextView)findViewById(R.id.TxtTenkhachhang1);
        TH = (TextView)findViewById(R.id.TxtTenhang1);
        SL = (TextView)findViewById(R.id.TxtSoluong1);
        DG = (TextView)findViewById(R.id.TxtDongia1);
        TT = (TextView)findViewById(R.id.TxtThanhTien);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("MyPackage");
        KH.setText("Tên khách hàng:" + packageFromCaller.getString("KH1"));
        TH.setText("Tên hàng: " + packageFromCaller.getString("TH1"));
        SL.setText("Số lượng: " + packageFromCaller.getInt("SL1"));
        DG.setText("Đơn giá:"+ packageFromCaller.getDouble("DG1"));
        int SL1 = packageFromCaller.getInt("SL1");
        Double DG1 = packageFromCaller.getDouble("DG1");
        Double SL2 = packageFromCaller.getDouble("SL1");
        Double DG2 = packageFromCaller.getDouble("DG1");
        Double Tien = SL1 * DG1;
        TT.setText("Thành tiền:" + Tien);
        BT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

