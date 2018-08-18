package com.example.ahmedmagdy.almohtawa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by AHMED MAGDY on 7/19/2018.
 */

public class QuestionList extends ArrayAdapter<completeQuestion> {
    private Activity context;
    private List<completeQuestion> questionList;
    private String mAnswer;
    private String a1,a2,a3,a4;
    public int mScore = 0;  // current total score
    public int ii = 0;
    int asize;
    completeQuestion  completequestion1;

    public QuestionList(Activity context, List<completeQuestion> questionList) {
        super(context, R.layout.list_layout, questionList);
        this.context = context;
        this.questionList = questionList;
    }



    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        final TextView textview = (TextView) listViewItem.findViewById(R.id.textView);
        final Button radioanswer1 = (Button) listViewItem.findViewById(R.id.radio_answer1);
        final Button radioanswer2 = (Button) listViewItem.findViewById(R.id.radio_answer2);
        final Button radioanswer3 = (Button) listViewItem.findViewById(R.id.radio_answer3);
        final Button radioanswer4 = (Button) listViewItem.findViewById(R.id.radio_answer4);


         completequestion1 = questionList.get(position);
         asize = questionList.size();



        textview.setText(completequestion1.getQuestionI());
        a1=completequestion1.getQuestionAnswer1();
        a2=completequestion1.getQuestionAnswer2();
        a3=completequestion1.getQuestionAnswer3();
        a4=completequestion1.getQuestionAnswer4();
        radioanswer1.setText(a1);
        radioanswer2.setText(a2);
        radioanswer3.setText(a3);
        radioanswer4.setText(a4);
        mAnswer = completequestion1.getQuestionCorrectAnswer();

        radioanswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completequestion1 = questionList.get(position);
                mAnswer = completequestion1.getQuestionCorrectAnswer();
                a1=completequestion1.getQuestionAnswer1();
                ii++;

                if (mAnswer.equalsIgnoreCase(a1)){
                    mScore = mScore + 1;
                    textview.setText("correct");
                    textview.setTextColor(Color.parseColor("#228b22"));
                    Log.v("QuestionList", " score: " +  mScore);
                }else {
                    textview.setText("wrong");
                    textview.setTextColor(Color.parseColor("#ff0000"));
                    Log.v("QuestionList", " c.answer: " + mAnswer);
                    Log.v("QuestionList", " a1: " + a1);
                    Log.v("QuestionList", " ii: " +  position);
                    //  Log.v("QuestionList", " score: " +  mScore);
                }
               // radioanswer1.setTextColor(Color.parseColor("#ff0000"));
               // questionList.remove(position);
               // textview.setVisibility(View.GONE);

                radioanswer1.setVisibility(View.GONE);
                radioanswer2.setVisibility(View.GONE);
                radioanswer3.setVisibility(View.GONE);
                radioanswer4.setVisibility(View.GONE);
            }
        });
        radioanswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completequestion1 = questionList.get(position);
                mAnswer = completequestion1.getQuestionCorrectAnswer();
                a2=completequestion1.getQuestionAnswer2();
                // Code here executes on main thread after user presses button
                ii++;
                if (mAnswer.equalsIgnoreCase( a2)) {
                    mScore = mScore + 1;
                    textview.setText("correct");
                    textview.setTextColor(Color.parseColor("#228b22"));
                    Log.v("QuestionList", " score: " +  mScore);
                }else {
                    textview.setText("wrong");
                    textview.setTextColor(Color.parseColor("#ff0000"));
                    Log.v("QuestionList", " c.answer: " + mAnswer);
                    Log.v("QuestionList", " a2: " + a2);
                    Log.v("QuestionList", " ii: " +  position);
                    // Log.v("QuestionList", " score: " +  mScore);
                }
                //radioanswer2.setTextColor(Color.parseColor("#ff0000"));
               // textview.setVisibility(View.GONE);
                radioanswer1.setVisibility(View.GONE);
                radioanswer2.setVisibility(View.GONE);
                radioanswer3.setVisibility(View.GONE);
                radioanswer4.setVisibility(View.GONE);
            }
        });
        radioanswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completequestion1 = questionList.get(position);
                mAnswer = completequestion1.getQuestionCorrectAnswer();
                a3=completequestion1.getQuestionAnswer3();
                ii++;
                // Code here executes on main thread after user presses button
                if (mAnswer.equalsIgnoreCase(a3)){
                    mScore = mScore + 1;
                    textview.setText("correct");
                    textview.setTextColor(Color.parseColor("#228b22"));
                    Log.v("QuestionList", " score: " +  mScore);
                }else {
                    textview.setText("wrong");
                    textview.setTextColor(Color.parseColor("#ff0000"));
                    Log.v("QuestionList", " c.answer: " + mAnswer);
                    Log.v("QuestionList", " a3: " + a3);
                    Log.v("QuestionList", " ii: " +  position);
                }
               // radioanswer3.setTextColor(Color.parseColor("#ff0000"));
               // textview.setVisibility(View.GONE);
                radioanswer1.setVisibility(View.GONE);
                radioanswer2.setVisibility(View.GONE);
                radioanswer3.setVisibility(View.GONE);
                radioanswer4.setVisibility(View.GONE);
            }
        });
        radioanswer4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                completequestion1 = questionList.get(position);
                mAnswer = completequestion1.getQuestionCorrectAnswer();
                a4=completequestion1.getQuestionAnswer4();
                ii++;
                // Code here executes on main thread after user presses button
                if (mAnswer.equalsIgnoreCase(a4)){
                    mScore = mScore + 1;
                    textview.setText("correct");
                    textview.setTextColor(Color.parseColor("#228b22"));
                    Log.v("QuestionList", " score: " +  mScore);
                }else {
                    textview.setText("wrong");
                    textview.setTextColor(Color.parseColor("#ff0000"));
                    Log.v("QuestionList", " c.answer: " + mAnswer);
                    Log.v("QuestionList", " a4: " + a4);
                    Log.v("QuestionList", " ii: " +  position);


                }
               // radioanswer4.setTextColor(Color.parseColor("#ff0000"));
               // textview.setVisibility(View.GONE);
                radioanswer1.setVisibility(View.GONE);
                radioanswer2.setVisibility(View.GONE);
                radioanswer3.setVisibility(View.GONE);
                radioanswer4.setVisibility(View.GONE);
            }
        });
        if (ii==asize) {
           Intent mIntent = new Intent(context, ScoreActivity.class);

            mIntent.putExtra("sscore", mScore);
            mIntent.putExtra("total", asize);

            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
            context.finish();

         /**  Intent mIntent = new Intent("custom-message");
            mIntent.putExtra("sscore", mScore);
            mIntent.putExtra("total", asize);
           LocalBroadcastManager.getInstance(context).sendBroadcast(mIntent);
          //context.startActivity(mIntent);**/

        }
        return listViewItem;
    }

}