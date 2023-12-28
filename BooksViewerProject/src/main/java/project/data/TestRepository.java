package project.data;

import project.models.Book;
import project.util.OrderByBookComparator;
import project.util.Utils;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TestRepository implements BooksRepository{

    private static int COUNT = 0;
    private final List<Book> books = List.of(
            new Book(
                    0,
                    "Harry Potter and the Deathly Hallows",
                    "J.K. Rowling",
                    759,
                    Utils.getCalendar(21, 7, 2007),
                    "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1663805647i/136251.jpg"
            ),

            new Book(
                    1,
                    "The Hunger Games",
                    "Suzanne Collins",
                    374,
                    Utils.getCalendar(14, 7, 2008),
                    "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1586722975i/2767052.jpg"
            ),

            new Book(
                    2,
                    "1984",
                    "George Orwell",
                    328,
                    Utils.getCalendar(8, 8, 1949),
                    "https://i.dr.com.tr/cache/600x600-0/originals/0000000064038-1.jpg"
            )

    );

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

    @Override
    public Book getBook(String name) {
        for (Book book: books) {
            if(book.getBookName().equals(name))
                return book;
        }
        return null;
    }
}
