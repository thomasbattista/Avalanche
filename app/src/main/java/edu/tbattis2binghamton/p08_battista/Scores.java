package edu.tbattis2binghamton.p08_battista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.Set;

/**
 * Created by thomas on 3/21/17.
 */

public class Scores
{
    private static int score=0;

    private static int highScore=0;
    private static int difficulty=15;



    public static void setDifficulty(int difficultyIn)
    {
       difficulty = difficultyIn;
    }

    public static int getDifficulty()
    {
        return difficulty;
    }

    public static void incrementScore()
    {
        score++;
        if (score > highScore)
        {
            highScore++;
        }
    }

    public static void updateHighScore(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        int oldHS = prefs.getInt("HighScore", Context.MODE_PRIVATE);

        if (score > oldHS)
        {
            editor.putInt("HighScore",score);
            editor.putInt("HighScoreDifficulty",difficulty);
        }

        Set<String> highScoreSet = null;
        String highScoreList = "";
        try
        {
            highScoreSet = prefs.getStringSet("ScoreListSet", null);
        }
        catch (Exception e)
        { }

        HashSet<String> hashSet = new HashSet<String>();

        if (highScoreSet == null)
        {
            //highScoreList = "";
            highScoreSet = new HashSet<String>();
        }
        for (String str : highScoreSet)
        {
            Log.d("S.uHS highScoreSet", str);
            //highScoreList += str + "\n";
            hashSet.add(str);
        }

        hashSet = HighScoreConverter.addScore(hashSet);

        for (String str : hashSet )
        {
            Log.d("Scores.updateHighScore", str);
        }

        editor.putStringSet("ScoreListSet", hashSet);
        editor.apply();

    }

    public static void resetScore()
    {
        score =0;
    }

    public static int getScore()
    {
        return score;
    }

    public static int getHighScore()
    {
        return highScore;
    }
}
