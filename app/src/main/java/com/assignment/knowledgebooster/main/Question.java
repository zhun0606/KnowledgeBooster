package com.assignment.knowledgebooster.main;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.knowledgebooster.FirebaseClass.Pictionary;
import com.assignment.knowledgebooster.FirebaseClass.QuestionAnswered;
import com.assignment.knowledgebooster.FirebaseClass.Questions;
import com.assignment.knowledgebooster.FirebaseClass.Scramble;
import com.assignment.knowledgebooster.FirebaseClass.Selection;
import com.assignment.knowledgebooster.Fragment.QuestionPictionaryFragment;
import com.assignment.knowledgebooster.Fragment.QuestionSelectionFragment;
import com.assignment.knowledgebooster.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question extends AppCompatActivity {
    public Questions questions;
    public QuestionAnswered questionAnswered;

    TextView  txtViewAnswer, txtViewExplanation;
    EditText editTxtHint,editTextAnswer, editTextExplanation, editTextInputSearchQuestion;
    Button btnCategory;
    BottomNavigationView navigation;

    FragmentManager fm;
    QuestionSelectionFragment selectionFragment = new QuestionSelectionFragment();
    QuestionPictionaryFragment pictionaryFragment = new QuestionPictionaryFragment();

    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        btnCategory = findViewById(R.id.btnCategory);
        navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm = getSupportFragmentManager();

        retrieveQuestion();

        setNightMode();
    }

    protected  void checkQuestionTypeAndDisplay(){
        try{
            Bundle extras = getIntent().getExtras();
            if(extras.getString("Monthly Pickup")  != null){
                btnCategory.setText("Monthly Pickup");
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
                displayQuestionSelection();
            }
            else if(extras.getString("Detective") != null){
                btnCategory.setText("Detective");
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
                displayQuestionSelection();
            }
            else if(extras.getString("Logic") != null){
                btnCategory.setText("Logic");
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
            else if(extras.getString("Pictionary") != null){
                btnCategory.setText("Pictionary");
                fm.beginTransaction().replace(R.id.questionFrameLayout, pictionaryFragment).commit();
            }
            else if(extras.getString("General Knowledge") != null){
                btnCategory.setText("General Knowledge");
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
            else if(extras.getString("Mathematics World") != null){
                btnCategory.setText("Mathematics World");
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
            else if(extras.getString("Decision Making") != null){
                btnCategory.setText("Decision Making");
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
        }catch (NullPointerException ex){
            ex.getStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    ////    Retrieving Question from FireBase and Removing Answered Question before Display   ////
    //////////////////////////////////////////////////////////////////////////////////////////////
    protected void retrieveQuestion() {
        DatabaseReference databaseQuestion = FirebaseDatabase.getInstance().getReference();
        databaseQuestion.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questions = dataSnapshot.child("questions").getValue(Questions.class);
                while (questions.getPictionaries().remove(null));
                while (questions.getScrambles().remove(null));
                while (questions.getSelections().remove(null));
                retrieveAnsweredQuestion();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void retrieveAnsweredQuestion() {
        DatabaseReference databaseQuestion = FirebaseDatabase.getInstance().getReference();
        databaseQuestion.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questionAnswered = dataSnapshot.child("questionAnswered").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(QuestionAnswered.class);
                while (questionAnswered.getPictionaries().remove(null));
                while (questionAnswered.getScrambles().remove(null));
                while (questionAnswered.getSelections().remove(null));
                removeAnsweredQuestion();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void removeAnsweredQuestion(){
        ArrayList<Pictionary> questionPictionaryToBeRemove = new ArrayList<>();
        ArrayList<Selection> questionSelectionToBeRemove = new ArrayList<>();
        ArrayList<Scramble> questionScrambleToBeRemove = new ArrayList<>();
        for(String ss : questionAnswered.getPictionaries()){
            for(Pictionary pictionaryQuestion : questions.getPictionaries()){
                try{
                    if(pictionaryQuestion.getQuestionId().equals(ss))
                        questionPictionaryToBeRemove.add(pictionaryQuestion);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        questions.getPictionaries().removeAll(questionPictionaryToBeRemove);

        for(String ss : questionAnswered.getScrambles()){
            for(Scramble scrambleQuestion : questions.getScrambles()){
                try{
                    if(scrambleQuestion.getQuestionId().equals(ss))
                        questionScrambleToBeRemove.add(scrambleQuestion);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        questions.getScrambles().removeAll(questionScrambleToBeRemove);

        for(String ss : questionAnswered.getSelections()){
            for(Selection selectionQuestion : questions.getSelections()){
                try{
                    if(selectionQuestion.getQuestionId().equals(ss))
                        questionSelectionToBeRemove.add(selectionQuestion);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        questions.getSelections().removeAll(questionSelectionToBeRemove);

        checkQuestionTypeAndDisplay();
    }

    protected void displayQuestionScramble(){}
    protected void displayQuestionPictionary(){}
    protected void displayQuestionSelection(){
        ArrayList<Selection> selectionQuestion = questions.getSelections();
        // shuffle the question
        Collections.shuffle(selectionQuestion);
        //get first question
        Selection ques = selectionQuestion.get(0);
        ArrayList<String> answ = new ArrayList<>();

        if(ques.getFake2() == "no" && ques.getFake3() == "no"){
            answ.add(ques.getAnswer());
            answ.add(ques.getFake1());
            Collections.shuffle(answ);

            selectionFragment.txtQuestion.setText(ques.getQuestion());
            selectionFragment.btnA.setText(answ.get(0));
            selectionFragment.btnB.setText(answ.get(1));
            selectionFragment.btnC.setVisibility(View.INVISIBLE);
            selectionFragment.btnD.setVisibility(View.INVISIBLE);
        }
        if(ques.getFake3() == "no"){
            answ.add(ques.getAnswer());
            answ.add(ques.getFake1());
            answ.add(ques.getFake2());
            Collections.shuffle(answ);

            selectionFragment.txtQuestion.setText(ques.getQuestion());
            selectionFragment.btnA.setText(answ.get(0));
            selectionFragment.btnB.setText(answ.get(1));
            selectionFragment.btnC.setText(answ.get(2));
            selectionFragment.btnD.setVisibility(View.INVISIBLE);
        }else {
            answ.add(ques.getAnswer());
            answ.add(ques.getFake1());
            answ.add(ques.getFake2());
            answ.add(ques.getFake3());
            Collections.shuffle(answ);

            selectionFragment.txtQuestion.setText(ques.getQuestion());
            selectionFragment.btnA.setText(answ.get(0));
            selectionFragment.btnB.setText(answ.get(1));
            selectionFragment.btnC.setText(answ.get(2));
            selectionFragment.btnD.setText(answ.get(3));
        }
    }



    

    public void btnSearchOnQuestionClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Search Question");

        editTextInputSearchQuestion = new EditText(this);
        builder.setView(editTextInputSearchQuestion);

        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Direct to question ID", Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    protected void showHint(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Hint");
        editTxtHint = new EditText(this);
        builder.setView(editTxtHint);
        builder.setPositiveButton("i Get It", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    protected void showExplanation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getParent());
        builder.setMessage("Explanation");

        LinearLayout mainLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(params);

        txtViewAnswer = new TextView(this);
        editTextAnswer = new EditText(this);
        txtViewAnswer.setText("Answer");
        editTextAnswer.setLines(5);
        editTextAnswer.setEnabled(false);

        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(parms);

        LinearLayout.LayoutParams selectTypeParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(txtViewAnswer, selectTypeParams);
        layout.addView(editTextAnswer, selectTypeParams);

        txtViewExplanation = new TextView(this);
        editTextExplanation = new EditText(this);
        txtViewExplanation.setText("Explanation");
        editTextExplanation.setLines(10);
        editTextAnswer.setEnabled(false);

        LinearLayout layout2 = new LinearLayout(this);
        LinearLayout.LayoutParams parms2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout2.setOrientation(LinearLayout.VERTICAL);
        layout2.setLayoutParams(parms2);

        //Declare layout style for category's buttons
        LinearLayout.LayoutParams selectCategoryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout2.addView(txtViewExplanation, selectCategoryParams);
        layout2.addView(editTextExplanation, selectCategoryParams);

        mainLayout.addView(layout);
        mainLayout.addView(layout2);

        builder.setView(mainLayout);

        builder.setPositiveButton("i Get It", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        final AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    protected void setNightMode() {
        mPrefs = this.getSharedPreferences("myPreference", 0);
        if(mPrefs.getBoolean("NightMode", true)){
            LinearLayout container = findViewById(R.id.container);
            ImageButton imgBtnBack = findViewById(R.id.imgBtnBack)
                    , imgBtnSearch = findViewById(R.id.imgBtnSearch);
            Button btnCategory = findViewById(R.id.btnCategory);
            FrameLayout questionFrameLayout = findViewById(R.id.questionFrameLayout);

            container.setBackgroundColor(Color.BLACK);
            imgBtnBack.setBackgroundColor(Color.BLACK);
            btnCategory.setBackgroundColor(Color.BLACK);
            btnCategory.setTextColor(Color.BLACK);
            imgBtnSearch.setBackgroundColor(Color.BLACK);
            questionFrameLayout.setBackgroundColor(Color.BLACK);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_previous:
                    if(getFragmentManager()!=null) {
                        getFragmentManager().popBackStack();
                    }else{
                        Toast.makeText(getApplicationContext(), "There are no previous Question", Toast.LENGTH_LONG).show();
                    }
                    return true;
                case R.id.navigation_tips:
                    showHint();
                    return true;
                case R.id.navigation_analyse:
                    showExplanation();
                    return true;
                case R.id.navigation_next:
                    btnCategory = findViewById(R.id.btnCategory);
                    if("Pictionary".equals(btnCategory.getText())){
                        fm.beginTransaction().replace(R.id.questionFrameLayout, pictionaryFragment).addToBackStack(null).commit();

                    }else{
                        fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).addToBackStack(null).commit();
                    }
                    return true;
            }
            return false;
        }
    };
}
