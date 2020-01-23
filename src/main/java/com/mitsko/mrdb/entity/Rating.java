package com.mitsko.mrdb.entity;

import java.util.Objects;

public class Rating {
    private int ID;
    private String usersLogin;
    private String nameOfMovie;
    private float rating;

    public Rating(String usersLogin, String nameOfMovie, float rating) {
        this.usersLogin = usersLogin;
        this.nameOfMovie = nameOfMovie;
        this.rating = rating;
    }

    public Rating(int ID, String usersLogin, String nameOfMovie, float rating) {
        this.ID = ID;
        this.usersLogin = usersLogin;
        this.nameOfMovie = nameOfMovie;
        this.rating = rating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(String usersLogin) {
        this.usersLogin = usersLogin;
    }

    public String getNameOfMovie() {
        return nameOfMovie;
    }

    public void setNameOfMovie(String nameOfMovie) {
        this.nameOfMovie = nameOfMovie;
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
                usersLogin.equals(rating1.usersLogin) &&
                nameOfMovie.equals(rating1.nameOfMovie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, usersLogin, nameOfMovie, rating);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ID=" + ID +
                ", usersLogin='" + usersLogin + '\'' +
                ", nameOfMovie='" + nameOfMovie + '\'' +
                ", rating=" + rating +
                '}';
    }
}
