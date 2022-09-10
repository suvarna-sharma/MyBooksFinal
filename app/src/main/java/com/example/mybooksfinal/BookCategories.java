package com.example.mybooksfinal;

public class BookCategories {


    private String category;
    private String goal;


    public BookCategories(String category, String goal) {
        this.category = category;
        this.goal = goal;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }


}
