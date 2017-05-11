package edu.tbattis2binghamton.p08_battista;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class HighScoreActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);


            Scores.updateHighScore(this);


        mListView = (ListView) findViewById(R.id.list_view_scores);

        updateScores();


    }

    @Override
    protected void onResume()
    {
        super.onResume();

        updateScores();
    }

    private void updateScores()
    {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);

        Set<String> set = prefs.getStringSet("ScoreList", null);
        ArrayList<Integer> highScoreList = new  ArrayList<Integer>(10);
        for (String s : set)
        {
            try
            {
                highScoreList.add(Integer.parseInt(s.substring(0,s.indexOf('\t'))));
            }
            catch (StringIndexOutOfBoundsException e)
            {
                highScoreList.add(Integer.parseInt(s));
            }

        }

        Collections.sort(highScoreList, Collections.<Integer>reverseOrder());

        ArrayList<String> scoresList = new ArrayList<String>(10);

        for (Integer i : highScoreList)
        {
            scoresList.add(Integer.toString(i));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, scoresList);
        mListView.setAdapter(adapter);
    }

}
