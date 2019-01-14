package com.example.android.gameconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //yellow=0;red=1
    int activePlayer=0;
    boolean active = true;
     int[] gameState= {2,2,2,2,2,2,2,2,2};

     int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void playAgain(View view){ //reset all to initial state
        active=true;
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.again);
        linearLayout.setVisibility(View.INVISIBLE);
        activePlayer=0;
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        //reset all images to no source
        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    public void dropin(View view){
        ImageView counter = (ImageView)view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && active) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);g
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions){
                if (gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2){

                        active = false;

                    String winColor = "Red";
                    if(gameState[winningPosition[0]]==0)
                        winColor="Yellow";
                    TextView winner = (TextView)findViewById(R.id.winner);
                    winner.setText(winColor+" has won!");
                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.again);
                    linearLayout.setVisibility(View.VISIBLE);
                } else {

                    boolean gameOver = true;
                    for (int counterState : gameState){
                        if (counterState == 2) gameOver = false;
                    }
                    if (gameOver){
                        TextView winner = (TextView)findViewById(R.id.winner);
                        winner.setText("It's a draw !");
                        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.again);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
