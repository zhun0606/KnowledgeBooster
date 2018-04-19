package com.assignment.knowledgebooster.main;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.assignment.knowledgebooster.FirebaseClass.User;
import com.assignment.knowledgebooster.Fragment.QuestionPictionaryFragment;
import com.assignment.knowledgebooster.Fragment.QuestionSelectionFragment;
import com.assignment.knowledgebooster.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question extends AppCompatActivity {
    public Questions questions;
    public Questions questionsToDisplay;
    public QuestionAnswered questionAnswered;

    public User currentUser;
    Selection selectionQuestion;
    Pictionary pictionaryQuestion;
    Scramble scrambleQuestion;

    TextView  txtViewAnswer, txtViewExplanation;
    EditText editTxtHint,editTextAnswer, editTextExplanation, editTextInputSearchQuestion;
    Button btnCategory;
    BottomNavigationView navigation;

    public FragmentManager fm = getSupportFragmentManager();
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
        retrieveUser();

        setNightMode();
    }

    public  void checkQuestionTypeAndDisplay(){
        try{
            Bundle extras = getIntent().getExtras();
            if(extras.getString("Monthly Pickup")  != null){
                btnCategory.setText("Monthly Pickup");
                extras.putString("text1","hello");
                selectionQuestion = displayQuestionSelection();
                selectionFragment.setArguments(selectionQuestion);
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
            else if(extras.getString("Detective") != null){
                btnCategory.setText("Detective");
                selectionQuestion = displayQuestionSelection();
                selectionFragment.setArguments(selectionQuestion);
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
            else if(extras.getString("Logic") != null){
                btnCategory.setText("Logic");
                selectionQuestion = displayQuestionSelection();
                selectionFragment.setArguments(selectionQuestion);
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
            else if(extras.getString("Pictionary") != null){
                btnCategory.setText("Pictionary");
                fm.beginTransaction().replace(R.id.questionFrameLayout, pictionaryFragment).commit();
            }
            else if(extras.getString("General Knowledge") != null){
                btnCategory.setText("General Knowledge");
                selectionQuestion = displayQuestionSelection();
                selectionFragment.setArguments(selectionQuestion);
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
            else if(extras.getString("Mathematics World") != null){
                btnCategory.setText("Mathematics World");
                selectionQuestion = displayQuestionSelection();
                selectionFragment.setArguments(selectionQuestion);
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
            else if(extras.getString("Decision Making") != null){
                btnCategory.setText("Decision Making");
                selectionQuestion = displayQuestionSelection();
                selectionFragment.setArguments(selectionQuestion);
                fm.beginTransaction().replace(R.id.questionFrameLayout, selectionFragment).commit();
            }
        }catch (NullPointerException ex){
            ex.getStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    ////    Retrieving Question from FireBase and Removing Answered Question before Display   ////
    //////////////////////////////////////////////////////////////////////////////////////////////
    public void retrieveUser() {
        DatabaseReference databaseQuestion = FirebaseDatabase.getInstance().getReference();
        databaseQuestion.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentUser = dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(User.class);
                retrieveQuestion();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void retrieveQuestion() {
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
    public void retrieveAnsweredQuestion() {
        DatabaseReference databaseQuestion = FirebaseDatabase.getInstance().getReference();
        databaseQuestion.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questionAnswered = dataSnapshot.child("questionAnswered").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(QuestionAnswered.class);
                while (questionAnswered.getPictionaries().remove(null));
                while (questionAnswered.getScrambles().remove(null));
                while (questionAnswered.getSelections().remove(null));
                retrieveQuestion1();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void retrieveQuestion1() {
        DatabaseReference databaseQuestion = FirebaseDatabase.getInstance().getReference();
        databaseQuestion.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questionsToDisplay = dataSnapshot.child("questions").getValue(Questions.class);
                while (questionsToDisplay.getPictionaries().remove(null));
                while (questionsToDisplay.getScrambles().remove(null));
                while (questionsToDisplay.getSelections().remove(null));
                removeAnsweredQuestion();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void removeAnsweredQuestion(){
        ArrayList<Pictionary> questionPictionaryToBeRemove = new ArrayList<>();
        ArrayList<Selection> questionSelectionToBeRemove = new ArrayList<>();
        ArrayList<Scramble> questionScrambleToBeRemove = new ArrayList<>();

        for(String ss : questionAnswered.getPictionaries()){
            for(Pictionary pictionaryQuestion : questionsToDisplay.getPictionaries()){
                try{
                    if(pictionaryQuestion.getQuestionId().equals(ss))
                        questionPictionaryToBeRemove.add(pictionaryQuestion);
                }catch(Exception ex){ ex.printStackTrace(); }
            }
        }questionsToDisplay.getPictionaries().removeAll(questionPictionaryToBeRemove);

        for(String ss : questionAnswered.getScrambles()){
            for(Scramble scrambleQuestion : questionsToDisplay.getScrambles()){
                try{
                    if(scrambleQuestion.getQuestionId().equals(ss))
                        questionScrambleToBeRemove.add(scrambleQuestion);
                }catch(Exception ex){ ex.printStackTrace(); }
            }
        }questionsToDisplay.getScrambles().removeAll(questionScrambleToBeRemove);

        for(String ss : questionAnswered.getSelections()){
            for(Selection selectionQuestion : questionsToDisplay.getSelections()){
                try{
                    if(selectionQuestion.getQuestionId().equals(ss))
                        questionSelectionToBeRemove.add(selectionQuestion);
                }catch(Exception ex){ ex.printStackTrace(); }
            }
        }questionsToDisplay.getSelections().removeAll(questionSelectionToBeRemove);

        checkQuestionTypeAndDisplay();
    }

    public Scramble displayQuestionScramble(){
        ArrayList<Scramble> scrambleQuestion = questions.getScrambles();
        // shuffle the question
        Collections.shuffle(scrambleQuestion);
        //return first question
        return scrambleQuestion.get(0);
    }
    public Pictionary displayQuestionPictionary(){
        ArrayList<Pictionary> pictionaryQuestion = questions.getPictionaries();
        // shuffle the question
        Collections.shuffle(pictionaryQuestion);
        //return first question
        return pictionaryQuestion.get(0);
    }
    public Selection displayQuestionSelection(){
        //Questions questionsToDisplay = removeAnsweredQuestion();
        ArrayList<Selection> selectionQuestion = questionsToDisplay.getSelections();
        // shuffle the question
        Collections.shuffle(selectionQuestion);
        //return first question
        return selectionQuestion.get(0);
    }

    /////////////////////////////////////////////////////
    ////    Validate User Answering the Question    ////
    ////////////////////////////////////////////////////
    public void answeringQuestion(View view) {
        int viewMode = view.getId();
        switch (viewMode){
            case R.id.btnA:
                if(selectionFragment.btnA.getText().equals(selectionFragment.ques.getAnswer())){
                    correctSelectionAnswer(selectionQuestion);
                }else{
                    wrongSelectionAnswer(selectionQuestion);
                }
                break;
            case R.id.btnB:
                if(selectionFragment.btnB.getText().equals(selectionFragment.ques.getAnswer())){
                    correctSelectionAnswer(selectionQuestion);
                }else{
                    wrongSelectionAnswer(selectionQuestion);
                }
                break;
            case R.id.btnC:
                if(selectionFragment.btnC.getText().equals(selectionFragment.ques.getAnswer())){
                    correctSelectionAnswer(selectionQuestion);
                }else{
                    wrongSelectionAnswer(selectionQuestion);
                }
                break;
            case R.id.btnD:
                if(selectionFragment.btnD.getText().equals(selectionFragment.ques.getAnswer())){
                    correctSelectionAnswer(selectionQuestion);
                }else{
                    wrongSelectionAnswer(selectionQuestion);
                }
                break;
        }
        callUpdate();
    }
    protected void correctSelectionAnswer(Selection selection){
        // update Question Answered
        questionAnswered.getSelections().add(selection.getQuestionId());
        ///////////// indexfound -1
        for(Selection compare : questions.getSelections()){
            if(compare.equals(selection))
                Log.w("",compare.toString());
        }

        int index = questions.getSelections().indexOf(selection);
        int totalQuestion = questions.getSelections().get(index).getTotalAnswer() + 1;
        int correctAnswer = questions.getSelections().get(index).getCorrectAnswer() + 1;
        questions.getSelections().get(index).setTotalAnswer(totalQuestion);
        questions.getSelections().get(index).setCorrectAnswer(correctAnswer);

        int userTotalQuestionAnswered = currentUser.getTotalQuestionAnswered() + 1;
        int userTotalCorrectQuestionAnswered = currentUser.getTotalCorrectQuestionAnswered() + 1;
        currentUser.setTotalQuestionAnswered(userTotalQuestionAnswered);
        currentUser.setTotalCorrectQuestionAnswered(userTotalCorrectQuestionAnswered);
        Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
    }
    protected void correctPictionaryAnswer(Pictionary pictionary){
        questionAnswered.getPictionaries().add(pictionary.getQuestionId());

        int index = questions.getPictionaries().indexOf(pictionary);
        int totalQuestion = questions.getPictionaries().get(index).getTotalAnswers() + 1;
        int correctAnswer = questions.getPictionaries().get(index).getCorrectAnswer() + 1;
        questions.getPictionaries().get(index).setTotalAnswers(totalQuestion);
        questions.getPictionaries().get(index).setCorrectAnswer(correctAnswer);

        int userTotalQuestionAnswered = currentUser.getTotalQuestionAnswered() + 1;
        int userTotalCorrectQuestionAnswered = currentUser.getTotalCorrectQuestionAnswered() + 1;
        currentUser.setTotalQuestionAnswered(userTotalQuestionAnswered);
        currentUser.setTotalCorrectQuestionAnswered(userTotalCorrectQuestionAnswered);
        Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
    }
    protected void correctScrambleAnswer(Scramble scramble){
        questionAnswered.getScrambles().add(scramble.getQuestionId());

        int index = questions.getScrambles().indexOf(scramble);
        int totalQuestion = questions.getScrambles().get(index).getTotalAnswers() + 1;
        int correctAnswer = questions.getScrambles().get(index).getCorrectAnswer() + 1;
        questions.getScrambles().get(index).setTotalAnswers(totalQuestion);
        questions.getScrambles().get(index).setCorrectAnswer(correctAnswer);

        int userTotalQuestionAnswered = currentUser.getTotalQuestionAnswered() + 1;
        int userTotalCorrectQuestionAnswered = currentUser.getTotalCorrectQuestionAnswered() + 1;
        currentUser.setTotalQuestionAnswered(userTotalQuestionAnswered);
        currentUser.setTotalCorrectQuestionAnswered(userTotalCorrectQuestionAnswered);
        Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
    }
    protected void wrongSelectionAnswer(Selection selection){
        int index = questions.getSelections().indexOf(selection);
        int totalQuestion = questions.getSelections().get(index).getTotalAnswer() + 1;
        questions.getSelections().get(index).setTotalAnswer(totalQuestion);
        int userTotalQuestionAnswered = currentUser.getTotalQuestionAnswered() + 1;
        currentUser.setTotalQuestionAnswered(userTotalQuestionAnswered);
        Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
    }
    protected void wrongPictionaryAnswer(Pictionary pictionary){
        questionAnswered.getPictionaries().add(pictionary.getQuestionId());

        int index = questions.getPictionaries().indexOf(pictionary);
        int totalQuestion = questions.getPictionaries().get(index).getTotalAnswers() + 1;
        questions.getPictionaries().get(index).setTotalAnswers(totalQuestion);

        int userTotalQuestionAnswered = currentUser.getTotalQuestionAnswered() + 1;
        currentUser.setTotalQuestionAnswered(userTotalQuestionAnswered);
        Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
    }
    protected void wrongScrambleAnswer(Scramble scramble){
        questionAnswered.getScrambles().add(scramble.getQuestionId());

        int index = questions.getScrambles().indexOf(scramble);
        int totalQuestion = questions.getScrambles().get(index).getTotalAnswers() + 1;
        questions.getScrambles().get(index).setTotalAnswers(totalQuestion);

        int userTotalQuestionAnswered = currentUser.getTotalQuestionAnswered() + 1;;
        currentUser.setTotalQuestionAnswered(userTotalQuestionAnswered);
        Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
    }

    ////////////////////////////////////////////////
    ////    Updating Firebase after Answering   ////
    ////////////////////////////////////////////////
    protected void callUpdate(){
        updateUser();
        updateQuestionAnswered();
        updateQuestions();
    }
    public void updateUser(){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child(currentUser.getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    databaseReference.setValue(currentUser);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
    public void updateQuestionAnswered(){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("questionAnswered").child(currentUser.getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    databaseReference.setValue(questionAnswered);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });}
    public void updateQuestions(){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("questions");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    databaseReference.setValue(questions);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
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
