package com.assignment.knowledgebooster.BottonNavigation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
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
import android.widget.ToggleButton;

import com.assignment.knowledgebooster.CreateQuestion.CreateQuestionActivity;
import com.assignment.knowledgebooster.CreateQuestion.MyQuestion;
import com.assignment.knowledgebooster.Message.MessageMainFragment;
import com.assignment.knowledgebooster.OtherUserProfile.OtherUserProfile;
import com.assignment.knowledgebooster.Play.HomePageFragment;
import com.assignment.knowledgebooster.OwnProfile.OwnDetailActivity;
import com.assignment.knowledgebooster.OwnProfile.OwnProfileFragment;
import com.assignment.knowledgebooster.Play.PictionaryFragment;
import com.assignment.knowledgebooster.Play.QuestionFragment;
import com.assignment.knowledgebooster.R;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    EditText editTextInputSearchQuestion;
    HomePageFragment homePageFragment = new HomePageFragment();
    OwnProfileFragment ownProfileFragment = new OwnProfileFragment();
    MessageMainFragment messageMainFragment = new MessageMainFragment();
    Button btnCategory;
    FragmentManager fm = getSupportFragmentManager();
    SharedPreferences mPrefs;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().replace(R.id.frameLayout, homePageFragment).commit();
                    return true;
                case R.id.navigation_news:
                    directNews();
                    return true;
                case R.id.navigation_message:
                    fm.beginTransaction().replace(R.id.frameLayout, messageMainFragment).commit();
                    return true;
                case R.id.navigation_me:
                    fm.beginTransaction().replace(R.id.frameLayout, ownProfileFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fm.beginTransaction().replace(R.id.frameLayout, homePageFragment).commit();
        mPrefs = this.getSharedPreferences("myPreference",0);

         if(mPrefs.getBoolean("NightMode", true) == true){

             setNightMode();
          }


    }

    protected void mCallOwnDetails(View v){
        Intent intent = new Intent(this, OwnDetailActivity.class);
        this.startActivityForResult(intent, 1);
    }


    public void btnOnClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        QuestionFragment questionFragment = new QuestionFragment();
        PictionaryFragment pictionaryFragment = new PictionaryFragment();
        switch (view.getId()){


            case R.id.imgBtnMontlyPickup:
                Intent intentPassMonthly = new Intent(this, QuestionBottomNavigation.class);
                intentPassMonthly.putExtra("Monthly Pickup", "Monthly Pickup");
                startActivity(intentPassMonthly);

                break;

            case R.id.imgBtnDetective:
                Intent intentPassDective = new Intent(this, QuestionBottomNavigation.class);
                intentPassDective.putExtra("Detective", "Detective");
                startActivity(intentPassDective);

                break;

            case R.id.imgBtnLogic:
                Intent intentPassLogic = new Intent(this, QuestionBottomNavigation.class);
                intentPassLogic.putExtra("Logic", "Logic");
                startActivity(intentPassLogic);

                break;

            case R.id.imgBtnPictionary:
                Intent intentPassPictionary = new Intent(this, QuestionBottomNavigation.class);
                intentPassPictionary.putExtra("Pictionary", "Pictionary");
                startActivity(intentPassPictionary);

                break;

            case R.id.imgBtnGeneralKnowledge:
                Intent intentPassGK = new Intent(this, QuestionBottomNavigation.class);
                intentPassGK.putExtra("General Knowledge", "General Knowledge");
                startActivity(intentPassGK);

                break;

            case R.id.imgBtnMath:
                Intent intentPassMath = new Intent(this, QuestionBottomNavigation.class);
                intentPassMath.putExtra("Mathematics World", "Mathematics World");
                startActivity(intentPassMath);

                break;

            case R.id.imgBtnDecision:
                Intent intentPassDecision = new Intent(this, OtherUserProfile.class);
            //    intentPassDecision.putExtra("Decision Making", "Decision Making");
                startActivity(intentPassDecision);
                break;
        }
    }

    protected void redirectMyQuestion(View v){
        Intent intent = new Intent(this, MyQuestion.class);
        this.startActivity(intent);
    }

    protected void directNews(){
        Intent intentNews = new Intent(this, NewsBottomNavigation.class);
        startActivityForResult(intentNews, 99);
    }

    public void btnSearchOnQuestioClick(View v){
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


        protected void nightMode(){

            SharedPreferences settings = getSharedPreferences("myPreference",0);
        SharedPreferences.Editor editor = settings.edit();


        ToggleButton toggleButtonNightMode = new ToggleButton(this);
        toggleButtonNightMode = findViewById(R.id.toggleNightMode);

        if(toggleButtonNightMode.isChecked()){
            editor.putBoolean("NightMode", true);
                setNightMode();
            fm.beginTransaction().detach(ownProfileFragment).attach(ownProfileFragment).commit();
        }else{
            editor.putBoolean("NightMode", false);
            cancelNightMode();
            fm.beginTransaction().detach(ownProfileFragment).attach(ownProfileFragment).commit();

        }

        editor.commit();
    }

    protected void cacatMode(){

        SharedPreferences settings = getSharedPreferences("myPreference",0);
        SharedPreferences.Editor editor = settings.edit();


        ToggleButton toggleButtonCacat = new ToggleButton(this);
        toggleButtonCacat = findViewById(R.id.toggleCacat);
        if(toggleButtonCacat.isChecked()){
            editor.putBoolean("CacatMode", true);

        }else{
            editor.putBoolean("CacatMode", false);
        }

        editor.commit();

    }

    protected void setNightMode(){

            LinearLayout mainHeader;
            ImageButton imgBtnBack, imgBtnSearch;
            TextView txtHeader;
            ConstraintLayout container;

            mainHeader = findViewById(R.id.mainHeader);
            mainHeader.setBackgroundColor(Color.BLACK);
            imgBtnBack = findViewById(R.id.imgBtnBack);
            imgBtnBack.setBackgroundColor(Color.BLACK);
            txtHeader = findViewById(R.id.txtHeader);
            txtHeader.setBackgroundColor(Color.BLACK);
            txtHeader.setTextColor(Color.WHITE);
            imgBtnSearch = findViewById(R.id.imgBtnSearch);
            imgBtnSearch.setBackgroundColor(Color.BLACK);
            container = findViewById(R.id.container);
            container.setBackgroundColor(Color.BLACK);

    }

    protected void cancelNightMode(){
        LinearLayout mainHeader;
        ImageButton imgBtnBack, imgBtnSearch;
        TextView txtHeader;
        ConstraintLayout container;

        mainHeader = findViewById(R.id.mainHeader);
        mainHeader.setBackgroundResource(R.color.colorAccent);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnBack.setBackgroundColor(Color.TRANSPARENT);
        txtHeader = findViewById(R.id.txtHeader);
        txtHeader.setBackgroundColor(Color.TRANSPARENT);
        txtHeader.setTextColor(Color.BLACK);
        imgBtnSearch = findViewById(R.id.imgBtnSearch);
        imgBtnSearch.setBackgroundColor(Color.TRANSPARENT);
        container = findViewById(R.id.container);
        container.setBackgroundResource(R.color.lightgray);
    }


    }



