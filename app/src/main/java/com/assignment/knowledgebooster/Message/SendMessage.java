package com.assignment.knowledgebooster.Message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.assignment.knowledgebooster.R;

public class SendMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
    }

    protected void imgBtnMsgBack(View v){
        finish();
    }
}
