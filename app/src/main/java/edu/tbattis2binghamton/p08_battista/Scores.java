package edu.tbattis2binghamton.p08_battista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

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

        int oldHS = prefs.getInt("HighScore",Context.MODE_PRIVATE);

        if (score > oldHS)
        {
            editor.putInt("HighScore",score);
            editor.putInt("HighScoreDifficulty",difficulty);
        }

        String highScoreList = prefs.getString("ScoreList", null);
        if (highScoreList == null)
            highScoreList = "";
        highScoreList = HighScoreConverter.addScore(highScoreList);

        editor.putString("ScoreList", highScoreList);
        editor.commit();

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
