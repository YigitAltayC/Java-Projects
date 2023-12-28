package project.models;

import project.util.BookImage;

import java.util.Calendar;

public class Book {

    private int ID;
    private final String bookName;
    private final String writer;
    private final int pageCount;
    private final Calendar releaseDate;
    private BookImage coverImage;

    public Book(int ID, String bookName, String writer, int pageCount, Calendar releaseDate, String imageURL) {
        this.ID = ID;
        this.bookName = bookName;
        this.writer = writer;
        this.pageCount = pageCount;
        this.releaseDate = releaseDate;
        coverImage = new BookImage(imageURL);
    }

    public Book(String bookName, String writer, int pageCount, Calendar releaseDate, String imageURL) {
        this.bookName = bookName;
        this.writer = writer;
        this.pageCount = pageCount;
        this.releaseDate = releaseDate;
        coverImage = new BookImage(imageURL);
    }

    public String getBookName() {
        return bookName;
    }

    public String getWriter() {
        return writer;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public BookImage getCoverImage() {
        return coverImage;
    }
}
