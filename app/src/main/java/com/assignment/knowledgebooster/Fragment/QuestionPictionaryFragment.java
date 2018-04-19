package com.assignment.knowledgebooster.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.assignment.knowledgebooster.BitmapDownloaderTask;
import com.assignment.knowledgebooster.FirebaseClass.Pictionary;
import com.assignment.knowledgebooster.R;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class QuestionPictionaryFragment extends Fragment {
    public Pictionary ques;

    public EditText editTxtPictionaryAnswer;
    public ImageView imgViewPictionaryQuestion;
    public Button btnSubmitQuestionPictionary;

    TextView txtViewQuestionID,txtViewTotalAnswer,txtAuthorName;
    RatingBar ratingBar;
    String name;
    public QuestionPictionaryFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_pictionary, container, false);

        editTxtPictionaryAnswer = v.findViewById(R.id.editTxtPictionaryAnswer);
        imgViewPictionaryQuestion = v.findViewById(R.id.imgViewPictionaryQuestion);
        btnSubmitQuestionPictionary = v.findViewById(R.id.btnSubmitQuestionPictionary);

        txtViewQuestionID = v.findViewById(R.id.txtViewQuestionID);
        txtViewTotalAnswer = v.findViewById(R.id.txtViewTotalAnswer);
        txtAuthorName = v.findViewById(R.id.txtAuthorName);
        ratingBar = v.findViewById(R.id.ratingBar);

        displayQuestion();
        return v;
    }

    public void setArguments(Pictionary pictionary, String name){ this.ques = pictionary; this.name = name;}

    public void displayQuestion(){
        retrieveQuestionImage(ques.getPicUrl());

        txtViewQuestionID.setText("#"+ques.getQuestionId());
        txtViewTotalAnswer.setText("( " + ques.getTotalAnswers()+" )");

        double rate;
        double correct = ques.getCorrectAnswer();
        double total = ques.getTotalAnswers();
        if(ques.getTotalAnswers() == 0)
            rate = 0;
        else
            rate =  correct/ total * 5 ;
        ratingBar.setRating((float)rate);
        txtAuthorName.setText(name);
    }
    private void retrieveQuestionImage(final String id){
        DatabaseReference databaseProduct = FirebaseDatabase.getInstance().getReference("questions").child("pictionaries").child(id);
        databaseProduct.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseStorage.getInstance().getReference().child("Question").child("Pictionary").child(ques.getPicUrl()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        BitmapDownloaderTask task = new BitmapDownloaderTask(imgViewPictionaryQuestion);
                        task.execute(uri.toString());
                    }
                });

                Answers.getInstance().logContentView(new ContentViewEvent().putContentName("Pictionary Retrieve").putCustomAttribute(" Name", ques.getPicUrl()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}
