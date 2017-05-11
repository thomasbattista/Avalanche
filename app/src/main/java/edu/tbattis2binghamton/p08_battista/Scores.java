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

    private static Set<String> stringSet;


    public static void setUp()
    {
        stringSet = new HashSet<String>();
    }

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

        Collections.sort(highScoreList);

        if (highScoreList.size() < 10 || highScore > highScoreList.get(0))
        {
            if (highScoreList.size() == 10)
                highScoreList.remove(0);
            highScoreList.add(highScore);

        }

        stringSet.clear();
       // ArrayList<String> highScoreStringList = new  ArrayList<String>(10);
        for (Integer i : highScoreList)
        {
            try
            {
                stringSet.add( Integer.toString(i));// + "\t" + difficulty);
            }
            catch (IndexOutOfBoundsException e)
            {
                //throw new InputMismatchException();
                break;
            }
        }


        //stringSet.addAll(highScoreStringList);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("ScoreList", stringSet);
        editor.commit();



        //String[] HighScores = new String[10];
        /*if (highScore > oldHighScore)
        {
            SharedPreferences.Editor editor = prefs.edit();


            editor.putInt("HighScore", highScore);
            editor.putInt("Difficulty", difficulty);
            editor.commit();
        }*/
    }

    public static Set<String> getHighScores()
    {
        return stringSet;
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
