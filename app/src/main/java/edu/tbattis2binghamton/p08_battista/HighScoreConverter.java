package edu.tbattis2binghamton.p08_battista;

<<<<<<< HEAD
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
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

=======
>>>>>>> f5f1232fe5b5aedc4e3623265e74f2d0c05ccc7b
/**
 * Created by thomas on 5/25/17.
 */

<<<<<<< HEAD


public class HighScoreConverter
{
    private static final int NUM_SCORES = 25;

    public static String addScore(String oldScores)
    {
        ArrayList<Integer> valueList = new ArrayList<Integer>(NUM_SCORES+1);
        BufferedReader bufReader = new BufferedReader(new StringReader(oldScores));
        String line = null;
        ArrayList<String> stringArrayList = toArrayList(oldScores);
        int i;
        int currScore = Scores.getScore();
        try
        {
            while ((line=bufReader.readLine()) != null) // maybe add to String ArrayList first then convert back
            {
                try
                {
                    valueList.add(Integer.parseInt(line.substring(0,line.indexOf('\t'))));
                }
                catch (StringIndexOutOfBoundsException e)
                {
                    Log.d("EXCEPTION", "bufReader failed - StringOutOf Bound Exception:" + line);
                }
            }
        }
        catch (IOException e)
        {
            Log.d("EXCEPTION", "bufReader failed - IOException");
            //throw new IOException("Thrown in add Score");
            //return oldScores;
        }

        if (valueList.size() < NUM_SCORES ||  currScore > valueList.get(valueList.size()-1))
        {
            for (i=0; i<valueList.size(); i++ )
            {
                if (currScore > valueList.get(i))
                    break;
            }




            String timeStamp = new SimpleDateFormat("MM/dd/yy hh:mm a").format(Calendar.getInstance().getTime());

            String temp = Integer.toString(currScore) + "\t" + Integer.toString(Scores.getDifficulty()) + "\t" +timeStamp;
            stringArrayList.add(i,temp);

            while (stringArrayList.size() > NUM_SCORES)
                stringArrayList.remove(stringArrayList.size()-1);

            String newScores="";

            for (String s : stringArrayList)
            {
                if (Character.isDigit(s.charAt(0)))
                    newScores += s + "\n";
            }

            //newScores = newScores.substring(0,newScores.lastIndexOf('\n'));
            return newScores;

            //add the current values at line i
            //remove last line if == valueList.size()==NUM_SCORES
            //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        }
        else
        {
            return oldScores;
        }

    }

    public static ArrayList<String> toArrayList(String string)
    {
        BufferedReader bufReader = new BufferedReader(new StringReader(string));
        String line=null;
        ArrayList<String> stringArrayList = new ArrayList<String>(NUM_SCORES+1);

        if (!( string.contains("\n")))
            return stringArrayList;
        try
        {
            while ((line=bufReader.readLine()) != null)
            {

                stringArrayList.add(line);
            }
        }
        catch (IOException e)
        {
            //throw new IOException("Thrown in to ArrayList");
        }

        String temp = stringArrayList.get(stringArrayList.size()-1);
        if (!Character.isDigit(temp.charAt(0)))
            stringArrayList.remove(stringArrayList.size()-1);

        return stringArrayList;
    }
=======
public class HighScoreConverter {
>>>>>>> f5f1232fe5b5aedc4e3623265e74f2d0c05ccc7b
}
