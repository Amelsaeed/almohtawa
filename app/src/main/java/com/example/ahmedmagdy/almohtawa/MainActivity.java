package com.example.ahmedmagdy.almohtawa;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText questionEditText, answer1EditText, answer2EditText, answer3EditText, answer4EditText, answerCorrectEditText;
Button buttonAdd;
DatabaseReference databasequestion;

    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasequestion= FirebaseDatabase.getInstance().getReference("questions");

        questionEditText= (EditText)findViewById(R.id.question);
        answer1EditText= (EditText)findViewById(R.id.answer1);
        answer2EditText= (EditText)findViewById(R.id.answer2);
        answer3EditText= (EditText)findViewById(R.id.answer3);
        answer4EditText= (EditText)findViewById(R.id.answer4);
        answerCorrectEditText= (EditText)findViewById(R.id.answer_correct);


        buttonAdd= (Button) findViewById(R.id.button);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                addQuestion();
            }
        });
    }



    private void addQuestion(){
        String mQestion=questionEditText.getText().toString();
        String mAnswer1=answer1EditText.getText().toString();
        String  mAnswer2= answer2EditText.getText().toString();
        String  mAnswer3=answer3EditText.getText().toString();
        String  mAnswer4=answer4EditText.getText().toString();
        String mAnswerCorrect=answerCorrectEditText.getText().toString();
        if((!TextUtils.isEmpty(mQestion))&&(!TextUtils.isEmpty(mAnswer1))&&(!TextUtils.isEmpty(mAnswer2))&&(!TextUtils.isEmpty(mAnswer3))&&(!TextUtils.isEmpty(mAnswer4))&&(!TextUtils.isEmpty(mAnswerCorrect))) {

            String id =databasequestion.push().getKey();
            completeQuestion completequestion= new completeQuestion(id, mQestion, mAnswer1,mAnswer2,mAnswer3,mAnswer4,mAnswerCorrect);
            databasequestion.child(id).setValue(completequestion);
            Toast.makeText(this, "Question added", Toast.LENGTH_LONG).show();
           questionEditText.setText("");
            answer1EditText.setText("");
            answer2EditText.setText("");
            answer3EditText.setText("");
            answer4EditText.setText("");
            answerCorrectEditText.setText("");
            Intent intent2 = new Intent(MainActivity.this, Main2Activity.class);

            // Start the new activity
            startActivity(intent2);
        }else{ Toast.makeText(this, "you should fill all fields", Toast.LENGTH_LONG).show();}

    }
}
