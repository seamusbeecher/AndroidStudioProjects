/**
 * In Class 05
 * DetailsAdapter
 * Seamus Beecher & Cam McIntosh
 */

package edu.uncc.inclass05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.uncc.inclass05.utils.App;

public class DetailsAdapter extends ArrayAdapter<App> {
    public DetailsAdapter(@NonNull Context context, int resource, @NonNull List<App> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        App app = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_list, parent, false);

        TextView textViewDetailsAppName = (TextView) convertView.findViewById(R.id.textViewDetailsAppName);
        TextView textViewDetailsArtistName = (TextView) convertView.findViewById(R.id.textViewDetailsArtistName);
        TextView textViewDetailsReleaseDate = (TextView) convertView.findViewById(R.id.textViewDetailsReleaseDate);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        textViewDetailsAppName.setText(app.getName());
        textViewDetailsArtistName.setText(app.getArtistName());
        textViewDetailsReleaseDate.setText(app.getReleaseDate());
        Picasso.get().load(app.getUrl()).into(imageView);

        return convertView;
    }

}
