package com.uncc.mobileappdev.hw03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(DataHelpers.isConnected(MainActivity.this)){
            Log.d("Demo","Fetching data");
            new QuestionResource(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
        }

    }
}
