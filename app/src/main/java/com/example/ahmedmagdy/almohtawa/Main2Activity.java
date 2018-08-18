package com.example.ahmedmagdy.almohtawa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    DatabaseReference databasequestion;
    ListView listViewQuestion;
    List<completeQuestion> questionList;
    private FirebaseAuth mAuth;
    //private String yh9k4J7GnRYuSDInm0fNNiSxAB13;
   // private final String  teacher = "yh9k4J7GnRYuSDInm0fNNiSxAB13";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button numbers = (Button) findViewById(R.id.go_to_activity);
        /**   **/
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            //String subject = user.getDisplayName();
            Log.v("Main2Activity", " uid " + uid);
           String teacher= "yh9k4J7GnRYuSDInm0fNNiSxAB13";
           /** !temp.equalsIgnoreCase("A")
            if (mAnswer.equalsIgnoreCase( a2))**/
            if (!uid .equals (teacher)){numbers.setVisibility(View.GONE);}

        }

        databasequestion= FirebaseDatabase.getInstance().getReference("questions");

        listViewQuestion= (ListView)findViewById(R.id.list_view_question);
        questionList=new ArrayList<>();



        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent intent1 = new Intent(Main2Activity.this, MainActivity.class);

                // Start the new activity
                startActivity(intent1);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        databasequestion.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questionList.clear();
                for(DataSnapshot questionSnapshot: dataSnapshot.getChildren()){
                    completeQuestion completequestion1=questionSnapshot.getValue(completeQuestion.class);
                    questionList.add(completequestion1);
                    QuestionList adapter=new QuestionList(Main2Activity.this,questionList);
                    listViewQuestion.setAdapter(adapter);
/**
                    listViewQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
String selecteditem=parent.getItemAtPosition(position).toString();
                            Toast.makeText(Main2Activity.this,selecteditem,Toast.LENGTH_LONG).show();
                        }
                    });**/
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
