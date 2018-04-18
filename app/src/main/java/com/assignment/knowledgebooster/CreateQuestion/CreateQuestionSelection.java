package com.assignment.knowledgebooster.CreateQuestion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.assignment.knowledgebooster.R;

public class CreateQuestionSelection extends Fragment {
    EditText txtQuestion;
    EditText editTextAnswer;
    EditText editTextFake1;
    EditText editTextFake2;
    EditText editTextFake3;

    public CreateQuestionSelection() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_question_selection, container, false);
        txtQuestion = v.findViewById(R.id.editTextQuestion);
        editTextAnswer = v.findViewById(R.id.editTextAnswer);
        editTextFake1 = v.findViewById(R.id.editTextFakeAnswer1);
        editTextFake2 = v.findViewById(R.id.editTextFakeAnswer2);
        editTextFake3 = v.findViewById(R.id.editTextFakeAnswer3);
        return v;
    }

}
