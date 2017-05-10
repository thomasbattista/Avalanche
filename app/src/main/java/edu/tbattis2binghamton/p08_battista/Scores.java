package edu.tbattis2binghamton.p08_battista;

import android.content.Context;
import android.content.SharedPreferences;

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
        int oldHighScore = prefs.getInt("HighScore", 0);

        if (highScore > oldHighScore)
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("HighScore", highScore);
            editor.commit();
        }
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
