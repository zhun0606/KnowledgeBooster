package com.assignment.knowledgebooster.CreateQuestion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.assignment.knowledgebooster.R;

public class SetExplanationActivity extends AppCompatActivity {
    EditText editTextAnswer, editTextExplanation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_explanation);
    }

    public void btnSetExplanation(View v){
        editTextAnswer = findViewById(R.id.editTextAnswer);
        editTextExplanation = findViewById(R.id.editTextExplanation);
        if(editTextAnswer.getText().toString().replace(" ","").equals("") && editTextExplanation.getText().toString().replace(" ","").equals("")){
            Toast.makeText(this, "Please fill in both field to proceed", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
        }

    }
}
