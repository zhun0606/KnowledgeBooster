package com.assignment.knowledgebooster.CreateQuestion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.knowledgebooster.CreateQuestion.CreateQuestionActivity;
import com.assignment.knowledgebooster.R;

public class MyQuestion extends AppCompatActivity {

    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);
        setNightMode();

    }

    protected void btnMyQuestionOnClick(View v){
        switch(v.getId()){
            case R.id.imgBtnBack:
                finish();
                break;
            case R.id.imgBtnCreateQuestion:
                Intent intent = new Intent(this,CreateQuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.imgBtnExamineQuestion:
                Intent intent1 = new Intent();
                break;
            case R.id.imgBtnMyContribution:

                break;
        }
    }

    protected void setNightMode(){
        mPrefs = this.getSharedPreferences("myPreference", 0);

        if(mPrefs.getBoolean("NightMode", true) == true){
            ConstraintLayout container;
            LinearLayout myQuestionLinearLayout, myQuestionLinearLayout2;
            ImageButton imgBtnBack, imgBtnCreateQuestion, imgBtnExamineQuestion, imgBtnMyContribution;
            TextView txtViewMyQuestionHeader, spacing;

            container = findViewById(R.id.container);
            myQuestionLinearLayout = findViewById(R.id.myQuestionLinearLayout);
            myQuestionLinearLayout2 = findViewById(R.id.myQuestionLinearLayout2);
            imgBtnBack = findViewById(R.id.imgBtnBack);
            imgBtnCreateQuestion = findViewById(R.id.imgBtnCreateQuestion);
            imgBtnExamineQuestion = findViewById(R.id.imgBtnExamineQuestion);
            imgBtnMyContribution = findViewById(R.id.imgBtnMyContribution);
            txtViewMyQuestionHeader = findViewById(R.id.txtViewMyQuestionHeader);
            spacing = findViewById(R.id.spacing);

            container.setBackgroundColor(Color.BLACK);
            myQuestionLinearLayout.setBackgroundColor(Color.BLACK);
            myQuestionLinearLayout2.setBackgroundColor(Color.BLACK);
            imgBtnBack.setBackgroundColor(Color.BLACK);
            imgBtnCreateQuestion.setBackgroundColor(Color.BLACK);
            imgBtnExamineQuestion.setBackgroundColor(Color.BLACK);
            imgBtnMyContribution.setBackgroundColor(Color.BLACK);
            txtViewMyQuestionHeader.setBackgroundColor(Color.BLACK);
            txtViewMyQuestionHeader.setTextColor(Color.WHITE);
            spacing.setBackgroundColor(Color.BLACK);



        }


    }
}
