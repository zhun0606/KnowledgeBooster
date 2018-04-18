package com.assignment.knowledgebooster.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.knowledgebooster.FirebaseClass.Selection;
import com.assignment.knowledgebooster.R;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionSelectionFragment extends Fragment {
    TextView txtQuestion;
    Button btnA,btnB,btnC,btnD;
    Selection ques;

    public QuestionSelectionFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_selection, container, false);

        txtQuestion = v.findViewById(R.id.txtQuestion);
        btnA = v.findViewById(R.id.btnA);
        btnB = v.findViewById(R.id.btnB);
        btnC = v.findViewById(R.id.btnC);
        btnD = v.findViewById(R.id.btnD);
        displayQuestion();
        return v;
    }

    public void setArguments(Selection selection) {
        this.ques = selection;
    }

    protected void displayQuestion(){
        ArrayList<String> answ = new ArrayList<>();
        if(ques.getFake2().equals("no")  && ques.getFake3().equals("no")){
            answ.add(ques.getAnswer());
            answ.add(ques.getFake1());
            Collections.shuffle(answ);
            txtQuestion.setText(ques.getQuestion());
            btnA.setText(answ.get(0));
            btnB.setText(answ.get(1));
            btnC.setVisibility(View.INVISIBLE);
            btnD.setVisibility(View.INVISIBLE);
        }
        else if(ques.getFake3().equals("no")){
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
    }
}
