package com.assignment.knowledgebooster.OwnProfile;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.assignment.knowledgebooster.R;

import org.w3c.dom.Text;


public class OwnProfileFragment extends Fragment {

    SharedPreferences mPrefs;
    ToggleButton toggleNightMode, toggleCacatMode;
    Button btnFavourite,btnFollowing,btnFollower,btnQuestionAnswered, btnRanking,btnNightMode,btnCacat,btnFeedback,btnSetting;
    RelativeLayout headerLayout;
    TextView txtName, txtPoint, txtPointLabel, txtRanking, txtRankingLabel;
    FrameLayout bigMainlayout;

    public OwnProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_own_profile, container, false);


        bigMainlayout = view.findViewById(R.id.bigMainlayout);
        toggleNightMode = view.findViewById(R.id.toggleNightMode);
        toggleCacatMode = view.findViewById(R.id.toggleCacat);
        txtName = view.findViewById(R.id.txtName);
        txtPoint = view.findViewById(R.id.txtPoint);
        txtRanking = view.findViewById(R.id.txtRanking);
        txtPointLabel = view.findViewById(R.id.txtPointLabel);
        txtRankingLabel = view.findViewById(R.id.txtRankingLabel);
        btnQuestionAnswered = view.findViewById(R.id.btnQuestionAnswered);
        btnFollowing = view.findViewById(R.id.btnFollowing);
        btnFollower = view.findViewById(R.id.btnFollower);
        btnFavourite = view.findViewById(R.id.btnFavourite);
        btnRanking = view.findViewById(R.id.btnRanking);
        btnNightMode = view.findViewById(R.id.btnNightMode);
        btnCacat = view.findViewById(R.id.btnCacat);
        btnFeedback = view.findViewById(R.id.btnFeedback);
        btnSetting = view.findViewById(R.id.btnSetting);
        headerLayout = view.findViewById(R.id.headerLayout);




        mPrefs = this.getActivity().getSharedPreferences("myPreference",0);


            toggleNightMode.setChecked(mPrefs.getBoolean("NightMode", true));


            toggleCacatMode.setChecked(mPrefs.getBoolean("CacatMode", true));


        mPrefs = this.getActivity().getSharedPreferences("myPreference",0);

        if(mPrefs.getBoolean("NightMode", true) == true){
            setNightMode();
        }else{
            cancelNightMode();
        }




        return view;
    }

    protected void setNightMode(){



            headerLayout.setBackgroundColor(Color.BLACK);
            txtName.setTextColor(Color.WHITE);
            txtPoint.setTextColor(Color.WHITE);
            txtRanking.setTextColor(Color.WHITE);
            txtPointLabel.setTextColor(Color.WHITE);
            txtRankingLabel.setTextColor(Color.WHITE);
            btnQuestionAnswered.setBackgroundColor(Color.BLACK);
            btnQuestionAnswered.setTextColor(Color.WHITE);
            btnFollowing.setBackgroundColor(Color.BLACK);
            btnFollowing.setTextColor(Color.WHITE);
            btnFollower.setBackgroundColor(Color.BLACK);
            btnFollower.setTextColor(Color.WHITE);


            btnFavourite.setBackgroundColor(Color.BLACK);
            btnFavourite.setTextColor(Color.WHITE);
            btnRanking.setBackgroundColor(Color.BLACK);
            btnRanking.setTextColor(Color.WHITE);
            btnNightMode.setBackgroundColor(Color.BLACK);
            btnNightMode.setTextColor(Color.WHITE);
            btnCacat.setBackgroundColor(Color.BLACK);
            btnCacat.setTextColor(Color.WHITE);
            btnFeedback.setBackgroundColor(Color.BLACK);
            btnFeedback.setTextColor(Color.WHITE);
            btnSetting.setBackgroundColor(Color.BLACK);
            btnSetting.setTextColor(Color.WHITE);
            toggleNightMode.setBackgroundColor(Color.BLACK);
            toggleCacatMode.setBackgroundColor(Color.BLACK);

            bigMainlayout.setBackgroundColor(Color.BLACK);

    }

    protected   void cancelNightMode(){
        headerLayout.setBackgroundResource(R.color.colorAccent);
        txtName.setTextColor(Color.BLACK);
        txtPoint.setTextColor(Color.BLACK);
        txtRanking.setTextColor(Color.BLACK);
        txtPointLabel.setTextColor(Color.BLACK);
        txtRankingLabel.setTextColor(Color.BLACK);
        btnQuestionAnswered.setBackgroundColor(Color.WHITE);
        btnQuestionAnswered.setTextColor(Color.BLACK);
        btnFollowing.setBackgroundColor(Color.WHITE);
        btnFollowing.setTextColor(Color.BLACK);
        btnFollower.setBackgroundColor(Color.WHITE);
        btnFollower.setTextColor(Color.BLACK);


        btnFavourite.setBackgroundColor(Color.WHITE);
        btnFavourite.setTextColor(Color.BLACK);
        btnRanking.setBackgroundColor(Color.WHITE);
        btnRanking.setTextColor(Color.BLACK);
        btnNightMode.setBackgroundColor(Color.WHITE);
        btnNightMode.setTextColor(Color.BLACK);
        btnCacat.setBackgroundColor(Color.WHITE);
        btnCacat.setTextColor(Color.BLACK);
        btnFeedback.setBackgroundColor(Color.WHITE);
        btnFeedback.setTextColor(Color.BLACK);
        btnSetting.setBackgroundColor(Color.WHITE);
        btnSetting.setTextColor(Color.BLACK);
        toggleNightMode.setBackgroundColor(Color.WHITE);
        toggleCacatMode.setBackgroundColor(Color.WHITE);

        bigMainlayout.setBackgroundResource(R.color.colorAccent);
    }

}
