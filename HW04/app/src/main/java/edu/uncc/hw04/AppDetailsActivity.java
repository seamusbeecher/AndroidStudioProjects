/*
 * Seamus Beecber & Cam McIntosh
 * HW04
 * AppsDetailsActivity
 * No errors shown,
 * not able to display url image in imageView: detailsImageView,
 * only error message shown
 * */

package edu.uncc.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import edu.uncc.hw04.utils.App;

public class AppDetailsActivity extends AppCompatActivity {

    TextView detailsAppName, detailsArtistName, detailsReleaseDate;
    ImageView detailsImageView;
    ListView listView3;

    ProgressDialog progress;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        detailsAppName = (TextView) findViewById(R.id.detailsAppName);
        detailsArtistName = (TextView) findViewById(R.id.detailsArtistName);
        detailsReleaseDate = (TextView) findViewById(R.id.detailsReleaseDate);
        detailsImageView = (ImageView) findViewById(R.id.detailsImageView);
        listView3 = (ListView) findViewById(R.id.listView3);

        Intent intentPosition = getIntent();
        Intent intentData = getIntent();

        if (intentPosition.getExtras() != null) {
            App app = (App) intentData.getSerializableExtra(AppsListActivity.APP_DATA);
            ArrayList<String> genres = new ArrayList<>(app.getGenres());
            String url = app.getUrl();

            setTitle(app.getName());

            detailsAppName.setText(app.getName());
            detailsArtistName.setText(app.getArtistName());
            detailsReleaseDate.setText(app.getReleaseDate());
            //IMAGEVIEW ERROR
            new LoadImage().execute(url);

            DetailsAdapter detailsAdapter = new DetailsAdapter(this, R.layout.app_details, genres);
            listView3.setAdapter(detailsAdapter);
        }

    }

    public class LoadImage extends AsyncTask <String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap=null;

            try {
                bitmap= BitmapFactory.decodeStream((InputStream)new URL(strings[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            //show progress dialog while image is loading
            progress=new ProgressDialog(AppDetailsActivity.this);
            progress.setMessage("Loading Image....");
            progress.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap!=null) {
                detailsImageView.setImageBitmap(bitmap);
                progress.dismiss();
            } else {
                //ERROR CAUGHT HERE
                progress.dismiss();
                Toast.makeText(AppDetailsActivity.this,"Some error occurred!",Toast.LENGTH_LONG).show();
            }
        }
    }
}
