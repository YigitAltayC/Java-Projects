package project.data;

import project.models.Book;
import project.util.OrderByBookComparator;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TestRepository implements BooksRepository{

    private static int COUNT = 0;
    private List<Book> books;

    @Override
    public Book addBook(String bookName, String writer, int pageCount, Calendar releaseDate, String imageURL) {

        COUNT++;

        Book newBook = new Book(COUNT, bookName, writer, pageCount, releaseDate, imageURL);
        books.add(newBook);

        return newBook;
    }

    @Override
    public List<Book> getBooks() {

        Set<Book> bookSet = new TreeSet<Book>(new OrderByBookComparator());

        bookSet.addAll(books);

        return bookSet.stream().toList();
    }
}
