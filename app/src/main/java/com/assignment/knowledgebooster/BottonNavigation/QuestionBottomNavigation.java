package com.assignment.knowledgebooster.BottonNavigation;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.knowledgebooster.Play.PictionaryFragment;
import com.assignment.knowledgebooster.Play.QuestionFragment;
import com.assignment.knowledgebooster.R;

public class QuestionBottomNavigation extends AppCompatActivity {
    TextView  txtViewAnswer, txtViewExplanation;
    EditText editTxtHint,editTextAnswer, editTextExplanation, editTextInputSearchQuestion;
    SharedPreferences mPrefs;
    FragmentManager fm = getSupportFragmentManager();
    QuestionFragment questionFragment = new QuestionFragment();
    PictionaryFragment pictionaryFragment = new PictionaryFragment();
    Button btnCategory;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

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
                        fm.beginTransaction().replace(R.id.questionFrameLayout, questionFragment).addToBackStack(null).commit();
                    }


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_bottom_navigation);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btnCategory = findViewById(R.id.btnCategory);

        Bundle extras = getIntent().getExtras();
        String getMonthlyPickup = extras.getString("Monthly Pickup");
        String getDetective = extras.getString("Detective");
        String getLogic = extras.getString("Logic");
        String getPictionary = extras.getString("Pictionary");
        String getGK = extras.getString("General Knowledge");
        String getMath = extras.getString("Mathematics World");
        String getDecision = extras.getString("Decision Making");

        if(getMonthlyPickup != null){
            btnCategory.setText("Monthly Pickup");
            fm.beginTransaction().replace(R.id.questionFrameLayout, questionFragment).commit();
        }else if(getDetective != null){
            btnCategory.setText("Detective");
            fm.beginTransaction().replace(R.id.questionFrameLayout, questionFragment).commit();
        }else if(getLogic != null){
            btnCategory.setText("Logic");
            fm.beginTransaction().replace(R.id.questionFrameLayout, questionFragment).commit();
        }else if(getPictionary != null){
            btnCategory.setText("Pictionary");
            fm.beginTransaction().replace(R.id.questionFrameLayout, pictionaryFragment).commit();
        }else if(getGK != null){
            btnCategory.setText("General Knowledge");
            fm.beginTransaction().replace(R.id.questionFrameLayout, questionFragment).commit();
        }else if(getMath != null){
            btnCategory.setText("Mathematics World");
            fm.beginTransaction().replace(R.id.questionFrameLayout, questionFragment).commit();
        }else if(getDecision != null){
            btnCategory.setText("Decision Making");
            fm.beginTransaction().replace(R.id.questionFrameLayout, questionFragment).commit();
        }


        setNightMode();
    }

    public void btnSearchOnQuestioClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Search Question");

        editTextInputSearchQuestion = new EditText(this);
        builder.setView(editTextInputSearchQuestion);


        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(QuestionBottomNavigation.this, "Direct to question ID", Toast.LENGTH_LONG).show();
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

        if(mPrefs.getBoolean("NightMode", true) == true){


            ConstraintLayout container;
            ImageButton imgBtnBack, imgBtnSearch;
            Button btnCategory;
            FrameLayout questionFrameLayout;

            container = findViewById(R.id.container);
            imgBtnBack = findViewById(R.id.imgBtnBack);
            btnCategory = findViewById(R.id.btnCategory);
            imgBtnSearch = findViewById(R.id.imgBtnSearch);
            questionFrameLayout = findViewById(R.id.questionFrameLayout);

            container.setBackgroundColor(Color.BLACK);
            imgBtnBack.setBackgroundColor(Color.BLACK);
            btnCategory.setBackgroundColor(Color.BLACK);
            btnCategory.setTextColor(Color.BLACK);
            imgBtnSearch.setBackgroundColor(Color.BLACK);
            questionFrameLayout.setBackgroundColor(Color.BLACK);

        }

    }
}
