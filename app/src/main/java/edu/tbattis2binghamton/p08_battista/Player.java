package edu.tbattis2binghamton.p08_battista;

/**
 * Created by thomas on 3/23/17.
 */

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by thomas on 3/14/17.
 */

public class Player{
    //Bitmap to get character from image
    private Bitmap bitmap;

    //coordinates
    private volatile int x;
    private int y;
    private int imageWidth;
    private int imageHeight;

    //motion speed of the character
    private double velocity = 0;
    private int VELOCITY_MULTIPLIER = 11;
    private Context con;

    int screenWidth;
    int screenHeight;

    public int direction;

    //constructor
    public Player(Context context)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        R
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_image, options);
        imageWidth = options.outWidth;
        imageHeight = options.outHeight;

        con = context;
        screenWidth =  Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight =  Resources.getSystem().getDisplayMetrics().heightPixels;
        x = screenWidth/2;
        y = screenHeight-screenHeight/6-10;
        velocity = 0;

        //Getting bitmap from drawable resource
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(context.getResources(),  R.drawable.player_image);
        bitmap = Bitmap.createScaledBitmap(unscaledBitmap, screenWidth/22, screenHeight/6, true);
        imageWidth = screenWidth/23;
        imageHeight= screenHeight/6;
    }

    //Method to update coordinate of character
    public void update()
    {
        //updating x coordinate

        x += velocity;
        if (x<0)
            x = screenWidth-imageWidth;
        else if (x > screenWidth-imageWidth)
            x = 0;


    }

    public void setMovement(int directionIn)
    {
        velocity = directionIn*(screenWidth/65);//VELOCITY_MULTIPLIER;
    }

    /*public void jump()
    {
        if (onPlatform)// || y >= (8*(height)/9))
        {
            velocity = -35;
            y += velocity;
            onPlatform = false;
        }
    }*/

    /*
    * These are getters you can generate it autmaticallyl
    * right click on editor -> generate -> getters
    * */
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRightX() { return x+imageWidth; }

    public int getBottom() { return y+imageHeight;}

    public double getSpeed() {
        return velocity;
    }

    // public void setAlpha(int i) { }
}