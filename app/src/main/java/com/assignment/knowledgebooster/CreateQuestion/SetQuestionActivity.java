package com.assignment.knowledgebooster.CreateQuestion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.assignment.knowledgebooster.R;

public class SetQuestionActivity extends AppCompatActivity {

    EditText editTextQuestion , editTextHint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_question);


    }


    public void btnSetQuestion(View v){
        editTextQuestion = findViewById(R.id.editTextQuestion);
        editTextHint = findViewById(R.id.editTextHint);
        if(editTextHint.getText().toString().replace(" ","").equals("") && editTextQuestion.getText().toString().replace(" ","").equals("")){
           Toast.makeText(this, "Please fill in both field to proceed", Toast.LENGTH_LONG).show();
        }else{
          Intent intent = new Intent();
          setResult(1, intent);
          finish();
        }

    }
}
