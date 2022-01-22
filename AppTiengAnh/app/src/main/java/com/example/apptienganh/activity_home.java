package com.example.apptienganh;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_home extends Activity {
    Button LamBai, DiemCao, HuongDan, Thoat;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LamBai = (Button) findViewById(R.id.BtnLamBai);
        LamBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_home.this, activity_chonsocau.class);
                startActivity(intent);
            }
        });

        DiemCao = (Button) findViewById(R.id.BtnDiemCao);
        DiemCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_home.this, activity_diemcao.class);
                startActivity(intent);
            }
        });

        HuongDan = (Button) findViewById(R.id.BtnHuongDan);
        HuongDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_home.this, activity_huongdan.class);
                startActivity(intent);
            }
        });
        Thoat = (Button) findViewById(R.id.BtnThoat);
        Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(activity_home.this,MainActivity.class));
                finish();
            }
        });
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("User");
        userID=user.getUid();

        final TextView txtusername=(TextView)findViewById(R.id.TxtUsername);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile= dataSnapshot.getValue(User.class);
                if(userProfile !=null){
                    String fullName=userProfile.fullName;
                    txtusername.setText(" "+fullName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}