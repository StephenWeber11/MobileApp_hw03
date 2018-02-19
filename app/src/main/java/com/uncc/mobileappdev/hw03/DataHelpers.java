package com.uncc.mobileappdev.hw03;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Stephen Weber on 2/18/2018.
 */

public class DataHelpers {

    private static ArrayList<Question> questionDetails = new ArrayList<>();

    public static boolean isConnected(Activity activity){
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

    public static ArrayList<Question> formatQuestionDetailsString(String questionString){
        if(questionString != null && questionString != "") {
            String[] questionDetailsArray = questionString.split(";");

            /* Not sure what the best way to do this is... */
            for(int i=0; i < questionDetailsArray.length - 1; i++){
                if(i != 0){
                    Question question = new Question();

                    if(questionDetailsArray[i].contains("?")) {
                        question.setQuestion(questionDetailsArray[i]);
                    }
//                    else if(questionDetailsArray[i].contains("http")){
//                        question.setImageURL(questionDetailsArray[i]);
//                    }
//                    else if(questionDetailsArray[i-1].contains("http") || questionDetailsArray[i+1]){
//
//                    }
                    else if(questionDetailsArray[i].matches("(?<=\\s|^)\\d+(?=\\s|$)")){
                        question.setAnswerIndex(Integer.parseInt(questionDetailsArray[i]));
                    }
                    questionDetails.add(question);
                }
            }
            int index = 1;
            while(questionDetailsArray[index].matches("(?<=\\s|^)\\d+(?=\\s|$)")){

            }
        }
        logQuestions();
        return null;
    }

    private static void logQuestions(){
        for (Question question : questionDetails) {
            if(question.getQuestion() != null && question.getQuestion() != "") {
                Log.d("Questions", question.getQuestion());
                Log.d("AnswerIndex", question.getAnswerIndex()+"");
            }
        }
    }

}
