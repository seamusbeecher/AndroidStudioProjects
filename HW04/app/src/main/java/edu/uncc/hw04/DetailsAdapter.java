/*
 * Seamus Beecber & Cam McIntosh
 * HW04
 * DetailsAdapter
 * */

package edu.uncc.hw04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.uncc.hw04.utils.App;

public class DetailsAdapter extends ArrayAdapter<String> {
    public DetailsAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String genres = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_details, parent, false);

        TextView detailsAdapterGenres = (TextView) convertView.findViewById(R.id.detailsAdapterGenres);

        detailsAdapterGenres.setText(genres);

        return convertView;
    }

}
