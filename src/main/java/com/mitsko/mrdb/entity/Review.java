package com.mitsko.mrdb.entity;

import java.util.Objects;

public class Review {
    private int ID;
    private int userID;
    private int movieID;
    private String review;

    public Review(int userID, int movieID, String review) {
        this.userID = userID;
        this.movieID = movieID;
        this.review = review;
    }

    public Review(int ID, int userID, int movieID, String review) {
        this.ID = ID;
        this.userID = userID;
        this.movieID = movieID;
        this.review = review;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return ID == review1.ID &&
                userID == review1.userID &&
                movieID == review1.movieID &&
                review.equals(review1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, userID, movieID, review);
    }

    @Override
    public String toString() {
        return "Review{" +
                "ID=" + ID +
                ", userLogin='" + userID + '\'' +
                ", movieName='" + movieID + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
