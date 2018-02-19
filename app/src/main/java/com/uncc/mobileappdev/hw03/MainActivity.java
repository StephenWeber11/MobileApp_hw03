package com.uncc.mobileappdev.hw03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ArrayList<Question> questionDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(DataHelpers.isConnected(MainActivity.this)){
            Log.d("Data","Fetching data");
            QuestionResource questionResource = (QuestionResource) new QuestionResource(new QuestionResource.AsyncResponse() {
                @Override
                public void processFinish(ArrayList<Question> questionDetails) {
                    MainActivity.this.questionDetails = questionDetails;
                    Log.d("Data", "The size of the list of questions is: " + questionDetails.size());

                    findViewById(R.id.button2).setEnabled(true);
                    findViewById(R.id.progress_bar_loading).setVisibility(View.INVISIBLE);
                }
            }).execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
        }




    }
}
