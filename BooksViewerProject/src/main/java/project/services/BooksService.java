package project.services;

import project.data.BooksRepository;
import project.data.TestRepository;
import project.models.Book;
import project.util.OrderByBookComparator;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BooksService {

    private BooksRepository repository = new TestRepository();

    public Book addBook(String bookName, String writer, int pageCount, Calendar releaseDate, String imageURL) {

        return repository.addBook(
                bookName,
                writer,
                pageCount,
                releaseDate,
                imageURL
        );
    }

    public List<Book> getBooks() {
        return repository.getBooks();
    }
}
