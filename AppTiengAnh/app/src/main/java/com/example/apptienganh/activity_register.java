package com.example.apptienganh;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class activity_register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    TextView banner;
    Button register,bt;
    EditText edtFullname, edtEmail, edtPassword;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        edtFullname = (EditText) findViewById(R.id.fullName);
        edtEmail = (EditText) findViewById(R.id.email);
        edtPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.processBar);
        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        bt = (Button) findViewById(R.id.BtnTroVe);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.register:
                register();
                break;
        }
    }
    public void register(){
        final String email =edtEmail.getText().toString().trim();
        String password=edtPassword.getText().toString().trim();
        final String fullname=edtFullname.getText().toString().trim();
        if(fullname.isEmpty()){
            edtFullname.setError("Vui lòng nhập họ tên");
            edtFullname.requestFocus();
            return;
        }
        if(email.isEmpty()){
            edtEmail.setError("Vui lòng nhập email");
            edtEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Vui lòng nhập đúng email");
            edtEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            edtPassword.setError("Vui lòng nhập mật khẩu");
            edtPassword.requestFocus();
            return;

        }
        if(password.length()<=6){
            edtPassword.setError("Mật khẩu phải từ 6 kí tự trở lên");
            edtPassword.requestFocus();
            return;

        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                        User user= new User(fullname,email,0,0);
                        FirebaseDatabase.getInstance().getReference("User")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(activity_register.this,"đăng ký thành công!",Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }else{
                                    Toast.makeText(activity_register.this,"đăng ký không thành công!Vui lòng thử lại",Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                            }
                        });
                    }else{
                        Toast.makeText(activity_register.this,"đăng ký không thành công!Vui lòng thử lại",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
    });
}
}