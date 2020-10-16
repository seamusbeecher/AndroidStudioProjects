/*
 * Seamus Beecber & Cam McIntosh
 * HW04
 * ListAdapter
 * DId not load image using Async
 * */

package edu.uncc.hw04;

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

import edu.uncc.hw04.utils.App;

public class ListAdapter extends ArrayAdapter<App> {
    public ListAdapter(@NonNull Context context, int resource, @NonNull List<App> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        App app = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_list, parent, false);

        TextView listAdapterAppName = (TextView) convertView.findViewById(R.id.listAdapterAppName);
        TextView listAdapterArtistName = (TextView) convertView.findViewById(R.id.listAdapterArtistName);
        TextView listAdapterReleaseDate = (TextView) convertView.findViewById(R.id.listAdapterReleaseDate);
        ImageView listAdapterimageView = (ImageView) convertView.findViewById(R.id.listAdapterimageView);

        listAdapterAppName.setText(app.getName());
        listAdapterArtistName.setText(app.getArtistName());
        listAdapterReleaseDate.setText(app.getReleaseDate());
        Picasso.get().load(app.getUrl()).into(listAdapterimageView);//DOES NOT DISPLAY

        return convertView;
    }

}
