package com.mitsko.mrdb.entity;

import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.entity.util.Status;

import java.util.Objects;

public class User {
    private int ID;
    private String login;
    private String password;
    private final Role role;
    private Status status;
    private float averageRating;

    public User(int ID, String login, String password, Role role) {
        this.ID = ID;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = Status.NO_LIMITS;
        this.averageRating = 0;
    }

    public User(int ID, String login, String password, String role, String status, float averageRating) {
        this.ID = ID;
        this.login = login;
        this.password = password;
        this.role = Role.valueOf(role);
        this.status = Status.valueOf(status);
        this.averageRating = averageRating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID == user.ID &&
                Float.compare(user.averageRating, averageRating) == 0 &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                role == user.role &&
                status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, login, password, role, status, averageRating);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", averageRating=" + averageRating +
                '}';
    }
}
