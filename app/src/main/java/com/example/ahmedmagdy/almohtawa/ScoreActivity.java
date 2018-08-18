package com.example.ahmedmagdy.almohtawa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ScoreActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView username;
    TextView email1;
   /** int mScore=0;
    int asize=0;**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
username= (TextView) findViewById(R.id.email_name);
        email1= (TextView) findViewById(R.id.email_email);
        mAuth = FirebaseAuth.getInstance();


           Intent intent = getIntent();
        //Intent intent = getIntent();
       // intent.getSerializableExtra(int mScore,int asize);

        int mScore = intent.getIntExtra("sscore", 0);
            int asize = intent.getIntExtra("total", 0);
       /** LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));**/

            TextView score_text = (TextView) findViewById(R.id.score);
            score_text.setText(" " +mScore+" ");
        TextView total_text = (TextView) findViewById(R.id.total_value);
        total_text.setText("Total= " + asize);
           // Log.v("ScoreActivity", " sscore: " + mScore);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            String subject = user.getDisplayName();
          //  String email = "a_magdy20002001@yahoo.com";
            String email = user.getEmail();
            String message= mScore+" / "+asize;
            username.setText(subject);
            email1.setText(email);
            SendMail sm = new SendMail(this, email, subject, message);

            //Executing sendmail to send email
            sm.execute();
        }
        }
  /**  public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            int mScore = intent.getIntExtra("sscore", 0);
            int asize = intent.getIntExtra("total", 0);
            Log.v("ScoreActivity", " mScore: " + mScore);
           // String ItemName = intent.getStringExtra("item");
           // String qty = intent.getStringExtra("quantity");
            Toast.makeText(ScoreActivity.this,mScore +" "+asize ,Toast.LENGTH_SHORT).show();
        }
    };**/

    }
