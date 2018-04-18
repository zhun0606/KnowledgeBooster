package com.assignment.knowledgebooster;

import android.content.Context;

import com.assignment.knowledgebooster.FirebaseClass.Questions;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ExcessFunction {
    /*
    String questionsFileName = "questions.json";
    public void saveQuestionFromLocalStorage(Questions questions) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(questionsFileName, Context.MODE_PRIVATE);
            outputStream.write(questions.toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Questions retrieveQuestionFromLocalStorage(){
        String JSONString = null;
        Questions questions;
        try {
            FileInputStream inputStream = openFileInput(questionsFileName);
            FileChannel fc = inputStream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            JSONString = Charset.defaultCharset().decode(bb).toString();

            Gson gson = new Gson();
            questions = gson.fromJson(JSONString, Questions.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return questions;
    }
    */
}
