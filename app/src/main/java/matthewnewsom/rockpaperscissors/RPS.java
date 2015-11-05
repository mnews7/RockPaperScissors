package matthewnewsom.rockpaperscissors;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class RPS extends ActionBarActivity {
    int wins = 0;
    int losses = 0;

    int cdTimer = 5000;
    int cdTimerInterval = 500;
    final Handler handler = new Handler(Looper.getMainLooper());
    int lossStreak = 0;
    int winsStreak = 0;
    String lastPlayerMove = "Rock";
    String lastCompMove = "Rock";
    String spCompMoves = "Losses";
    String spPlayerMoves = "Wins";
    String spLastPlayerMove = "lastPlayerMove";
    String spLastCompMove = "lastCompMove";
// pass to scorelist fragment
    ArrayList<String> playerMoves = new ArrayList<>();
    ArrayList<String> compMoves = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_rps);
        Toolbar toolbartest = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbartest);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_r, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.score_board) {
            Bundle scoreArgs = new Bundle();
            scoreArgs.putStringArrayList(spPlayerMoves, playerMoves);
            scoreArgs.putStringArrayList(spCompMoves, compMoves);
          // try and remember dialog fragment stuff

          //DialogFragment scorelist = new DialogFragment();
          //scorelist.

        }
        return super.onOptionsItemSelected(item);
    }


    //updates text views for win/loss/draws
    protected void updateTextView() {

        TextView mTextViewWins = (TextView) findViewById(R.id.Wins);
        TextView mTextViewLoss = (TextView) findViewById(R.id.Loss);
        mTextViewWins.setText("You've Won: " + wins);
        mTextViewLoss.setText("You've Lost: " + losses);


    }

    // starts game
    public void startButtonClick(View view) {
        losses = 0;
        wins = 0;
        cdTimer = 5000;


        updateTextView();
        handler.post(new Runnable() {
            @Override
            public void run() {

                gameStart();

                if (losses == 0) {
                    if (cdTimer < 1500) {
                        cdTimer = 1000;
                    } else {
                        cdTimer = cdTimer - 500;
                    }
                    handler.postDelayed(this, cdTimer + 500);


                } else {
                    handler.removeCallbacksAndMessages(null);

                    updateTextView();

                }

            }
        });
    }


    public void gameStart() {
        //initialize  buttons
        final Button startButton = (Button) findViewById(R.id.startbutton);
        final ImageButton scissorsButton = (ImageButton) findViewById(R.id.scissorsBut);
        final ImageButton rockButton = (ImageButton) findViewById(R.id.rockBut);
        final ImageButton paperButton = (ImageButton) findViewById(R.id.paperBut);
        final ImageView mImageComp = (ImageView) findViewById(R.id.computermove);
        final TextView mTextViewDraws = (TextView) findViewById(R.id.Draws);
        final CountDownTimer mCDT;


        // generate instance of gamePlay
        final gamePlay mGameplay = new gamePlay();
        final String mCompChoice = mGameplay.compChoice();
        scissorsButton.setEnabled(true);
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);

        lastCompMove = mGameplay.compChoice();

        mCDT = new CountDownTimer(cdTimer, cdTimerInterval) {

            public void onTick(long millisUntilFinished) {
                mTextViewDraws.setText("milliseconds remaining: " + millisUntilFinished);
            }

            public void onFinish() {

                mTextViewDraws.setText("done!");
                handler.removeCallbacksAndMessages(null);
                losses = losses + 1;


                mImageComp.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);
                updateTextView();
                scissorsButton.setEnabled(false);
                rockButton.setEnabled(false);
                paperButton.setEnabled(false);

                playerMoves.add("None :(");
                compMoves.add(lastCompMove);
                Log.v("PlayerMove = ", playerMoves.get(0));
                Log.v("Comp move = ", compMoves.get(0));




            }
        }.start();

        //User selects ROCK
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCDT.cancel();
                String s = mGameplay.setChoice(3);
                String winner = mGameplay.winDeterm(s, mCompChoice);
                if (winner.equals("Win")) {
                    wins = wins + 1;


                } else {
                    losses = losses + 1;

                    startButton.setVisibility(View.VISIBLE);
                    handler.removeCallbacksAndMessages(null);

                }
                updateTextView();
                mImageComp.setVisibility(View.INVISIBLE);
                scissorsButton.setEnabled(false);
                rockButton.setEnabled(false);
                paperButton.setEnabled(false);
                playerMoves.add(mGameplay.setChoice(3));
                compMoves.add(mCompChoice);
                Log.v("PlayerMove = ", playerMoves.get(0));
                Log.v("Comp move = ", compMoves.get(0));


            }

        });
        //user selects scissors
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCDT.cancel();
                String s = mGameplay.setChoice(2);
                String winner = mGameplay.winDeterm(s, mCompChoice);
                if (winner.equals("Win")) {
                    wins = wins + 1;


                } else {
                    losses = losses + 1;

                    startButton.setVisibility(View.VISIBLE);
                    handler.removeCallbacksAndMessages(null);

                }
                updateTextView();
                mImageComp.setVisibility(View.INVISIBLE);
                scissorsButton.setEnabled(false);
                rockButton.setEnabled(false);
                paperButton.setEnabled(false);
                playerMoves.add(mGameplay.setChoice(2));
                compMoves.add(mCompChoice);
                Log.v("PlayerMove = ", playerMoves.get(0));
                Log.v("Comp move = ", compMoves.get(0));



            }

        });
        //user selects paper
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCDT.cancel();
                String s = mGameplay.setChoice(1);
                String winner = mGameplay.winDeterm(s, mCompChoice);
                if (winner.equals("Win")) {
                    wins = wins + 1;
                    winsStreak = winsStreak + 1;


                } else {
                    losses = losses + 1;

                    startButton.setVisibility(View.VISIBLE);
                    handler.removeCallbacksAndMessages(null);

                }
                updateTextView();
                mImageComp.setVisibility(View.INVISIBLE);
                scissorsButton.setEnabled(false);
                rockButton.setEnabled(false);
                paperButton.setEnabled(false);
                playerMoves.add(mGameplay.setChoice(1));
                compMoves.add(mCompChoice);
                Log.v("PlayerMove = ", playerMoves.get(0));
                Log.v("Comp move = ", compMoves.get(0));


            }

        });

        startButton.setVisibility(View.INVISIBLE);

        // determine which image to use for computer choice and set it to the image
        switch (mCompChoice) {
            case "Rock":
                mImageComp.setImageResource(R.drawable.rock);
                break;
            case "Paper":
                mImageComp.setImageResource(R.drawable.paper);
                break;
            case "Scissors":
                mImageComp.setImageResource(R.drawable.scissors);
                break;
        }


        //display the image transitioning in and give user some time to select input
        //TODO image transition
        mImageComp.setVisibility(View.VISIBLE);
        mImageComp.bringToFront();

    }

}














