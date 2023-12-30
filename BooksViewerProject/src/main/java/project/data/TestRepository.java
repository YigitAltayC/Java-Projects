package project.data;

import project.models.Book;
import project.util.OrderByBookComparator;
import project.util.Utils;

import java.util.*;

public class TestRepository implements BooksRepository{

    private static int COUNT;
    private final List<Book> books;

    public TestRepository()
    {
        books = new ArrayList<>();
        books.add(
                new Book(
                        0,
                        "Harry Potter and the Deathly Hallows",
                        "J.K. Rowling",
                        759,
                        Utils.getCalendar(21, 7, 2007),
                        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1663805647i/136251.jpg",
                        "Harry has been burdened with a dark, dangerous and seemingly impossible task: that of locating and destroying Voldemort's remaining Horcruxes. Never has Harry felt so alone, or faced a future so full of shadows. But Harry must somehow find within himself the strength to complete the task he has been given. He must leave the warmth, safety and companionship of The Burrow and follow without fear or hesitation the inexorable path laid out for him...\n" +
                                "\n" +
                                "In this final, seventh installment of the Harry Potter series, J.K. Rowling unveils in spectacular fashion the answers to the many questions that have been so eagerly awaited."
                )
        );

        books.add(
                new Book(
                        1,
                        "The Hunger Games",
                        "Suzanne Collins",
                        374,
                        Utils.getCalendar(14, 7, 2008),
                        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1586722975i/2767052.jpg",
                        "Sixteen-year-old Katniss Everdeen, who lives alone with her mother and younger sister, regards it as a death sentence when she steps forward to take her sister's place in the Games. But Katniss has been close to dead before—and survival, for her, is second nature. Without really meaning to, she becomes a contender. But if she is to win, she will have to start making choices that weight survival against humanity and life against love."


                )
        );

        books.add(
                new Book(
                        2,
                        "1984",
                        "George Orwell",
                        328,
                        Utils.getCalendar(8, 8, 1949),
                        "https://i.dr.com.tr/cache/600x600-0/originals/0000000064038-1.jpg",
                        "The year 1984 has come and gone, but George Orwell's prophetic, nightmarish vision in 1949 of the world we were becoming is timelier than ever. 1984 is still the great modern classic of negative utopia —a startlingly original and haunting novel that creates an imaginary world that is completely convincing, from the first sentence to the last four words. No one can deny the novel's hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time."

                )
        );

        COUNT = books.size();
    }

    @Override
    public Book addBook(String bookName, String writer, int pageCount, Calendar releaseDate, String imageURL, String description) {


        Book newBook = new Book(COUNT, bookName, writer, pageCount, releaseDate, imageURL, description);
        books.add(newBook);
        COUNT++;

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
