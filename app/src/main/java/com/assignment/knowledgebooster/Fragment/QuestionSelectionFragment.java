package com.assignment.knowledgebooster.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.knowledgebooster.FirebaseClass.QuestionAnswered;
import com.assignment.knowledgebooster.FirebaseClass.Questions;
import com.assignment.knowledgebooster.FirebaseClass.Selection;
import com.assignment.knowledgebooster.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionSelectionFragment extends Fragment {
    public Selection ques;

    public TextView txtQuestion;
    public Button btnA,btnB,btnC,btnD;

    TextView txtViewQuestionID,txtViewTotalAnswer,txtAuthorName;
    RatingBar ratingBar;
    String name;

    public QuestionSelectionFragment(){ }
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_selection, container, false);

        txtQuestion = v.findViewById(R.id.txtQuestion);
        btnA = v.findViewById(R.id.btnA);
        btnB = v.findViewById(R.id.btnB);
        btnC = v.findViewById(R.id.btnC);
        btnD = v.findViewById(R.id.btnD);

        txtViewQuestionID = v.findViewById(R.id.txtViewQuestionID);
        txtViewTotalAnswer = v.findViewById(R.id.txtViewTotalAnswer);
        txtAuthorName = v.findViewById(R.id.txtAuthorName);
        ratingBar = v.findViewById(R.id.ratingBar);

        displayQuestion();
        return v;
    }
    public void setArguments(Selection selection, String name) {
        this.ques = selection; this.name = name;
    }
    protected void displayQuestion(){
        ArrayList<String> answ = new ArrayList<>();
        if(ques.getFake2().equals("")  && ques.getFake3().equals("")){
            answ.add(ques.getAnswer());
            answ.add(ques.getFake1());
            Collections.shuffle(answ);
            txtQuestion.setText(ques.getQuestion());
            btnA.setText(answ.get(0));
            btnB.setText(answ.get(1));
            btnC.setVisibility(View.INVISIBLE);
            btnD.setVisibility(View.INVISIBLE);
        }
        else if(ques.getFake3().equals("")){
            answ.add(ques.getAnswer());
            answ.add(ques.getFake1());
            answ.add(ques.getFake2());
            Collections.shuffle(answ);
            txtQuestion.setText(ques.getQuestion());
            btnA.setText(answ.get(0));
            btnB.setText(answ.get(1));
            btnC.setText(answ.get(2));
            btnD.setVisibility(View.INVISIBLE);
        }else {
            answ.add(ques.getAnswer());
            answ.add(ques.getFake1());
            answ.add(ques.getFake2());
            answ.add(ques.getFake3());
            Collections.shuffle(answ);
            txtQuestion.setText(ques.getQuestion() + " ");
            btnA.setText(answ.get(0));
            btnB.setText(answ.get(1));
            btnC.setText(answ.get(2));
            btnD.setText(answ.get(3));
        }

        txtViewQuestionID.setText("#"+ques.getQuestionId());
        txtViewTotalAnswer.setText("( " + ques.getTotalAnswer()+" )");
        double rate;
        double correct = ques.getCorrectAnswer();
        double total = ques.getTotalAnswer();
        if(ques.getCorrectAnswer() == 0)
            rate = 0;
        else
            rate =  correct/ total * 5 ;
        ratingBar.setRating((float)rate);
        txtAuthorName.setText(name);
    }
}
