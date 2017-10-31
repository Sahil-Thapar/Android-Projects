package com.mandeepkaur.angrybird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import pl.droidsonroids.gif.GifTextView;

public class MainActivity extends AppCompatActivity {

    GifTextView g1,g2,g3,g4,g5,g6,g7,g8,g9,g10,g11,g12,g13,g14,g15,g16,g17,g18,g19,g20;
    TextView hitMiss,score,timer;
    ArrayList<Integer> randNoArray;
    Runnable runnable, runnable1;
    Handler handler, handler1;
    Random random;
    int index;
    int speed = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.maintheme);
        ring.start();

        random = new Random();
        hitMiss = (TextView) findViewById(R.id.hitMiss);
        score = (TextView) findViewById(R.id.score);
        timer = (TextView) findViewById(R.id.timer);

        g1 = (GifTextView)findViewById(R.id.button1);
        g2 = (GifTextView)findViewById(R.id.button2);
        g3 = (GifTextView)findViewById(R.id.button3);
        g4 = (GifTextView)findViewById(R.id.button4);
        g5 = (GifTextView)findViewById(R.id.button5);
        g6 = (GifTextView)findViewById(R.id.button6);
        g7 = (GifTextView)findViewById(R.id.button7);
        g8 = (GifTextView)findViewById(R.id.button8);
        g9 = (GifTextView)findViewById(R.id.button9);
        g10 = (GifTextView)findViewById(R.id.button10);
        g11 = (GifTextView)findViewById(R.id.button11);
        g12 = (GifTextView)findViewById(R.id.button12);
        g13 = (GifTextView)findViewById(R.id.button13);
        g14 = (GifTextView)findViewById(R.id.button14);
        g15 = (GifTextView)findViewById(R.id.button15);
        g16 = (GifTextView)findViewById(R.id.button16);
        g17 = (GifTextView)findViewById(R.id.button17);
        g18 = (GifTextView)findViewById(R.id.button18);
        g19 = (GifTextView)findViewById(R.id.button19);
        g20 = (GifTextView)findViewById(R.id.button20);

        resetImages();

        final ArrayList<GifTextView> imageList = new ArrayList<>();
        imageList.add(g1);
        imageList.add(g2);
        imageList.add(g3);
        imageList.add(g4);
        imageList.add(g5);
        imageList.add(g6);
        imageList.add(g7);
        imageList.add(g8);
        imageList.add(g9);
        imageList.add(g10);
        imageList.add(g11);
        imageList.add(g12);
        imageList.add(g13);
        imageList.add(g14);
        imageList.add(g15);
        imageList.add(g16);
        imageList.add(g17);
        imageList.add(g18);
        imageList.add(g19);
        imageList.add(g20);

        randNoArray = new ArrayList<Integer>();

        do
        {
            int num = random.nextInt(19);
             randNoArray.add(num);
        }while(randNoArray.size() <=50);

        timer.setText("Time : " + Constants.time + " sec");
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                if (Constants.time != 0) {
                    Constants.time--;
                    timer.setText("Time : " + Constants.time + " sec");
                    handler.postDelayed(this, 1000);
                }
                else if (Constants.time == 0) {
                    timer.setText("Time : " + Constants.time + " sec");
                    handler.removeCallbacks(runnable);
                    disableButton();
                    ring.stop();
                    hitMiss.setText("Game Over Buddy! Exit is here!");
                    hitMiss.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.Theme_Sphinx_Dialog_Alert);
                    builder.setTitle("GAME OVER!");
                    builder.setMessage("You hunt for Proody ends here. It was fun though!. Your hunt score is "+Constants.correctCount);
                    builder.setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Constants.time = 30;
                            Constants.correctCount=0;
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.show();
              }
            }
        };
        handler.postDelayed(runnable, 1000);


        handler1 = new Handler();
        runnable1 = new Runnable() {
            @Override
            public void run() {
                if (Constants.time != 0) {
                    if(Constants.time == 25){
                        speed = 800;
                    }else if(Constants.time == 20){
                        speed = 750;
                    }else if(Constants.time == 15){
                        speed = 700;
                    }
                    resetImages();
                    imageList.get(randNoArray.get(index)).setBackgroundResource(R.drawable.target);
                    index++;

                    handler1.postDelayed(this, speed);

                }else if (Constants.time == 0) {
                    handler1.removeCallbacks(runnable1);
                }
            }
        };
        handler1.postDelayed(runnable1, speed);

        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(0);
            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(1);
            }
        });
        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(2);
            }
        });
        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(3);
            }
        });
        g5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(4);
            }
        });
        g6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(5);
            }
        });
        g7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(6);
            }
        });
        g8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(7);
            }
        });
        g9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(8);
            }
        });
        g10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(9);
            }
        });
        g11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(10);
            }
        });
        g12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(11);
            }
        });
        g13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(12);
            }
        });
        g14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(13);
            }
        });
        g15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(14);
            }
        });
        g16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(15);
            }
        });
        g17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(16);
            }
        });
        g18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(17);
            }
        });
        g19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(18);
            }
        });
        g20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkMatch(19);
            }
        });

    }
    private void checkMatch(int buttonNum){
        Log.i("data",String.valueOf(randNoArray.get(index-1)));
        if(buttonNum == randNoArray.get(index-1)){
            MediaPlayer ring1= MediaPlayer.create(MainActivity.this,R.raw.birdshot);
            ring1.start();
            Constants.correctCount +=10;
            hitMiss.setText("Wooo! That was a Hit.");
            score.setText("Score : "+Constants.correctCount);
        }else{
            hitMiss.setText("Ouch! You Missed It.");
        }
    }

    private void resetImages(){

        g1.setBackgroundResource(R.drawable.green);
        g2.setBackgroundResource(R.drawable.cream);
        g3.setBackgroundResource(R.drawable.cream);
        g4.setBackgroundResource(R.drawable.green);
        g5.setBackgroundResource(R.drawable.cream);
        g6.setBackgroundResource(R.drawable.green);
        g7.setBackgroundResource(R.drawable.green);
        g8.setBackgroundResource(R.drawable.cream);
        g9.setBackgroundResource(R.drawable.cream);
        g10.setBackgroundResource(R.drawable.green);
        g11.setBackgroundResource(R.drawable.cream);
        g12.setBackgroundResource(R.drawable.green);
        g13.setBackgroundResource(R.drawable.cream);
        g14.setBackgroundResource(R.drawable.cream);
        g15.setBackgroundResource(R.drawable.green);
        g16.setBackgroundResource(R.drawable.cream);
        g17.setBackgroundResource(R.drawable.green);
        g18.setBackgroundResource(R.drawable.cream);
        g19.setBackgroundResource(R.drawable.green);
        g20.setBackgroundResource(R.drawable.cream);

    }
    private void disableButton(){
        g1.setClickable(false);
        g2.setClickable(false);
        g3.setClickable(false);
        g4.setClickable(false);
        g5.setClickable(false);
        g6.setClickable(false);
        g7.setClickable(false);
        g8.setClickable(false);
        g9.setClickable(false);
        g10.setClickable(false);
        g11.setClickable(false);
        g12.setClickable(false);
        g13.setClickable(false);
        g14.setClickable(false);
        g15.setClickable(false);
        g16.setClickable(false);
        g17.setClickable(false);
        g18.setClickable(false);
        g19.setClickable(false);
        g20.setClickable(false);
    }
}
