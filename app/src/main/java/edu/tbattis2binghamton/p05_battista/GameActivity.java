package edu.tbattis2binghamton.p08_battista;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);
        gameView = new GameView(this);

        //adding it to contentview
        setContentView(gameView);

        View mView = new View(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    //running the game when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int screenWidth =  Resources.getSystem().getDisplayMetrics().widthPixels;
        int eventaction = event.getAction();
        switch (eventaction) {

            case MotionEvent.ACTION_DOWN:
                if (x < screenWidth /2)
                    gameView.setMovement(-1);
                else
                    gameView.setMovement(1);
                break;

            case MotionEvent.ACTION_MOVE:
                if (x < screenWidth /2)
                    gameView.setMovement(-1);
                else
                    gameView.setMovement(1);
                break;

            case MotionEvent.ACTION_UP:
                gameView.setMovement(0);
                break;
        }
        return true;
    }

    /*@Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub


        gameView.setMovement(-5);
        return super.dispatchTouchEvent(event);

        @Override

    }
    }*/
}
