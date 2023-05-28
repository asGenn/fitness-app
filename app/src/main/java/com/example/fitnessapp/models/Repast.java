package com.example.fitnessapp.models;

public class Repast {
    public String getRepast() {
        return repast;
    }

    public void setRepast(String repast) {
        this.repast = repast;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public Repast(String repast, String meal) {
        this.repast = repast;
        this.meal = meal;
    }

    private String repast,meal;

}
