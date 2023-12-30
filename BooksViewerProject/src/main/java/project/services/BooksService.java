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

    public Book addBook(String bookName, String writer, int pageCount, Calendar releaseDate, String imageURL, String description) {

        return repository.addBook(
                bookName,
                writer,
                pageCount,
                releaseDate,
                imageURL,
                description
        );
    }

    public List<Book> getBooks() {
        return repository.getBooks();
    }

    public boolean contains(Book book)
    {
        List<Book> books = getBooks();

        for (Book bookInList: books) {
            if(bookInList.getBookName().equals(book.getBookName()) ||
            bookInList.getPageCount() == book.getPageCount() ||
            bookInList.getWriter().equals(book.getWriter()) ||
            bookInList.getCoverImage().equals(book.getCoverImage())
            )
                return true;
        }

        return false;
    }
}
