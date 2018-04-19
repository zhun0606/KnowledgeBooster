package com.assignment.knowledgebooster.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.assignment.knowledgebooster.FirebaseClass.Scramble;
import com.assignment.knowledgebooster.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionScrambleFragment extends Fragment {
    public Scramble ques;
    public TextView txtViewQuestionScramble;
    public Button btnSubmitQuestionScramble;
    public EditText editTxtScrambleAnswer;

    TextView txtViewQuestionID,txtViewTotalAnswer,txtAuthorName;
    RatingBar ratingBar;
String name;
    public QuestionScrambleFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_question_scramble, container, false);
        txtViewQuestionScramble = v.findViewById(R.id.txtViewQuestionScramble);
        btnSubmitQuestionScramble = v.findViewById(R.id.btnSubmitQuestionScramble);
        editTxtScrambleAnswer = v.findViewById(R.id.editTxtScrambleAnswer);

        txtViewQuestionID = v.findViewById(R.id.txtViewQuestionID);
        txtViewTotalAnswer = v.findViewById(R.id.txtViewTotalAnswer);
        txtAuthorName = v.findViewById(R.id.txtAuthorName);
        ratingBar = v.findViewById(R.id.ratingBar);

        displayQuestion();
        return v;
    }
    public void setArguments(Scramble ques,String name){
        this.ques = ques;
        this.name = name;
    }

    protected void displayQuestion(){
        String id ="";
        List<String> myList = new ArrayList<String>(Arrays.asList(ques.getQuestion().split("")));
        Collections.shuffle(myList);

        for(String str : myList){
            id = id + str;
        }
        txtViewQuestionScramble.setText(id);

        txtViewQuestionID.setText("#"+ques.getQuestionId());
        txtViewTotalAnswer.setText("( " + ques.getTotalAnswer()+" )");

        double rate;
        double correct = ques.getCorrectAnswer();
        double total = ques.getTotalAnswer();
        if(ques.getTotalAnswer() == 0)
            rate = 0;
        else
            rate =  correct/ total * 5 ;
        ratingBar.setRating((float)rate);
        txtAuthorName.setText(name);
    }
}
