package com.assignment.knowledgebooster.CreateQuestion;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.knowledgebooster.R;

public class CreateQuestionActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnMCQ, btnCrossword, btnNews;
    Button btnDetective, btnLogic, btnMath, btnDecision, btnPictionary, btnGE, btnCategory2, btnQuestionCategory2;
    TextView txtViewType, txtViewCategory;
    ImageButton btnQuestionContent2, btnQuestionExplanation2;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        setNightMode();
    }

    protected void btnQuestionCategoryOnClick(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        //Declare main layout
        LinearLayout mainLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(params);

        //Declare buttons for type
        btnMCQ = new Button(this);
        btnCrossword = new Button(this);
        btnNews = new Button(this);
        btnMCQ.setId(Ids.ID_ONE);
        btnCrossword.setId(Ids.ID_TWO);
        btnNews.setId(Ids.ID_THREE);

        btnMCQ.setText("MCQ");
        btnCrossword.setText("Crossword");
        btnNews.setText("News");
        btnMCQ.setBackgroundColor(Color.TRANSPARENT);
        btnCrossword.setBackgroundColor(Color.TRANSPARENT);
        btnNews.setBackgroundColor(Color.TRANSPARENT);

        //Declare layout for type's buttons
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(parms);

        //Declare layout style for type's button
        LinearLayout.LayoutParams selectTypeParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(btnMCQ, selectTypeParams);
        layout.addView(btnCrossword, selectTypeParams);
        layout.addView(btnNews, selectTypeParams);

        //Declare buttons for category
        btnDetective = new Button(this);
        btnLogic = new Button(this);
        btnPictionary = new Button(this);
        btnGE = new Button(this);
        btnMath = new Button(this);
        btnDecision = new Button(this);
        btnDetective.setId(Ids.ID_FOUR);
        btnLogic.setId(Ids.ID_FIVE);
        btnPictionary.setId(Ids.ID_SIX);
        btnGE.setId(Ids.ID_SEVEN);
        btnMath.setId(Ids.ID_EIGHT);
        btnDecision.setId(Ids.ID_NINE);
        btnDetective.setText("Detective");
        btnLogic.setText("Logic");
        btnPictionary.setText("Pictionary");
        btnGE.setText("General Knowledge");
        btnMath.setText("Mathematics World");
        btnDecision.setText("Decision Making");
        btnDetective.setBackgroundColor(Color.TRANSPARENT);
        btnLogic.setBackgroundColor(Color.TRANSPARENT);
        btnPictionary.setBackgroundColor(Color.TRANSPARENT);
        btnGE.setBackgroundColor(Color.TRANSPARENT);
        btnMath.setBackgroundColor(Color.TRANSPARENT);
        btnDecision.setBackgroundColor(Color.TRANSPARENT);

        //Declare layout for category
        LinearLayout layout2 = new LinearLayout(this);
        LinearLayout.LayoutParams parms2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout2.setOrientation(LinearLayout.VERTICAL);
        layout2.setLayoutParams(parms2);

        //Declare layout style for category's buttons
        LinearLayout.LayoutParams selectCategoryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout2.addView(btnDetective, selectCategoryParams);
        layout2.addView(btnLogic, selectCategoryParams);
        layout2.addView(btnPictionary, selectCategoryParams);
        layout2.addView(btnGE, selectCategoryParams);
        layout2.addView(btnMath, selectCategoryParams);
        layout2.addView(btnDecision, selectCategoryParams);

        //Declare text view for both tittle
        LinearLayout layout3 = new LinearLayout(this);
        LinearLayout.LayoutParams parms3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout3.setOrientation(LinearLayout.VERTICAL);
        layout3.setLayoutParams(parms3);
        txtViewType = new TextView(this);
        txtViewType.setText("Type");
        txtViewType.setGravity(Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams textViewTypePrams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout3.addView(txtViewType, textViewTypePrams);

        LinearLayout layout4 = new LinearLayout(this);
        LinearLayout.LayoutParams parms4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parms4.setMargins(0,100,0,0);
        layout4.setOrientation(LinearLayout.VERTICAL);
        layout4.setLayoutParams(parms4);
        txtViewCategory = new TextView(this);
        txtViewCategory.setText("Category");
        txtViewCategory.setGravity(Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams textCategoryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout4.addView(txtViewCategory, textCategoryParams);


        btnMCQ.setOnClickListener(this);
        btnCrossword.setOnClickListener(this);
        btnNews.setOnClickListener(this);
        btnDetective.setOnClickListener(this);
        btnLogic.setOnClickListener(this);
        btnPictionary.setOnClickListener(this);
        btnGE.setOnClickListener(this);
        btnMath.setOnClickListener(this);
        btnDecision.setOnClickListener(this);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnCategory2 = findViewById(R.id.btnQuestionCategory2);
                String s1= "";
                String s2 = "";
                if( ((ColorDrawable)btnMCQ.getBackground()).getColor() == Color.BLUE){
                    s1 = btnMCQ.getText().toString();
                }else if(((ColorDrawable)btnCrossword.getBackground()).getColor() == Color.BLUE){
                    s1 = btnCrossword.getText().toString();
                }else if(((ColorDrawable)btnNews.getBackground()).getColor() == Color.BLUE){
                    s1 = btnNews.getText().toString();
                }

                if(((ColorDrawable)btnDetective.getBackground()).getColor() == Color.BLUE){
                    s2 = btnDetective.getText().toString();
                }else if(((ColorDrawable)btnLogic.getBackground()).getColor() == Color.BLUE){
                    s2 = btnLogic.getText().toString();
                }else if(((ColorDrawable)btnPictionary.getBackground()).getColor() == Color.BLUE){
                    s2 = btnPictionary.getText().toString();
                }else if(((ColorDrawable)btnGE.getBackground()).getColor() == Color.BLUE){
                    s2 = btnGE.getText().toString();
                }else if(((ColorDrawable)btnMath.getBackground()).getColor() == Color.BLUE){
                    s2 = btnMath.getText().toString();
                }else if(((ColorDrawable)btnDecision.getBackground()).getColor() == Color.BLUE){
                    s2 = btnDecision.getText().toString();
                }


                btnCategory2.setText(s1 + "  " + s2);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });



        //Add layouts to main layout
        mainLayout.addView(layout3);
        mainLayout.addView(layout);
        mainLayout.addView(layout4);
        mainLayout.addView(layout2);
        builder.setView(mainLayout);

        final AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View v){
        GradientDrawable gdSelected = new GradientDrawable();
        gdSelected.setColor(0xFF0000FF);
        gdSelected.setCornerRadius(5);
        gdSelected.setStroke(1, 0xFF0000FF);

        GradientDrawable gdNotSelected = new GradientDrawable();
        gdNotSelected.setColor(0xFFFFFFF);
        gdNotSelected.setCornerRadius(5);
        gdNotSelected.setStroke(2, 0xFFFFFFF);



        switch (v.getId()){
            case 1:
                btnCrossword.setBackgroundColor(Color.WHITE);
                btnNews.setBackgroundColor(Color.WHITE);
                btnMCQ.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                btnCrossword.setBackgroundColor(Color.BLUE);
                btnNews.setBackgroundColor(Color.WHITE);
                btnMCQ.setBackgroundColor(Color.WHITE);
                break;
            case 3:
                btnCrossword.setBackgroundColor(Color.WHITE);
                btnNews.setBackgroundColor(Color.BLUE);
                btnMCQ.setBackgroundColor(Color.WHITE);
                break;
            case 4:
                btnDetective.setBackgroundColor(Color.BLUE);
                btnLogic.setBackgroundColor(Color.WHITE);
                btnPictionary.setBackgroundColor(Color.WHITE);
                btnGE.setBackgroundColor(Color.WHITE);
                btnMath.setBackgroundColor(Color.WHITE);
                btnDecision.setBackgroundColor(Color.WHITE);
                break;
            case 5:
                btnDetective.setBackgroundColor(Color.WHITE);
                btnLogic.setBackgroundColor(Color.BLUE);
                btnPictionary.setBackgroundColor(Color.WHITE);
                btnGE.setBackgroundColor(Color.WHITE);
                btnMath.setBackgroundColor(Color.WHITE);
                btnDecision.setBackgroundColor(Color.WHITE);
                break;
            case 6:
                btnDetective.setBackgroundColor(Color.WHITE);
                btnLogic.setBackgroundColor(Color.WHITE);
                btnPictionary.setBackgroundColor(Color.BLUE);
                btnGE.setBackgroundColor(Color.WHITE);
                btnMath.setBackgroundColor(Color.WHITE);
                btnDecision.setBackgroundColor(Color.WHITE);
                break;
            case 7:
                btnDetective.setBackgroundColor(Color.WHITE);
                btnLogic.setBackgroundColor(Color.WHITE);
                btnPictionary.setBackgroundColor(Color.WHITE);
                btnGE.setBackgroundColor(Color.BLUE);
                btnMath.setBackgroundColor(Color.WHITE);
                btnDecision.setBackgroundColor(Color.WHITE);
                break;
            case 8:
                btnDetective.setBackgroundColor(Color.WHITE);
                btnLogic.setBackgroundColor(Color.WHITE);
                btnPictionary.setBackgroundColor(Color.WHITE);
                btnGE.setBackgroundColor(Color.WHITE);
                btnMath.setBackgroundColor(Color.BLUE);
                btnDecision.setBackgroundColor(Color.WHITE);
                break;
            case 9:
                btnDetective.setBackgroundColor(Color.WHITE);
                btnLogic.setBackgroundColor(Color.WHITE);
                btnPictionary.setBackgroundColor(Color.WHITE);
                btnGE.setBackgroundColor(Color.WHITE);
                btnMath.setBackgroundColor(Color.WHITE);
                btnDecision.setBackgroundColor(Color.BLUE);
                break;

        }
    }

    public void directSetQuestion(View v){
        Intent intent = new Intent(this, SetQuestionActivity.class);
        startActivityForResult(intent, 1);
    }

    public  void setExplanation(View v){
        Intent intent = new Intent(this, SetExplanationActivity.class);
        startActivityForResult(intent, 2);
    }


    protected void btnDoneCreateQuestion(View v){
        btnQuestionContent2 = findViewById(R.id.btnQuestionContent2);
        btnQuestionCategory2 = findViewById(R.id.btnQuestionCategory2);
        btnQuestionExplanation2 = findViewById(R.id.btnQuestionExplanation2);

            if(btnQuestionCategory2.getText().toString().trim() == ""){
            Toast.makeText(this, "Please set the question context", Toast.LENGTH_LONG).show();
        }else if (btnQuestionContent2.getTag().equals("null")) {
            Toast.makeText(this, "Please enter an explanation for the question", Toast.LENGTH_LONG).show();
        }else if (btnQuestionExplanation2.equals("null")){
            Toast.makeText(this, "Please select a category", Toast.LENGTH_LONG).show();
        }
    }

    public class Ids{
        public static final int ID_ONE = 1; //btnMCQ
        public static final int ID_TWO = 2; //btnCrossword
        public static final int ID_THREE = 3; //btnNews
        public static final int ID_FOUR = 4; //btnDetective
        public static final int ID_FIVE = 5; //btnLogic
        public static final int ID_SIX = 6; //btnPictionary
        public static final int ID_SEVEN = 7; //btnGE
        public static final int ID_EIGHT = 8; //btnMath
        public static final int ID_NINE = 9; //btnDecision


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        btnQuestionContent2 = findViewById(R.id.btnQuestionContent2);
        btnQuestionExplanation2 = findViewById(R.id.btnQuestionExplanation2);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1){
            btnQuestionContent2.setTag("R.drawable.ic_tick");
            btnQuestionContent2.setBackgroundResource(R.drawable.ic_tick);
        }else if(requestCode == 2 && resultCode == 2){
            btnQuestionExplanation2.setTag("R.drawable.ic_tick");
            btnQuestionExplanation2.setBackgroundResource(R.drawable.ic_tick);
        }

    }

    protected void setNightMode(){
        mPrefs = this.getSharedPreferences("myPreference",0);

        if(mPrefs.getBoolean("NightMode", true) == true){

            ConstraintLayout container;
            LinearLayout createQuestionMainLayout, createQuestionLinearLayout1, questionTypeLinearLayout, questionTypeLinearLayout2, questionTypeLinearLayout3,termsLayout;
            ImageButton btnRevert, btnQuestionContent2, btnQuestionExplanation2;
            TextView txtViewMyQuestionHeader, txtMust, txtViewMstKnow1, txtViewMstKnow2, txtViewMstKnow3;
            Button btnCreate, btnQuestionCategory1, btnQuestionCategory2, btnQuestionContent1, btnQuestionExplanation1;

            container = findViewById(R.id.container);
            createQuestionMainLayout = findViewById(R.id.createQuestionMainLayout);
            createQuestionLinearLayout1 = findViewById(R.id.createQuestionLinearLayout1);
            questionTypeLinearLayout = findViewById(R.id.questionTypeLinearLayout);
            questionTypeLinearLayout2 = findViewById(R.id.questionTypeLinearLayout2);
            questionTypeLinearLayout3 = findViewById(R.id.questionTypeLinearLayout3);
            btnRevert = findViewById(R.id.btnRevert);
            txtViewMyQuestionHeader = findViewById(R.id.txtViewMyQuestionHeader);
            txtMust = findViewById(R.id.txtMust);
            txtViewMstKnow1 = findViewById(R.id.txtViewMstKnow1);
            txtViewMstKnow2 = findViewById(R.id.txtViewMstKnow2);
            txtViewMstKnow3 = findViewById(R.id.txtViewMstKnow3);
            btnCreate = findViewById(R.id.btnCreate);
            btnQuestionCategory1 = findViewById(R.id.btnQuestionCategory1);
            btnQuestionCategory2 = findViewById(R.id.btnQuestionCategory2);
            btnQuestionContent1 = findViewById(R.id.btnQuestionContent1);
            btnQuestionContent2 = findViewById(R.id.btnQuestionContent2);
            btnQuestionExplanation1 = findViewById(R.id.btnQuestionExplanation1);
            btnQuestionExplanation2 = findViewById(R.id.btnQuestionExplanation2);

            container.setBackgroundColor(Color.BLACK);
            createQuestionMainLayout.setBackgroundColor(Color.BLACK);
            createQuestionLinearLayout1.setBackgroundColor(Color.BLACK);
            questionTypeLinearLayout.setBackgroundColor(Color.BLACK);
            questionTypeLinearLayout2.setBackgroundColor(Color.BLACK);
            questionTypeLinearLayout3.setBackgroundColor(Color.BLACK);
            btnRevert.setBackgroundColor(Color.BLACK);
            txtViewMyQuestionHeader.setBackgroundColor(Color.BLACK);
            txtViewMyQuestionHeader.setTextColor(Color.WHITE);
            txtMust.setBackgroundColor(Color.BLACK);
            txtMust.setTextColor(Color.WHITE);
            txtViewMstKnow1.setBackgroundColor(Color.BLACK);
            txtViewMstKnow1.setTextColor(Color.WHITE);
            txtViewMstKnow2.setBackgroundColor(Color.BLACK);
            txtViewMstKnow2.setTextColor(Color.WHITE);
            txtViewMstKnow3.setBackgroundColor(Color.BLACK);
            txtViewMstKnow3.setTextColor(Color.WHITE);
            btnCreate.setBackgroundColor(Color.BLACK);
            btnCreate.setTextColor(Color.WHITE);
            btnQuestionCategory1.setBackgroundColor(Color.BLACK);
            btnQuestionCategory1.setTextColor(Color.WHITE);
            btnQuestionCategory2.setBackgroundColor(Color.BLACK);
            btnQuestionCategory2.setTextColor(Color.WHITE);
            btnQuestionContent1.setBackgroundColor(Color.BLACK);
            btnQuestionContent1.setTextColor(Color.WHITE);
            btnQuestionContent2.setBackgroundColor(Color.BLACK);
            btnQuestionExplanation1.setBackgroundColor(Color.BLACK);
            btnQuestionExplanation1.setTextColor(Color.WHITE);
            btnQuestionExplanation2.setBackgroundColor(Color.BLACK);
        }




    }

}
