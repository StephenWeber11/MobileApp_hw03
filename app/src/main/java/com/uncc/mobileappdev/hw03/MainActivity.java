package com.uncc.mobileappdev.hw03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    private ArrayList<Question> questionDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(DataHelpers.isConnected(MainActivity.this)){
            Log.d("Data","Fetching data");
            QuestionResource questionResource = new QuestionResource(MainActivity.this);
            questionResource.delegate = this;
            questionResource.execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
        }

    }

    @Override
    public void processFinish(ArrayList<Question> output) {
        this.questionDetails = output;
        Log.d("Data", "Size of questionDetails in MainActivity: " + questionDetails.size());
    }
}
