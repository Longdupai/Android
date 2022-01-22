package com.son.maytinh1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText N1, N2;
    TextView KQ;
    Button BT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        N1 = (EditText)findViewById(R.id.EdtN1);
        N2 = (EditText)findViewById(R.id.EdtN2);
        KQ = (TextView)findViewById(R.id.TxtKq);
        BT = (Button) findViewById(R.id.BtnKq);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double A = Double.parseDouble(N1.getText().toString());
                Double B = Double.parseDouble(N2.getText().toString());
                KQ.setText(""+(A+B));
            }
        });
    }
}