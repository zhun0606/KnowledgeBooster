package com.assignment.knowledgebooster.main;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.knowledgebooster.Fragment.NewsFragment;
import com.assignment.knowledgebooster.News.NewsResultFragment;
import com.assignment.knowledgebooster.News.NewsVoteFragment;
import com.assignment.knowledgebooster.R;

public class News extends AppCompatActivity {
    private TextView mTextMessage;
    FragmentManager fm = getSupportFragmentManager();
    NewsFragment newsFragment = new NewsFragment();
    NewsVoteFragment newsVoteFragment = new NewsVoteFragment();
    NewsResultFragment newsResultFragment = new NewsResultFragment();
    SharedPreferences mPrefs;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.news_navigation_back:
                    if(getSupportFragmentManager() != null){
                        getSupportFragmentManager().popBackStack();
                    }
                    return true;
                case R.id.news_navigation_conmment:
                    Toast.makeText(News.this, "Show Comments", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.news_navigation_next:
                    fm.beginTransaction().replace(R.id.newsFrameLayout, newsFragment).commit();
                    fm.beginTransaction().replace(R.id.newsVoteResultFrameLayout, newsVoteFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.newsFrameLayout, newsFragment).commit();
        fm.beginTransaction().add(R.id.newsVoteResultFrameLayout, newsVoteFragment).commit();

        setNightMode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    public void btnNewsAnswerSubmit(View v){
        fm.beginTransaction().replace(R.id.newsVoteResultFrameLayout, newsResultFragment).commit();
    }

    protected void imgBtnNewsBackOnClick(View v){
        finish();
    }

    protected void setNightMode(){
        mPrefs = this.getSharedPreferences("myPreference",0);

        if(mPrefs.getBoolean("NightMode", true) == true){
            LinearLayout newsBottomHeader = findViewById(R.id.newsBottomHeader);
            ImageButton imgBtnBack = findViewById(R.id.imgBtnBack)
                    , imgBtnSearch = findViewById(R.id.imgBtnSearch);
            Button btnCategory = findViewById(R.id.btnCategory);
            FrameLayout newsFrameLayout = findViewById(R.id.newsFrameLayout);

            newsBottomHeader.setBackgroundColor(Color.BLACK);
            imgBtnBack.setBackgroundColor(Color.BLACK);
            btnCategory.setBackgroundColor(Color.BLACK);
            btnCategory.setTextColor(Color.WHITE);
            imgBtnSearch.setBackgroundColor(Color.BLACK);
            newsFrameLayout.setBackgroundColor(Color.BLACK);
        }
    }
}
