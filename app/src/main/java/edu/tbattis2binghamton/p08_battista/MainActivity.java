package edu.tbattis2binghamton.p08_battista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private int difficulty=15;
    TextView textView;
    TextView difficultyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button);
        final Button hsButton = (Button) findViewById(R.id.high_score_button);
        textView = (TextView) findViewById(R.id.score_text_view);
        difficultyTextView = (TextView) findViewById(R.id.difficulty_text_num);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        seekBar = (SeekBar) findViewById(R.id.level_bar);
       // seekBar.setOnSeekBarChangeListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                difficulty = progress;
                difficultyTextView.setText(progress+"");
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
            //Toast.makeText(getApplicationContext(), "difficulty: " +  difficulty, Toast.LENGTH_SHORT).show();
        });

        updateTextView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        updateTextView();
    }

    private void updateDifficulty()
    {

    }

    private void updateTextView()
    {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        int myHighScore = prefs.getInt("HighScore", 0);
        int myDifficulty = prefs.getInt("HighScoreDifficulty", 0);

        textView.setText("Score: " + Scores.getScore() + " Difficulty: " + Scores.getDifficulty() + "\nHigh score: " + myHighScore + " Difficulty: " + myDifficulty);
    }

    public void startGame(View v)
    {
        //TextView textView = (TextView) findViewById(R.id.score_text_view);
        //textView.setText("Testing\ntesting");
        Scores.incrementScore();
        Scores.setDifficulty(difficulty);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void goToHighScores(View v)
    {
        //TextView textView = (TextView) findViewById(R.id.score_text_view);
        //textView.setText("Testing\ntesting");

        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }
}
