package com.example.inclass04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    TextView textViewProgressBar, textViewoutput, textViewSeekBarProgress;
    SeekBar seekBar;
    ProgressBar progressBar;
    Button buttonThread, buttonAsync;
    static final String TAG = "demo";
    Handler handler;
    Thread threadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewProgressBar = (TextView) findViewById(R.id.textViewProgressBar);
        textViewoutput = (TextView) findViewById(R.id.textViewoutput);
        textViewSeekBarProgress = (TextView) findViewById(R.id.textViewSeekBarProgress);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        buttonAsync = (Button) findViewById(R.id.buttonAsync);
        buttonThread = (Button) findViewById(R.id.buttonThread);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSeekBarProgress.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //NOT USED
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //NOT USED
            }
        });

        //Async Button
        buttonAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int complexity = seekBar.getProgress();

                if(complexity > 0 ) {
                    //check on the seekbar if it is not 0 ??
                    progressBar.setMax(complexity);
                    new AsyncAvg().execute(complexity);
                } else {
                    Toast.makeText(MainActivity.this, "Complexity cannot be 0!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Thread button
        buttonThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int complexity = seekBar.getProgress();

                if (complexity > 0) {
                    progressBar.setMax(complexity);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(0);
                    textViewProgressBar.setText("0");
                    textViewProgressBar.setVisibility(View.VISIBLE);

                    threadPool.execute(new DoRunnable(complexity));

                } else {
                    Toast.makeText(MainActivity.this, "Complexity cant be 0", Toast.LENGTH_SHORT).show();
                }

            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                switch (msg.what) {

                    case DoRunnable.STATUS_START:
                        Log.d(TAG, "Starting...");
                        break;

                    case DoRunnable.STATUS_STOP:
                        Log.d(TAG, "Stopping...");
                        avg.setText(msg.getData().getDouble(DoRunnable.AVG_KEY));
                        break;

                    case DoRunnable.STATUS_PROGRESS:
                        Log.d(TAG, "Progress..." + msg.getData().getInt(DoRunnable.PROGRESS_KEY));
                        textViewProgressBar.setText((msg.getData().getInt(DoRunnable.PROGRESS_KEY) + 1) + "/" + msg.getData().getInt(DoRunnable.STEPS_KEY));
                        progressBar.setProgress(msg.getData().getInt(DoRunnable.PROGRESS_KEY) + 1);
                        break;

                }

                return false;
            }
        });

        threadPool = Executors.newFixedThreadPool(2);

    }

    //Asycn Butotn
    class AsyncAvg extends AsyncTask<Integer, Integer, Double> {
        int total = 0;
        @Override
        protected void onPreExecute() { //Main Thread !!
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            textViewProgressBar.setText("0");
            textViewProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Double doInBackground(Integer... integers) { //Background !!
            int N = integers[0];
            total = N;
            double sum = 0;

            for(int i = 0; i< N; i++) {
                sum = sum + HeavyWork.getNumber();
                publishProgress(i);
            }
            return sum /((double)N);
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //Main Thread !!
            int progress = values[0];
            progressBar.setProgress(progress + 1);
            textViewProgressBar.setText(String.valueOf(progress + 1) + "/" + String.valueOf(total));
        }

        @Override
        protected void onPostExecute(Double average) { //Main Thread!!
            textViewoutput.setText(String.valueOf(average));
        }
    }

    class DoRunnable implements Runnable {

        static final int STATUS_START = 0x00;
        static final int STATUS_PROGRESS = 0x01;
        static final int STATUS_STOP = 0x02;
        static final String PROGRESS_KEY = "PROGRESS";
        static final String STEPS_KEY = "STEPS";
        static final String AVG_KEY = "AVG";

        int steps;

        public DoRunnable(int steps) {
            this.steps = steps;
        }


        @Override
        public void run() {

            Message startMessage = new Message();
            startMessage.what = STATUS_START;
            handler.sendMessage(startMessage);

            double sum = 0;

            for (int i = 0; i < steps; i++) {
                sum = sum + HeavyWork.getNumber();

                Message stopMessage = new Message();
                stopMessage.what = STATUS_PROGRESS;

                Bundle bundle = new Bundle();
                bundle.putInt(PROGRESS_KEY, (Integer)i);
                bundle.putInt(STEPS_KEY, (Integer)steps);
                stopMessage.setData(bundle);
                handler.sendMessage(stopMessage);
            }

            double average = sum / ((double)steps);

            Message stopMessage = new Message();
            stopMessage.what = STATUS_STOP;
            Bundle bundle = new Bundle();
            bundle.getDouble(AVG_KEY, average);
            stopMessage.setData(bundle);
            handler.sendMessage(stopMessage);

        }
    }

}
