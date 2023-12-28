package project.data;

import project.models.Book;

import java.util.Calendar;
import java.util.List;

public interface BooksRepository {

    Book addBook(String bookName,
                 String writer,
                 int pageCount,
                 Calendar releaseDate,
                 String imageURL
    );

    List<Book> getBooks();
}
