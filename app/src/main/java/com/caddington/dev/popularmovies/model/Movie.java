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
    private String title;
    private String overview;
    @ColumnInfo(name = "vote_average")
    private Float voteAverage;
    @ColumnInfo(name = "release_date")
    private String releaseDate;
    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @Ignore
    public Movie(String title, String overview, Float voteAverage, String releaseDate, String posterPath) {
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
    }

    public Movie(int id, String title, String overview, Float voteAverage, String releaseDate, String posterPath) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
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
        title = in.readString();
        overview = in.readString();
        voteAverage = in.readFloat();
        releaseDate = in.readString();
        posterPath = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(title);
        out.writeString(overview);
        out.writeFloat(voteAverage);
        out.writeString(releaseDate);
        out.writeString(posterPath);
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

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
