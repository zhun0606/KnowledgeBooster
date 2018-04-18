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

import com.assignment.knowledgebooster.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionSelectionFragment extends Fragment {
    public TextView txtQuestion;
    public Button btnA,btnB,btnC,btnD;

    public QuestionSelectionFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_question_selection, container, false);

            txtQuestion = v.findViewById(R.id.txtQuestion);
            btnA = v.findViewById(R.id.btnA);
            btnB = v.findViewById(R.id.btnB);
            btnC = v.findViewById(R.id.btnC);
            btnD = v.findViewById(R.id.btnD);

            return v;

    }
}
