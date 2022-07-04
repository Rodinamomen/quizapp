package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestions;
    TextView question;
    Button A,B,C,D;
    Button submit;
    int  score=0 ;
    int total= questionAndAns.questions.length; //4
    int currentquestion=0;
    String selectedAns="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.q1);
        totalQuestions = findViewById(R.id.Totalquestions);
        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        submit = findViewById(R.id.submit);
        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        submit.setOnClickListener(this);
        totalQuestions.setText("Total questions is:" + total);
        loadnewquestion();
    }

    @Override
    public void onClick(View view) {
        A.setBackgroundColor(Color.WHITE);
        B.setBackgroundColor(Color.WHITE);
        C.setBackgroundColor(Color.WHITE);
        D.setBackgroundColor(Color.WHITE);

        Button clicked =(Button) view;
        if (clicked.getId()==R.id.submit){
            Toast.makeText(this, "The correct answer is : "+questionAndAns.rightAns[currentquestion], Toast.LENGTH_SHORT).show();
            if(selectedAns.equals(questionAndAns.rightAns[currentquestion])){
                score++;
            }
            currentquestion++;
            loadnewquestion();

        }else{
            selectedAns=clicked.getText().toString();
            clicked.setBackgroundColor(Color.rgb(250,244,183));
        }

    }
    void loadnewquestion(){
        if(currentquestion==total){
            finshquiz();
            return;
        }
        else {
            question.setText(questionAndAns.questions[currentquestion]);
            A.setText(questionAndAns.ans[currentquestion][0]);
            B.setText(questionAndAns.ans[currentquestion][1]);
            C.setText(questionAndAns.ans[currentquestion][2]);
            D.setText(questionAndAns.ans[currentquestion][3]);
        }
    }
    void finshquiz(){
        String passOrfail;
        if(score>(total*0.60)){
            passOrfail="Passed";
        }else {
            passOrfail="Faild";
        }
        new AlertDialog.Builder(this)
                .setTitle(passOrfail)
                .setMessage("Score is : "+score+ " out of "+total)
                .setPositiveButton("Restart",((dialogInterface, i) -> restartquiz()))
                .setCancelable(false)
                .show();


    }
    void restartquiz(){
        score=0;
        currentquestion=0;
        loadnewquestion();
    }
}