package com.assignment.knowledgebooster.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.assignment.knowledgebooster.CreateQuestion.MyQuestion;
import com.assignment.knowledgebooster.FirebaseClass.Questions;
import com.assignment.knowledgebooster.Fragment.NewsFragment;
import com.assignment.knowledgebooster.Fragment.MessageMainFragment;
import com.assignment.knowledgebooster.OtherUserProfile;
import com.assignment.knowledgebooster.Fragment.HomePageFragment;
import com.assignment.knowledgebooster.Fragment.OwnProfileFragment;
import com.assignment.knowledgebooster.R;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    Questions questions;
    ImageButton imgBtnQuestion,imgBtnSearch;
    TextView txtHeader;
    EditText editTextInputSearchQuestion;
    BottomNavigationView navigation;
    LinearLayout mainHeader;
    HomePageFragment homePageFragment;
    NewsFragment newsFragment;
    OwnProfileFragment ownProfileFragment;
    MessageMainFragment messageMainFragment;

    FragmentManager fm = getSupportFragmentManager();
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        imgBtnQuestion = findViewById(R.id.imgBtnQuestion);
        imgBtnSearch = findViewById(R.id.imgBtnSearch);
        txtHeader = findViewById(R.id.txtHeader);
        navigation = findViewById(R.id.navigation);
        mainHeader = findViewById(R.id.mainHeader);

        homePageFragment = new HomePageFragment();
        newsFragment = new NewsFragment();
        ownProfileFragment = new OwnProfileFragment();
        messageMainFragment = new MessageMainFragment();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().replace(R.id.frameLayout, homePageFragment).commit();
        mPrefs = this.getSharedPreferences("myPreference",0);

        retrieveQuestion();
    }

    public void btnOnClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.imgBtnMonthlyPickup:
                intent = new Intent(this, QuestionBottomNavigation.class);
                intent.putExtra("Monthly Pickup", "Monthly Pickup");
                break;
            case R.id.imgBtnDetective:
                intent = new Intent(this, QuestionBottomNavigation.class);
                intent.putExtra("Detective", "Detective");
                break;
            case R.id.imgBtnLogic:
                intent = new Intent(this, QuestionBottomNavigation.class);
                intent.putExtra("Logic", "Logic");
                break;

            case R.id.imgBtnPictionary:
                intent = new Intent(this, QuestionBottomNavigation.class);
                intent.putExtra("Pictionary", "Pictionary");
                break;

            case R.id.imgBtnGeneralKnowledge:
                intent = new Intent(this, QuestionBottomNavigation.class);
                intent.putExtra("General Knowledge", "General Knowledge");
                break;

            case R.id.imgBtnMath:
                intent = new Intent(this, QuestionBottomNavigation.class);
                intent.putExtra("Mathematics World", "Mathematics World");
                break;

            case R.id.imgBtnDecision:
                intent = new Intent(this, OtherUserProfile.class);
                //intentPassDecision.putExtra("Decision Making", "Decision Making");
                break;
        }
        startActivity(intent);
    }

    public void redirectQuestion(View view) {
        Intent intent = new Intent(this, MyQuestion.class);
        this.startActivity(intent);
    }

    public void btnSearchOnQuestionClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Search Question");

        editTextInputSearchQuestion = new EditText(this);
        builder.setView(editTextInputSearchQuestion);

        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Direct to question ID", Toast.LENGTH_LONG).show();
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

    public void btnMsgOnClick(View v){
        switch(v.getId()){
            case R.id.btnComment:
                break;
            case R.id.btnTag:
                break;
            case R.id.btnLike:
                break;
            case R.id.btnSystemMsg:
                break;
        }
    }

    public void toggleOnClick(View v) {
        switch (v.getId()) {
            case R.id.toggleNightMode:
                nightMode();
                break;
            case R.id.btnNightMode:
                nightMode();
                break;
            case R.id.toggleCacat:
                cacatMode();
                break;
            case R.id.btnCacat:
                cacatMode();
                break;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ViewGroup.LayoutParams params = mainHeader.getLayoutParams();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mainHeader.setVisibility(View.VISIBLE);
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    imgBtnQuestion.setVisibility(View.VISIBLE);
                    imgBtnSearch.setVisibility(View.VISIBLE);
                    txtHeader.setText("Question Bank");
                    fm.beginTransaction().replace(R.id.frameLayout, homePageFragment).commit();
                    return true;
                case R.id.navigation_news:
                    mainHeader.setVisibility(View.VISIBLE);
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    imgBtnQuestion.setVisibility(View.INVISIBLE);
                    imgBtnSearch.setVisibility(View.INVISIBLE);
                    txtHeader.setText("News");
                    fm.beginTransaction().replace(R.id.frameLayout, newsFragment).commit();
                    return true;
                case R.id.navigation_message:
                    mainHeader.setVisibility(View.INVISIBLE);
                    params.height = 0;
                    fm.beginTransaction().replace(R.id.frameLayout, messageMainFragment).commit();
                    return true;
                case R.id.navigation_me:
                    mainHeader.setVisibility(View.INVISIBLE);
                    params.height = 0;
                    fm.beginTransaction().replace(R.id.frameLayout, ownProfileFragment).commit();
                    return true;
            }
            mainHeader.setLayoutParams(params);
            return false;
        }
    };
    protected void directNews(){
        Intent intentNews = new Intent(this, News.class);
        startActivityForResult(intentNews, 99);
    }
    protected void cacatMode(){
        SharedPreferences settings = getSharedPreferences("myPreference",0);
        SharedPreferences.Editor editor = settings.edit();
        ToggleButton toggleButtonCacat =  findViewById(R.id.toggleCacat);
        if(toggleButtonCacat.isChecked()){
            editor.putBoolean("CacatMode", true);
        }else{
            editor.putBoolean("CacatMode", false);
        }
        editor.commit();
    }
    protected void nightMode(){
        SharedPreferences settings = getSharedPreferences("myPreference",0);
        SharedPreferences.Editor editor = settings.edit();

        ToggleButton toggleButtonNightMode = findViewById(R.id.toggleNightMode);

        if(toggleButtonNightMode.isChecked()){
            editor.putBoolean("NightMode", true);
            setNightMode();
            fm.beginTransaction().detach(ownProfileFragment).attach(ownProfileFragment).commit();
        }
        else{
            editor.putBoolean("NightMode", false);
            cancelNightMode();
            fm.beginTransaction().detach(ownProfileFragment).attach(ownProfileFragment).commit();
        }
        editor.apply();
    }

    public void setNightMode(){
        LinearLayout mainHeader = findViewById(R.id.mainHeader);
        ImageButton imgBtnQuestion  = findViewById(R.id.imgBtnQuestion)
                , imgBtnSearch  = findViewById(R.id.imgBtnSearch);
        TextView txtHeader      = findViewById(R.id.txtHeader);
        LinearLayout container  = findViewById(R.id.container);

        mainHeader.setBackgroundColor(Color.BLACK);
        imgBtnQuestion.setBackgroundColor(Color.BLACK);
        txtHeader.setBackgroundColor(Color.BLACK);
        txtHeader.setTextColor(Color.WHITE);
        imgBtnSearch.setBackgroundColor(Color.BLACK);
        container.setBackgroundColor(Color.BLACK);
    }
    protected void cancelNightMode(){
        setTheme(R.style.AppTheme);

        LinearLayout mainHeader = findViewById(R.id.mainHeader)
                   , container  = findViewById(R.id.container);
        ImageButton imgBtnQuestion  = findViewById(R.id.imgBtnQuestion)
                , imgBtnSearch  = findViewById(R.id.imgBtnSearch);
        TextView txtHeader      = findViewById(R.id.txtHeader);

        mainHeader.setBackgroundResource(R.color.colorAccent);
        txtHeader.setBackgroundColor(Color.TRANSPARENT);
        txtHeader.setTextColor(Color.BLACK);

        imgBtnQuestion.setBackgroundColor(Color.TRANSPARENT);
        imgBtnSearch.setBackgroundColor(Color.TRANSPARENT);
        container.setBackgroundResource(R.color.lightgray);
    }

    protected void retrieveQuestion(){
        DatabaseReference databaseQuestion = FirebaseDatabase.getInstance().getReference().child("questions");
        databaseQuestion.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questions = dataSnapshot.getValue(Questions.class);
                if(questions == null){
                    Toast.makeText(getApplicationContext(), "Purchases Empty ! Please Purchase An Item First Before Proceed.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Questions Retrieved Successfully.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    protected void mCallOwnDetails(View v){
        Intent intent = new Intent(this, OwnDetailActivity.class);
        this.startActivityForResult(intent, 1);
    }
    */

}



