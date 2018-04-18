package com.assignment.knowledgebooster.CreateQuestion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.assignment.knowledgebooster.PermissionUtils;
import com.assignment.knowledgebooster.R;

import java.io.File;

public class CreateQuestionPictionary extends Fragment {
    EditText editTextQuestion;
    Button btnInsert,btnSubmitQuestionPictionary;
    ImageView imageViewPic;

    public CreateQuestionPictionary() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_question_pictionary, container, false);

        editTextQuestion = v.findViewById(R.id.editTextQuestion);
        btnInsert = v.findViewById(R.id.buttonInsert);
        imageViewPic = v.findViewById(R.id.imageViewPic);
        btnSubmitQuestionPictionary = v.findViewById(R.id.btnSubmitQuestionPictionary);

        return v;
    }
}
