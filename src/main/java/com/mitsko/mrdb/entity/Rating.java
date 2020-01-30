package com.mitsko.mrdb.entity;

import java.util.Objects;

public class Rating {
    private int ID;
    private int userID;
    private int movieID;
    private float rating;

    public Rating(int userID, int movieID, float rating) {
        this.userID = userID;
        this.movieID = movieID;
        this.rating = rating;
    }

    public Rating(int ID, int userID, int movieID, float rating) {
        this.ID = ID;
        this.userID = userID;
        this.movieID = movieID;
        this.rating = rating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return ID == rating1.ID &&
                Float.compare(rating1.rating, rating) == 0 &&
                userID == rating1.userID &&
                movieID == rating1.movieID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, userID, movieID, rating);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ID=" + ID +
                ", usersLogin='" + userID + '\'' +
                ", nameOfMovie='" + movieID + '\'' +
                ", rating=" + rating +
                '}';
    }
}
