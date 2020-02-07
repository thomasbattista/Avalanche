package edu.tbattis2binghamton.p08_battista;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeMap;

public class HighScoreConverter {
    private static final int NUM_SCORES = 25;

    /*public static String addScore(String oldScores)
    {
        ArrayList<Integer> valueList = new ArrayList<Integer>(NUM_SCORES + 1);
        BufferedReader bufReader = new BufferedReader(new StringReader(oldScores));
        String line = null;
        ArrayList<String> stringArrayList = toArrayList(oldScores);
        int i;
        int currScore = Scores.getScore();
        try
        {
            while ((line = bufReader.readLine()) != null) // maybe add to String ArrayList first then convert back
            {
                try {
                    valueList.add(Integer.parseInt(line.substring(0, line.indexOf('\t'))));
                } catch (StringIndexOutOfBoundsException e) {
                    Log.d("EXCEPTION", "bufReader failed - StringOutOf Bound Exception:" + line);
                }
            }
        } catch (IOException e) {
            Log.d("EXCEPTION", "bufReader failed - IOException");
            //throw new IOException("Thrown in add Score");
            //return oldScores;
        }

        if (valueList.size() < NUM_SCORES || currScore > valueList.get(valueList.size() - 1)) {
            for (i = 0; i < valueList.size(); i++) {
                if (currScore > valueList.get(i))
                    break;
            }


            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());

            String temp = Integer.toString(currScore) + "\t" + Integer.toString(Scores.getDifficulty()) + "\t" + timeStamp;
            stringArrayList.add(i, temp);

            while (stringArrayList.size() > NUM_SCORES)
                stringArrayList.remove(stringArrayList.size() - 1);

            String newScores = "";

            for (String s : stringArrayList) {
                if (Character.isDigit(s.charAt(0)))
                    newScores += s + "\n";
            }

            //newScores = newScores.substring(0,newScores.lastIndexOf('\n'));
            return newScores;

            //add the current values at line i
            //remove last line if == valueList.size()==NUM_SCORES
            //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        } else {
            return oldScores;
        }

    }*/

    public static HashSet<String> addScore(HashSet<String> hashSet)
    {
        Log.d("addScore", "in add score");
        SortedMap<Integer, String> map = new TreeMap<Integer, String>();
        int score;
        int lowestScore = Integer.MAX_VALUE;
        //String lowestScoreValue = null;
        for (String s : hashSet)
        {
            score = Integer.parseInt(s.substring(0, s.indexOf('\t')));
            map.put(score, s);
            Log.d("HiScoreConvrtr.addScore", s);
            if (score < lowestScore)
            {
                lowestScore = score;
                //lowestScoreValue = s;
            }
        }

        int currScore = Scores.getScore();

        Log.d("HiScoreConvrtr.addScore", lowestScore + " : " + Integer.MAX_VALUE);
        if (currScore > lowestScore || lowestScore == Integer.MAX_VALUE || hashSet.size() < NUM_SCORES )
        {
            if (lowestScore != Integer.MAX_VALUE && hashSet.size() >= NUM_SCORES)
                map.remove(lowestScore);
            // If >= 24...
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
            map.put(currScore, Integer.toString(currScore) + "\t" + Integer.toString(Scores.getDifficulty()) + "\t" + timeStamp);
            Log.d("HiScoreConvrtr.addScore", "put new score");
        }

        return new HashSet<String>(map.values());
    }

    public static ArrayList<String> toArrayList(HashSet<String> hashSet)
    {
        SortedMap<Integer, String> map = new TreeMap<Integer, String>();
        int score = 0;
        for (String s : hashSet)
        {
            score = Integer.parseInt(s.substring(0, s.indexOf('\t')));
            map.put(score, s);
            Log.d("TAG", s);
        }

        ArrayList<String> arrayList = new ArrayList<String>(map.values());
        Collections.reverse(arrayList);
        return arrayList;
    }

}