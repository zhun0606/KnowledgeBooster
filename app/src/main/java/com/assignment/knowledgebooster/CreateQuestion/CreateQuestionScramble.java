package com.assignment.knowledgebooster.CreateQuestion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.assignment.knowledgebooster.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateQuestionScramble extends Fragment {
    EditText editTextQuestion;

    public CreateQuestionScramble() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_question_scramble, container, false);
        editTextQuestion = v.findViewById(R.id.editTextQuestion);
        return v;
    }

}
