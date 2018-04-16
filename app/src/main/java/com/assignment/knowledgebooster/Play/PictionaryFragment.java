package com.assignment.knowledgebooster.Play;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.assignment.knowledgebooster.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PictionaryFragment extends Fragment {

    public PictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pictionary, container, false);
    }

}
