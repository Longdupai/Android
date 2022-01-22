package com.example.apptienganh;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class activity_kho extends AppCompatActivity {

    TextView tv_question;
    Button b_answer1,b_answer2,b_answer3,b_answer4;
    String st;
    List<QuestionItemDe> questionItemDes;
    int currentQuestion = 0;

    int correct = 0 , wrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de);

        tv_question = findViewById(R.id.question);
        b_answer1 = findViewById(R.id.answer1);
        b_answer2 = findViewById(R.id.answer2);
        b_answer3 = findViewById(R.id.answer3);
        b_answer4 = findViewById(R.id.answer4);

        st = getIntent().getExtras().getString("ValueDe");


        //lay tat ca cau hoi
        loadAllQuestions();
        //dao lon cau hoi
        Collections.shuffle(questionItemDes);
        //load cau hoi dau tien
        setQeustionScreen(currentQuestion);


        b_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra neu cau tra loi la dung
                if(questionItemDes.get(currentQuestion).getAnswer1().equals(questionItemDes.get(currentQuestion).getCorrect())){
                    //dung
                    correct++;
                    Toast.makeText(activity_kho.this,"Đúng!",Toast.LENGTH_SHORT).show();
                } else {
                    //sai
                    wrong++;
                    Toast.makeText(activity_kho.this,"Sai! Câu đúng là: "
                            + questionItemDes.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }

                //load cau hoi tiep theo neu co
                if(currentQuestion < questionItemDes.size()-1){
                    currentQuestion++;
                    setQeustionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent = new Intent(getApplicationContext(), EndActivityDe.class);
                    intent.putExtra("Đúng",correct);
                    intent.putExtra("Sai",wrong);
                    startActivity(intent);
                    finish();
                }
            }
        });

        b_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra neu cau tra loi la dung
                if(questionItemDes.get(currentQuestion).getAnswer2().equals(questionItemDes.get(currentQuestion).getCorrect())){
                    //dung
                    correct++;
                    Toast.makeText(activity_kho.this,"Đúng!",Toast.LENGTH_SHORT).show();
                } else {
                    //sai
                    wrong++;
                    Toast.makeText(activity_kho.this,"Sai! Câu đúng là: "
                            + questionItemDes.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }

                //load cau hoi tiep theo neu co
                if(currentQuestion < questionItemDes.size()-1){
                    currentQuestion++;
                    setQeustionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent = new Intent(getApplicationContext(), EndActivityDe.class);
                    intent.putExtra("Đúng",correct);
                    intent.putExtra("Sai",wrong);
                    startActivity(intent);
                    finish();
                }

            }
        });

        b_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra neu cau tra loi la dung
                if(questionItemDes.get(currentQuestion).getAnswer3().equals(questionItemDes.get(currentQuestion).getCorrect())){
                    //dung
                    correct++;
                    Toast.makeText(activity_kho.this,"Đúng!",Toast.LENGTH_SHORT).show();
                } else {
                    //sai
                    wrong++;
                    Toast.makeText(activity_kho.this,"Sai! Câu đúng là: "
                            + questionItemDes.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }

                //load cau hoi tiep theo neu co
                if(currentQuestion < questionItemDes.size()-1){
                    currentQuestion++;
                    setQeustionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent = new Intent(getApplicationContext(), EndActivityDe.class);
                    intent.putExtra("Đúng",correct);
                    intent.putExtra("Sai",wrong);
                    startActivity(intent);
                    finish();
                }

            }
        });
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.tieptheo);
        b_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra neu cau tra loi la dung
                if(questionItemDes.get(currentQuestion).getAnswer4().equals(questionItemDes.get(currentQuestion).getCorrect())){
                    //dung
                    correct++;
                    Toast.makeText(activity_kho.this,"Đúng!",Toast.LENGTH_SHORT).show();
                } else {
                    //sai
                    wrong++;
                    Toast.makeText(activity_kho.this,"Sai! Câu đúng là: "
                            + questionItemDes.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }

                //load cau hoi tiep theo neu co
                if(currentQuestion < questionItemDes.size()-1){
                    currentQuestion++;
                    setQeustionScreen(currentQuestion);
                    mediaPlayer.start();
                }else{
                    //game over
                    Intent intent = new Intent(getApplicationContext(), EndActivityDe.class);
                    intent.putExtra("Đúng",correct);
                    intent.putExtra("Sai",wrong);
                    startActivity(intent);
                    finish();

                }

            }
        });
    }
    //dat cau hoi len man hinh
    private void setQeustionScreen (int number){
        tv_question.setText(questionItemDes.get(number).getQuestion());
        b_answer1.setText(questionItemDes.get(number).getAnswer1());
        b_answer2.setText(questionItemDes.get(number).getAnswer2());
        b_answer3.setText(questionItemDes.get(number).getAnswer3());
        b_answer4.setText(questionItemDes.get(number).getAnswer4());


    }

    //lam list voi tat ca cau hoi
    private void loadAllQuestions(){
        questionItemDes = new ArrayList<>();

        int socauhoi = Integer.parseInt(st);

        //load tat ca cau hoi tu json string
        String jsonStr = loadJSONFromAsset("questionsDe.json");

        //load tat ca data vao list
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray questions = jsonObj.getJSONArray("Kho");
            for (int i = 0;i<socauhoi;i++){
                JSONObject question = questions.getJSONObject(i);

                String questionString = question.getString("question");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String answer3String = question.getString("answer3");
                String answer4String = question.getString("answer4");
                String correctString = question.getString("correct");

                questionItemDes.add(new QuestionItemDe(
                        questionString,
                        answer1String,
                        answer2String,
                        answer3String,
                        answer4String,
                        correctString
                ));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    //load json file tu assets folder
    private String loadJSONFromAsset(String file){
        String json = "";
        try {
            InputStream is = getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }
}
