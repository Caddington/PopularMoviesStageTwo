package com.caddington.dev.popularmovies.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

//Referenced source documentation for Parcelable implementation, at https://developer.android.com/reference/android/os/Parcelable
@Entity(tableName = "movie")
public class Movie implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    //TODO: Add movieId property to store tMDB's ID in addition to the auto-generated Room row ID above.
    private String title;
    private String overview;
    @ColumnInfo(name = "vote_average")
    private Float vote_average;
    @ColumnInfo(name = "release_date")
    private String releaseDate;
    @ColumnInfo(name = "poster_path")
    private String poster_path;

    @Ignore
    public Movie(String title, String overview, Float voteAverage, String releaseDate, String posterPath) {
        this.title = title;
        this.overview = overview;
        this.vote_average = voteAverage;
        this.releaseDate = releaseDate;
        this.poster_path = posterPath;
    }

    public Movie(int id, String title, String overview, Float vote_average, String releaseDate, String poster_path) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.releaseDate = releaseDate;
        this.poster_path = poster_path;
    }

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
        releaseDate = in.readString();
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
        out.writeString(releaseDate);
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String posterPath) {
        this.poster_path = posterPath;
    }
}
