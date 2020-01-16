package com.mitsko.mrdb.entity;

import java.util.Objects;

public class Review {
    private int ID;
    private String userLogin;
    private String movieName;
    private String review;

    public Review(int ID, String userLogin, String movieName, String review) {
        this.ID = ID;
        this.userLogin = userLogin;
        this.movieName = movieName;
        this.review = review;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
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
                userLogin.equals(review1.userLogin) &&
                movieName.equals(review1.movieName) &&
                review.equals(review1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, userLogin, movieName, review);
    }

    @Override
    public String toString() {
        return "Review{" +
                "ID=" + ID +
                ", userLogin='" + userLogin + '\'' +
                ", movieName='" + movieName + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
