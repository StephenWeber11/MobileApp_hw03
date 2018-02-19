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

            /* Not sure what the best way to do this is... But this works!!!*/
            for(int i=0; i < questionDetailsArray.length - 1; i++){
                if(questionDetailsArray[i].matches("(?<=\\s|^)\\d+(?=\\s|$)")){
                    Question question = new Question();

                    for(int j=i+1; j < questionDetailsArray.length -1; j++){
                        if(question.getAnswerIndex() != 9999){
                            questionDetails.add(question);
                            break;
                        }
                        if(questionDetailsArray[j].contains("?")) {
                            question.setQuestion(questionDetailsArray[j]);
                            question.setImageURL(questionDetailsArray[j+1]);

                            int index = j+2;
                            ArrayList<String> possibleAnswers = new ArrayList<>();
                            while(!questionDetailsArray[index].matches("(?<=\\s|^)\\d+(?=\\s|$)")){
                                possibleAnswers.add(questionDetailsArray[index]);
                                index++;
                            }
                            question.setAnswers(possibleAnswers);

                        }
                        if(questionDetailsArray[j].matches("(?<=\\s|^)\\d+(?=\\s|$)")){
                            question.setAnswerIndex(Integer.parseInt(questionDetailsArray[j]));
                        }

                    }

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
                Log.d("ImageURL", question.getImageURL());
                for(String str : question.getAnswers()){
                    Log.d("Answer: ", str);
                }
                Log.d("AnswerIndex", question.getAnswerIndex()+"");
            }
        }
    }

}
