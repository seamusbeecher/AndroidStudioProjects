package edu.uncc.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "demo";
    TextView progressBar;
    ImageButton prevButton, nextButton;
    ImageView imageView;
    int counter;
    ArrayList<String> urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (TextView) findViewById(R.id.progressBar);
        prevButton = (ImageButton) findViewById(R.id.prevButton);
        nextButton = (ImageButton) findViewById(R.id.nextButton);
        imageView = (ImageView) findViewById(R.id.imageView);

        //Disabling before load
        prevButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);

        //Urls
        urls = new ArrayList<>(GetImageFromUrl.getImgUrls());

        //First image
        Picasso.get().load(urls.get(0)).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                //Enable when load
                prevButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            }
            @Override
            public void onError(Exception e) {
                //If load fails
                Toast.makeText(MainActivity.this, "OOPS, url did not load :(", Toast.LENGTH_SHORT).show();
            }
        });
        counter = 1;

        //prevButton
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "PrevButton Clicked");
                new DownloadPrevImage().execute(urls);
                counter--;
            }
        });

        //nextButton
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "NextButton Clicked");
                new DownloadNextImage().execute(urls);
                counter++;
            }
        });
    }

    //Async<Params, Progress, Result>

    class DownloadPrevImage extends AsyncTask<ArrayList<String>, Integer, String> {

        @Override
        protected String doInBackground(ArrayList<String>... arrayLists) {
            return null;
        }

        @Override
        protected void onPostExecute(String url) { //Main Thread!!

            if (counter == 0) {
                Picasso.get().load(urls.get(urls.size() - 1)).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        counter = urls.size();
                        progressBar.setText(counter + " / " + urls.size());
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MainActivity.this, "OOPS, url did not load :(", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                Picasso.get().load(urls.get(counter - 1)).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setText(counter + " / " + urls.size());
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MainActivity.this, "OOPS, url did not load :(", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

    }

    class DownloadNextImage extends AsyncTask<ArrayList<String>, Integer, String> {

        @Override
        protected String doInBackground(ArrayList<String>... arrayLists) {
            return null;
        }

        @Override
        protected void onPostExecute(String url) { //Main Thread!!

            if ((counter - 1) == urls.size()) {
                Picasso.get().load(urls.get(0)).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setText(counter + " / " + urls.size());
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MainActivity.this, "OOPS, url did not load :(", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {

                Picasso.get().load(urls.get(counter - 1)).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setText(counter + " / " + urls.size());
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MainActivity.this, "OOPS, url did not load :(", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }

    }


}

