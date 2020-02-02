package com.mitsko.mrdb.entity;

import java.util.Objects;

public class Movie {
    private int ID;
    private String name;
    private float averageRating;
    private int countOfRatings;
    private String imageName;
    private String description;

    public Movie(String name) {
        this.averageRating = 0;
        this.countOfRatings = 0;
        this.name = name;
    }

    public Movie(int ID, String name, float averageRating, int countOfRatings) {
        this.ID = ID;
        this.name = name;
        this.averageRating = averageRating;
        this.countOfRatings = countOfRatings;
    }

    public Movie(String name, String imageName, String description) {
        this.name = name;
        this.imageName = imageName;
        this.description = description;
    }

    public Movie(int ID, String name, float averageRating, int countOfRatings, String imageName, String description) {
        this.ID = ID;
        this.name = name;
        this.averageRating = averageRating;
        this.countOfRatings = countOfRatings;
        this.imageName = imageName;
        this.description = description;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return ID == movie.ID &&
                Float.compare(movie.averageRating, averageRating) == 0 &&
                countOfRatings == movie.countOfRatings &&
                name.equals(movie.name) &&
                imageName.equals(movie.imageName) &&
                description.equals(movie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, averageRating, countOfRatings, imageName, description);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", averageRating=" + averageRating +
                ", countOfRatings=" + countOfRatings +
                ", imagePath='" + imageName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
