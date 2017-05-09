package edu.tbattis2binghamton.p08_battista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.start_button);
        TextView textView = (TextView) findViewById(R.id.score_text_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        int myHighScore = prefs.getInt("HighScore", 0);

        textView.setText("Score: " + Scores.getScore() + "\nHigh score: " + myHighScore);
    }

    public void startGame(View v)
    {
        //TextView textView = (TextView) findViewById(R.id.score_text_view);
        //textView.setText("Testing\ntesting");
        Scores.incrementScore();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
