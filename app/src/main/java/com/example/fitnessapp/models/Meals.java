package com.example.fitnessapp.models;

import java.util.List;

public class Meals {
    private List<Repast> myRepast;

    public Meals(List<Repast> myRepast) {
        this.myRepast = myRepast;
    }

    public List<Repast> getMyRepast() {
        return myRepast;
    }

    public void setMyRepast(List<Repast> myRepast) {
        this.myRepast = myRepast;
    }
}
