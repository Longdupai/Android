package com.example.apptienganh;
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
public class activity_ketqua extends Activity {
    Button BT;
    TextView KQ;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    int Highscore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);
        KQ = (TextView) findViewById(R.id.TxtKetqua);
        BT = (Button)findViewById(R.id.BtnTroVe);

        Intent callerIntent=getIntent();
        final Bundle packageFromCaller= callerIntent.getBundleExtra("MyPackage");
        KQ.setText(" "+ getIntent().getIntExtra("Đúng",0));

        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("User");
        userID=user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile= dataSnapshot.getValue(User.class);
                if(userProfile !=null){ ;
                    int score=getIntent().getIntExtra("Đúng",0);
                    reference.child(userID).child("Score").setValue(score);
                    if(Highscore<score){
                        Highscore=score;
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
    }


}