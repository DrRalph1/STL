package com.android_code_challenge.stl.Controller;

// BooksAdapter Controller Class
public class BooksController {

    // Instantiating Variables of the BooksAdapter Controller Class
    public String isbn;
    public String coverImage;
    public String title;
    public String description;
    public String author;
    public String yearOfPublication;

    // Get ISBN
    public String getISBN() {
        return isbn;
    }

    // Set ISBN
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    // Get Cover Image
    public String getCoverImage() {
        return coverImage;
    }

    // Set Cover Image
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    // Get Title
    public String getTitle() {
        return title;
    }

    // Set Title
    public void setTitle(String title) {
        this.title = title;
    }

    // Get Descrition
    public String getDescription() {
        return description;
    }

    // Set Description
    public void setDescription(String description) {
        this.description = description;
    }

    // Get Author
    public String getAuthor() {
        return author;
    }

    // Set Auther
    public void setAuthor(String author) {
        this.author = author;
    }

    // Get Year of Publication
    public String getYearOfPublication() {
        return yearOfPublication;
    }

    // Get Year of Publication
    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

}
