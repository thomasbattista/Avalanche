package edu.tbattis2binghamton.p08_battista;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.p2p.WifiP2pManager;

import java.util.Date;
import java.util.Random;

import android.os.CountDownTimer;


/**
 * Created by thomas on 3/24/17.
 */

public class Icicle {

    private Bitmap icicleBitmap;
    private Bitmap stalactitesBitmap;
    private Bitmap stalactitesBiggerBitmap;

    //coordinates
    private int x;
    private volatile double y;
    private int imageWidth;
    private int imageHeight;

    //motion speed of the character
    private double velocity = 0;
    private final int VELOCITY_MULTIPLIER = 1;
    private Context con;

    int screenWidth;
    int screenHeight;

    public volatile int falling;

    CountDownTimer startTimer;
    CountDownTimer timer;
    CountDownTimer increaseSpeedTimer;


    Random r;


    //constructor
    public Icicle(Context context, int xIn, double speedIn)
    {
        falling = -2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        icicleBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icicle_image2, options);
        //set width to 23rd of screen
        // inbetween 38th
        imageWidth = options.outWidth;
        imageHeight = options.outHeight;

        con = context;
        screenWidth =  Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight =  Resources.getSystem().getDisplayMetrics().heightPixels;
        x = screenWidth/2;
        y = -110;
        //velocity = 0;

        //Getting bitmap from drawable resource
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(context.getResources(),  R.drawable.icicle_image2);
        icicleBitmap = Bitmap.createScaledBitmap(unscaledBitmap, screenWidth/22, screenHeight/6, true);

        unscaledBitmap = BitmapFactory.decodeResource(context.getResources(),  R.drawable.stalactite_image);
        stalactitesBitmap = Bitmap.createScaledBitmap(unscaledBitmap, screenWidth/22, screenHeight/6, true);

        /*unscaledBitmap = BitmapFactory.decodeResource(context.getResources(),  R.drawable.stalactite_bigger_image);
        stalactitesBiggerBitmap = Bitmap.createScaledBitmap(unscaledBitmap, screenWidth/22, screenHeight/6, true);*/

        Random r = new Random();
        //setMovement(speedIn);
        //if (speedIn <0)
          //  velocity = -1;
        //else
        velocity = (speedIn);
        x = xIn;

        imageWidth = screenWidth/23;
        imageHeight= screenHeight/6;
        setUpTimer();
    }

    //Method to update coordinate of character
    public void update()
    {
        if (falling == 1)
        {

            if (y+imageHeight > screenHeight)
            {

                falling = -1;
                y = -110;
                timer.cancel();
                timer.start();

                /* = new CountDownTimer((r.nextInt() % 3500) + 500,50) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        falling = -1;
                        if (millisUntilFinished < 1000)
                            falling = 0;
                    }

                    @Override
                    public void onFinish() {
                        falling = 1;
                    }
                }.start();*/
            }
            else
                y += velocity;
        }
        /*else if (falling == 0)
        {
            y = -1;
        }
        else
        {
            y = -100;

        }*/

    }

    /* FIX WEIRD GLITCH RIGHT AFTER IT FALLS*/

    /*public void setMovement(int directionIn)
    {

    }*/

    public void setUpTimer()
    {
        r = new Random();

        timer = new CountDownTimer((r.nextInt(100)) + 600,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 500)
                {
                    y = screenHeight/(-6.5);
                    falling = -1;
                }
                else //(millisUntilFinished < 1000)
                {
                    y = screenHeight/(-8);
                    falling = 0;

                }

            }

            @Override
            public void onFinish()
            {
                y = 0;
                falling = 1;

                //velocity = velocity+(Scores.getScore()/5000); // if ewual certiain #, increase
            }
        };


        startTimer = new CountDownTimer((r.nextInt(1500)) + 50,100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish()
            {
               falling = -1;
                timer.start();
                //startTimer.;
                //velocity = velocity+(Scores.getScore()/5000); // if ewual certiain #, increase
            }
        }.start();

        increaseSpeedTimer = new CountDownTimer(60000,30000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish()
            {
                velocity++;
                increaseSpeedTimer.start();
            }
        }.start();

    }

    /*
    * These are getters you can generate it autmaticallyl
    * right click on editor -> generate -> getters
    * */
    public Bitmap getBitmap()
    {
        if (falling ==1)
            return icicleBitmap;
        else //if (falling <= 0)
            return stalactitesBitmap;
        //return null;
    }

    public int getX() {
        return x;
    }

    public int getY()
    {
        return (int)y;
    }

    public int getRightX() { return x+imageWidth; }

    public int getBottom() { return (int)y+imageHeight;}

    public double getSpeed() {
        return velocity;
    }
}
