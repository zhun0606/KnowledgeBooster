package com.assignment.knowledgebooster.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.knowledgebooster.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionPictionaryFragment extends Fragment {

    public QuestionPictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_pictionary, container, false);
    }

}
