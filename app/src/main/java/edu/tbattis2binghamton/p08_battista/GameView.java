package edu.tbattis2binghamton.p08_battista;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;



/**
 * Created by thomas on 3/23/17.
 */


public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;

    //adding the player to this class
    private Player player;
    //private Icicle icicle;
    private ArrayList<Icicle> icicles;

    //These objects will be used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Context con;


    public GameView(Context context) {
        super(context);

        con = context;
        int screenWidth =  Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight =  Resources.getSystem().getDisplayMetrics().heightPixels;

        //initializing player object
        //Random rand = new Random();
        //int n = rand.nextInt(50) + 1;
        player = new Player(context);
        Random r = new Random();
        icicles = new ArrayList<Icicle>(15);
        double prevSpeed=0;
        double currSpeed=0;
        int difficulty = Scores.getDifficulty();
        //Toast.makeText(con,  "Height: " + ((r.nextInt(80) + 50)),Toast.LENGTH_SHORT).show();
        for (int i=0; i<15;i++)
        {
            currSpeed = r.nextInt(difficulty*3) + difficulty*3;
            //currSpeed = r.nextInt(45) + 45;
            while ( Math.abs(screenHeight/currSpeed - screenHeight/prevSpeed) <= (difficulty/10)+2)
                currSpeed = r.nextInt(difficulty*5) + difficulty*2;

            icicles.add(new Icicle(context,(i*screenWidth/13)+10, currSpeed/5.0)); // set 7 to inceace every minute
            prevSpeed = currSpeed;
            //icicles.add(new Icicle(context,i*10,1));
        }
        //get prev height so that it is possible

        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    @Override
    public void run() {
        Scores.resetScore();
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        //updating player position
        /*if now icicle - player.toggleIcicle();*/
        Scores.incrementScore();
        player.update();
        for (Icicle p : icicles)
        {
            p.update();
            if ( (((player.getX() <  p.getRightX()) &&  (p.getRightX() < player.getRightX())) ||
                    (player.getRightX() > p.getX() && p.getX() > player.getX()) || (p.getX() == player.getX()) )
                    && (p.getBottom() > player.getY()) )
            {

                //Toast.makeText(con,  "GAME OVER!",Toast.LENGTH_SHORT).show();
                Scores.updateHighScore(con);
                Intent intent = new Intent(con, MainActivity.class);
                con.startActivity(intent);

            }
        }
        //check if intersect
        /*Icicle plat=null;
        for (Icicle p : icicles)
        {
            p.update();
            if ( (player.getSpeed() > 0) &&(player.getCenterX() - p.getLeft() >= -10) && (p.getRight() - player.getCenterX() >=-10) &&
                    (player.getBottom() - p.getTop() >= 0) && (player.getBottom() - p.getTop() < 50))
            {
                isPlayerOnIcicle = true;
                plat = p;
            }
        }
        if (isPlayerOnIcicle)
            player.toggleIcicle(true,plat.getTop());
        else
            player.toggleIcicle(false,-1);*/



    }

    private void draw() {
        //checking if surface is valid
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            //drawing a background color for canvas
            canvas.drawColor(Color.WHITE);
            //Drawing the player
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);
            //Unlocking the canvas
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(3);
            for (Icicle i : icicles)
            {
                canvas.drawBitmap(
                        i.getBitmap(),
                        i.getX(),
                        i.getY(),
                        paint);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public boolean setMovement(int moveIn){

        player.setMovement(moveIn);
        // Be sure to call the superclass implementation
        return true;//super.onTouchEvent(event);
    }
}