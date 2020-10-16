/**
 * In Class 05
 * ListAdapter
 * Seamus Beecher & Cam McIntosh
 */

package edu.uncc.inclass05;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import edu.uncc.inclass05.utils.App;
import edu.uncc.inclass05.utils.Data;

public class ListAdapter extends ArrayAdapter<App> {
    public ListAdapter(@NonNull Context context, int resource, @NonNull List<App> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        App app = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_list, parent, false);

        TextView textViewAppName = (TextView) convertView.findViewById(R.id.textViewAppName);
        TextView textViewArtistName = (TextView) convertView.findViewById(R.id.textViewArtistName);
        TextView textViewReleaseDate = (TextView) convertView.findViewById(R.id.textViewReleaseDate);

        textViewAppName.setText(app.getName());
        textViewArtistName.setText(app.getArtistName());
        textViewReleaseDate.setText(app.getReleaseDate());

        return convertView;
    }

}
