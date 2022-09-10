package com.example.mybooksfinal;

public class BooksCollection {

    //variables
    private String title;
    private String author;
    private String Publisher;
    private String Description;
    private String Date;

    public BooksCollection(String title, String author, String publisher, String description, String date) {
        this.title = title;
        this.author = author;
        this.Publisher = publisher;
        this.Description = description;
        this.Date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        this.Publisher = publisher;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }


}
