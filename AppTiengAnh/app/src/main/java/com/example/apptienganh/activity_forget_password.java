package com.example.apptienganh;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class activity_forget_password extends AppCompatActivity {
    private EditText emailEditText;
    private Button resetPassowrdButton;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emailEditText=(EditText)findViewById(R.id.email);
        resetPassowrdButton=(Button)findViewById(R.id.resetPassowrd);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
        resetPassowrdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }
    private void resetPassword(){
        String email=emailEditText.getText().toString().trim();
        if(email.isEmpty()){
            emailEditText.setError("Vui lòng nhập email");
            emailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Vui lòng nhập đúng định dạng email");
            emailEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   Toast.makeText(activity_forget_password.this,"Vui lòng kiểm tra hòm thư!",Toast.LENGTH_LONG).show();
                   progressBar.setVisibility(View.INVISIBLE);
                   finish();

               }else{
                   Toast.makeText(activity_forget_password.this,"Vui lòng thử lại sau!",Toast.LENGTH_LONG).show();
                   progressBar.setVisibility(View.INVISIBLE);
               }
            }
        });
    }
}