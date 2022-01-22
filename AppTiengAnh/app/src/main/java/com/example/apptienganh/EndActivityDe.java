package com.example.apptienganh;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
;import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EndActivityDe extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    int Highscore;

    TextView tv_result;
    Button Trove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endde);

        tv_result = findViewById(R.id.result);
        final int correct = getIntent().getIntExtra("Đúng", 0);
        int wrong = getIntent().getIntExtra("Sai",0);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.kethuc);
        mediaPlayer.start();
        tv_result.setText("Đúng: "+correct + " Sai: "+wrong);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("User");
        userID=user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile= dataSnapshot.getValue(User.class);
                if(userProfile !=null){ ;

                    reference.child(userID).child("Score").setValue(correct);
                    if(Highscore<correct){
                        Highscore=correct;
                        reference.child(userID).child("Highscore").setValue(Highscore);
                    }
                    else{

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Trove = (Button) findViewById(R.id.BtnTroVe);
        Trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndActivityDe.this, activity_home.class);
                startActivity(intent);
            }
        });

    }
}