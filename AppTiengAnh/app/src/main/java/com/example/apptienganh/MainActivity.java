package com.example.apptienganh;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
                TextView register,forgotpassword;
                Button login;
                EditText edtEmail,edtPassword;
                private FirebaseAuth mAuth;
                private ProgressBar progressBar;
                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);
                    register = (TextView) findViewById(R.id.register);
                    register.setOnClickListener(this);
                    login =(Button) findViewById(R.id.login);
                    login.setOnClickListener(this);
                    mAuth=FirebaseAuth.getInstance();
                    edtEmail=(EditText) findViewById(R.id.email);
                    edtPassword=(EditText) findViewById(R.id.password);
                    progressBar=(ProgressBar) findViewById(R.id.processBar);
                    forgotpassword=(TextView)findViewById(R.id.forgotPassword);
                    forgotpassword.setOnClickListener(this);
                }
    public void onBackPressed(){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("B???n c?? mu???n tho??t ch????ng tr??nh  ?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                    builder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
    }


    @Override
    public void onClick(View v) {
                    switch(v.getId()){
                        case R.id.register:
                            startActivity(new Intent(this,activity_register.class));
                            break;

                        case R.id.login:
                            userLogin();
                            break;
                        case R.id.forgotPassword:
                            startActivity(new Intent(this,activity_forget_password.class));
                            break;
                    }

    }

    private void userLogin() {
                    String email=edtEmail.getText().toString().trim();
                    String password=edtPassword.getText().toString().trim();
                    if(email.isEmpty()){
                        edtEmail.setError("Vui l??ng nh???p email!");
                        edtEmail.requestFocus();
                        return;
                    }
                 if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                         edtEmail.setError("Vui l??ng nh???p ????ng ?????nh d???ng email");
                         edtEmail.requestFocus();
                         return;
                 }

                    if(password.isEmpty()){
                        edtPassword.setError("Vui l??ng nh???p m???t kh???u!");
                        edtPassword.requestFocus();
                        return;
                    }
                    if(password.length()<6){
                        edtPassword.setError("Vui l??ng nh???p 6 k?? t??? tr??? l??n!");
                        edtPassword.requestFocus();
                        return;
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                                if(user.isEmailVerified()){
                                    startActivity(new Intent(MainActivity.this ,activity_home.class));
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MainActivity.this,"????ng nh???p th??nh c??ng",Toast.LENGTH_LONG).show();
                                    finish();
                                }else{
                                    user.sendEmailVerification();
                                    Toast.makeText(MainActivity.this,"Vui l??ng ki???m tra h??m th?? c???a b???n!",Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            }else{
                                Toast.makeText(MainActivity.this,"????ng nh???p th???t b???i!Vui l??ng th??? l???i sau",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
    }
}