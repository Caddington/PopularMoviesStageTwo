package com.caddington.dev.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

//Referenced source documentation for Parcelable implementation, at https://developer.android.com/reference/android/os/Parcelable
public class Movie implements Parcelable {

    private int id;
    private String title;
    private String overview;
    private Float vote_average;
    private String release_date;
    private String poster_path;

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){
        public Movie createFromParcel(Parcel in){
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[0];
        }
    };

    private Movie(Parcel in){
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        vote_average = in.readFloat();
        release_date = in.readString();
        poster_path = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeString(title);
        out.writeString(overview);
        out.writeFloat(vote_average);
        out.writeString(release_date);
        out.writeString(poster_path);
    }

    //GETTERS/SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
