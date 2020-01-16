package com.mitsko.mrdb.entity;

import java.util.Objects;

public class Movie {
    private int ID;
    private String name;
    private float averageRating;
    private int countOfRatings;

    public Movie(int ID, String name, float averageRating, int countOfRatings) {
        this.ID = ID;
        this.name = name;
        this.averageRating = averageRating;
        this.countOfRatings = countOfRatings;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getCountOfRatings() {
        return countOfRatings;
    }

    public void setCountOfRatings(int countOfRatings) {
        this.countOfRatings = countOfRatings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return ID == movie.ID &&
                Float.compare(movie.averageRating, averageRating) == 0 &&
                countOfRatings == movie.countOfRatings &&
                name.equals(movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, averageRating, countOfRatings);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", averageRating=" + averageRating +
                ", countOfRatings=" + countOfRatings +
                '}';
    }
}
