package com.example.apptienganh;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_diemcao extends Activity {
    TextView Txt1,Txt2;
    int diemcao;
    Button BT;
    private DatabaseReference reference;
    private DatabaseReference referencescore;
    private String userID;
    private FirebaseUser user;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState); setContentView(R.layout.activity_diemcao);

        BT = (Button)findViewById(R.id.BtnTroVe);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Txt1 = (TextView)findViewById(R.id.TxtGanNhat);
        Txt2 = (TextView)findViewById(R.id.TxtDiemCao);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("User");
        userID=user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile= dataSnapshot.getValue(User.class);
                if(userProfile !=null){
                     int diemgannhat=userProfile.Score;
                     int diemcao=userProfile.Highscore;
                    Txt1.setText(""+ diemgannhat);
                    Txt2.setText(""+ diemcao);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    void LoadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if (sharedPreferences !=null){
            diemcao = sharedPreferences.getInt("H",0);
        }

    }

}