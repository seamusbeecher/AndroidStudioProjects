package edu.uncc.inclass05.utils;

import java.util.ArrayList;

public class App {
    ArrayList<String> genres;
    String id, artistId, artistName, artworkUrl100, name, releaseDate, url;

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

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getId() {
        return id;
    }

    public String getArtistId() {
        return artistId;
    }

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
}
