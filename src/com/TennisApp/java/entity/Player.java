package com.TennisApp.java.entity;

/**
 * This is the Player entity bean. Getters/Setters don't require javadoc.
 * Created by Dave on 11/5/2015.
 */
public class Player {
    private int playerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String NTRPlevel;


    public Player() {
    }

    public Player(int playerId, String firstName, String lastName, String email, String gender, String NTRPlevel, String phone) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.NTRPlevel = NTRPlevel;
        this.phone = phone;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int employeeId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {this.email = email; }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNTRPlevel() {
        return NTRPlevel;
    }

    public void setNTRPlevel(String NTRPlevel) {
        this.NTRPlevel = NTRPlevel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return  System.lineSeparator() +
                "Player: " + " "
                + playerId + " "
                + firstName + " "
                + lastName + " "
                + email + " "
                + NTRPlevel + " "
                + gender + " "
                + phone;
    }
}
