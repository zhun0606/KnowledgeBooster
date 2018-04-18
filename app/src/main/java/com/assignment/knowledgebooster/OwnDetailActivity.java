package com.assignment.knowledgebooster;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.assignment.knowledgebooster.R;

import java.util.Calendar;

public class OwnDetailActivity extends AppCompatActivity {
     DatePickerDialog.OnDateSetListener chgDateListener;
    EditText inputNickName, inputSchool, inputOccupation, inputSignature, inputAboutMe;
    Button btnNickName, btnGender, btnIdentity, btnDoB, btnArea, btnSchool, btnOccupation, btnSignature, btnAboutMe;
    Button btnChgMale, btnChgFemale, btnChgOther;
    Button btnChgStudent, btnChgParent, btnChgTeacher;
    Button btnKL, btnMelacca, btnSelangor, btnJohor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_detail);


        btnNickName = (Button) findViewById((R.id.btnNickname));
        btnNickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNickname();
            }
        });

        btnGender = findViewById(R.id.btnGender);
        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGender();
            }
        });

        btnIdentity = findViewById(R.id.btnIdentity);
        btnIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIdentity();
            }
        });

        btnDoB = findViewById(R.id.btnDoB);
        btnDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDoB();
            }
        });

        btnArea = findViewById(R.id.btnArea);
        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chgArea();
            }
        });

        btnSchool = findViewById(R.id.btnSchool);
        btnSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chgSchool();
            }
        });

        btnOccupation = findViewById(R.id.btnOccupation);
        btnOccupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chgOccupation();
            }
        });

        btnSignature = findViewById(R.id.btnSignature);
        btnSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chgSignature();
            }
        });

        btnAboutMe = findViewById(R.id.btnAboutMe);
        btnAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chgAboutMe();
            }
        });




    }
    protected void changeNickname(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Nickname");

        inputNickName = new EditText(this);
        builder.setView(inputNickName);


        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnNickName = findViewById(R.id.btnNickname);
                btnNickName.setText(inputNickName.getText());
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

    protected void changeGender(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        btnChgFemale = new Button(this);
        btnChgMale = new Button(this);
        btnChgOther  = new Button(this);
        btnChgFemale.setText("Female");
        btnChgMale.setText("Male");
        btnChgOther.setText("Other");
        btnChgFemale.setBackgroundColor(Color.TRANSPARENT);
        btnChgMale.setBackgroundColor(Color.TRANSPARENT);
        btnChgOther.setBackgroundColor(Color.TRANSPARENT);

        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(parms);

        LinearLayout.LayoutParams chgGenderParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(btnChgMale, chgGenderParams);
        layout.addView(btnChgFemale, chgGenderParams);
        layout.addView(btnChgOther, chgGenderParams);


       builder.setView(layout);

        final AlertDialog alertDialog = builder.create();

        btnChgFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGender.setText("Female");
                DialogInterface dialog;
                alertDialog.dismiss();
            }
        });

        btnChgMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGender.setText("Male");
                alertDialog.dismiss();
            }
        });

        btnChgOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGender.setText("Other");
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    protected void changeIdentity(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        btnChgStudent = new Button(this);
        btnChgParent = new Button(this);
        btnChgTeacher = new Button(this);
        btnChgOther  = new Button(this);
        btnChgStudent.setText("Student");
        btnChgParent.setText("Parent");
        btnChgTeacher.setText("Teacher");
        btnChgOther.setText("Other");
        btnChgStudent.setBackgroundColor(Color.TRANSPARENT);
        btnChgParent.setBackgroundColor(Color.TRANSPARENT);
        btnChgTeacher.setBackgroundColor(Color.TRANSPARENT);
        btnChgOther.setBackgroundColor(Color.TRANSPARENT);

        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(parms);

        LinearLayout.LayoutParams chgGenderParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(btnChgStudent, chgGenderParams);
        layout.addView(btnChgParent, chgGenderParams);
        layout.addView(btnChgTeacher, chgGenderParams);
        layout.addView(btnChgOther, chgGenderParams);


        builder.setView(layout);

        final AlertDialog alertDialog = builder.create();

        btnChgStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIdentity.setText("Student");
                alertDialog.dismiss();
            }
        });

        btnChgParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIdentity.setText("Parent");
                alertDialog.dismiss();
            }
        });

        btnChgTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIdentity.setText("Teacher");
                alertDialog.dismiss();
            }
        });


        btnChgOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIdentity.setText("Other");
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    protected void changeDoB() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_MinWidth, chgDateListener, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();

        chgDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btnDoB = findViewById(R.id.btnDoB);
                month = month+1;
                btnDoB.setText(dayOfMonth + "-" + month + "-" + year);
            }
        };
    }

    protected void chgArea(){

//        ScrollView scrollView = new ScrollView(this);
//        ScrollView.LayoutParams scrollViewParms = new ScrollView.LayoutParams(400,500);
//        scrollView.setLayoutParams(scrollViewParms);

       // scrollView.addView(layout);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        btnKL = new Button(this);
        btnMelacca = new Button(this);
        btnSelangor  = new Button(this);
        btnJohor  = new Button(this);
        btnKL.setText("Kuala Lumpur");
        btnMelacca.setText("Melacca");
        btnSelangor.setText("Selangor");
        btnJohor.setText("Johor");
        btnKL.setBackgroundColor(Color.TRANSPARENT);
        btnMelacca.setBackgroundColor(Color.TRANSPARENT);
        btnSelangor.setBackgroundColor(Color.TRANSPARENT);
        btnJohor.setBackgroundColor(Color.TRANSPARENT);

        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(parms);

        LinearLayout.LayoutParams chgAreaParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(btnKL, chgAreaParams);
        layout.addView(btnMelacca, chgAreaParams);
        layout.addView(btnSelangor, chgAreaParams);
        layout.addView(btnJohor, chgAreaParams);


        builder.setView(layout);

        final AlertDialog alertDialog = builder.create();

        btnKL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnArea.setText("Kuala Lumpur");
                alertDialog.dismiss();
            }
        });

        btnMelacca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnArea.setText("Melacca");
                alertDialog.dismiss();
            }
        });

        btnSelangor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnArea.setText("Selangor");
                alertDialog.dismiss();
            }
        });

        btnJohor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnArea.setText("Johor");
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    protected void chgSchool(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("School");

        inputSchool = new EditText(this);
        builder.setView(inputSchool);


        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnSchool = findViewById(R.id.btnSchool);
                btnSchool.setText(inputSchool.getText());
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

    protected void chgOccupation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Occupation");

        inputOccupation = new EditText(this);
        builder.setView(inputOccupation);


        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnOccupation = findViewById(R.id.btnOccupation);
                btnOccupation.setText(inputOccupation.getText());
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

    protected  void chgSignature(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Signature");

        inputSignature = new EditText(this);
        inputSignature.setSingleLine(false);
        inputSignature.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        inputSignature.setLines(2);
        inputSignature.setMaxLines(6);
        builder.setView(inputSignature);



        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnSignature = findViewById(R.id.btnSignature);
                btnSignature.setText(inputSignature.getText());
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

    protected void chgAboutMe(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("About Me");

        inputAboutMe = new EditText(this);
        inputAboutMe.setSingleLine(false);
        inputAboutMe.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        inputAboutMe.setLines(2);
        inputAboutMe.setMaxLines(6);
        builder.setView(inputAboutMe);



        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnAboutMe = findViewById(R.id.btnAboutMe);
                btnAboutMe.setText(inputAboutMe.getText());
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
}



