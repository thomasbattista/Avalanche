package edu.tbattis2binghamton.p08_battista;

<<<<<<< HEAD
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> f5f1232fe5b5aedc4e3623265e74f2d0c05ccc7b
/**
 * Created by thomas on 6/8/17.
 */

<<<<<<< HEAD
public class ScoreListAdapter extends ArrayAdapter<String> {

    private static final String TAG = "ScoreListAdapter";

    private Context context;
    private int resource;

    public ScoreListAdapter(Context contextIn, int resourceIn, ArrayList<String> objects) {
        super(contextIn, resourceIn, objects);
        context = contextIn;
        resource = resourceIn;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String s = getItem(position);
        String score, difficulty, date;

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        try
        {
            score = s.substring(0,s.indexOf('\t'));
            difficulty = s.substring(s.indexOf('\t')+1,s.lastIndexOf('\t'));
            date = s.substring(s.lastIndexOf('\t')+1);
        }
        catch (NullPointerException e)
        {
            score = "";
            difficulty = "";
            date = "";
        }

        TextView scoreTV = (TextView) convertView.findViewById(R.id.score_tv);
        TextView difficultyTV = (TextView) convertView.findViewById(R.id.difficulty_tv);
        TextView dateTV = (TextView) convertView.findViewById(R.id.date_tv);

        scoreTV.setText(score);
        difficultyTV.setText(difficulty);
        dateTV.setText(date);

        return convertView;
    }
=======
public class ScoreListAdapter {
>>>>>>> f5f1232fe5b5aedc4e3623265e74f2d0c05ccc7b
}
