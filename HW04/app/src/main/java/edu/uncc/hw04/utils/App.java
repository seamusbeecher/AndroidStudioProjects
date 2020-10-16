package edu.uncc.hw04.utils;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class App implements Serializable {
    ArrayList<String> genres;
    String id, artistId, artistName, artworkUrl100, name, releaseDate, url;

    public App(ArrayList<String> genres, String id, String artistId, String artistName, String artworkUrl100, String name, String releaseDate, String url) {
        this.genres = genres;
        this.id = id;
        this.artistId = artistId;
        this.artistName = artistName;
        this.artworkUrl100 = artworkUrl100;
        this.name = name;
        this.releaseDate = releaseDate;
        this.url = url;
    }

    @Override
    public String toString() {
        return "App{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", artistId='" + artistId + '\'' +
                ", artistName='" + artistName + '\'' +
                ", artworkUrl100='" + artworkUrl100 + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", url='" + url + '\'' +
                ", genres=" + genres +
                '}';
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getId() {
        return id;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getUrl() {
        return url;
    }

}
