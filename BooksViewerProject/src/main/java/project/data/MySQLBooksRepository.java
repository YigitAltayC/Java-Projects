package project.data;

import project.models.Book;
import project.util.BookException;
import project.util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLBooksRepository implements BooksRepository{

    private static final Logger LOGGER = Logger.getLogger(MySQLBooksRepository.class.getName());

    private static final String SQL_INSERT_BOOK = "INSERT INTO books (name, writer, page_count, release_date, image_url, description) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BOOKS = "SELECT * FROM books";

    @Override
    public Book addBook(String bookName, String writer, int pageCount, Calendar releaseDate, String imageURL, String description) {

        try (
                Connection con = MySQLConnection.create();
                PreparedStatement stmt = con.prepareStatement(SQL_INSERT_BOOK, Statement.RETURN_GENERATED_KEYS)
        ) {

            stmt.setString(1, bookName);
            stmt.setString(2, writer);
            stmt.setInt(3, pageCount);
            stmt.setDate(4, new java.sql.Date(releaseDate.getTimeInMillis()));
            stmt.setString(5, imageURL);
            stmt.setString(6, description);

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                keys.next();
                int ID = keys.getInt(1);

                return new Book(
                        ID, bookName, writer, pageCount, releaseDate, imageURL, description
                );
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to add patient to DB.", ex);
            throw new BookException("Unable to add patient.");
        }
    }

    @Override
    public List<Book> getBooks() {
        try (
                Connection con = MySQLConnection.create();
                PreparedStatement stmt = con.prepareStatement(SQL_SELECT_BOOKS);
                ResultSet rs = stmt.executeQuery()
        ) {

            List<Book> books = new ArrayList<>();

            while (rs.next()) {
                books.add(rs2Book(rs));
            }

            return books;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve patients from DB.", ex);
            throw new BookException("Unable to retrieve patients");
        }
    }

    @Override
    public Book getBook(String name) {

        List<Book> books = getBooks();
        for (Book book: books) {
            if(book.getBookName().equals(name))
                return book;
        }

        return null;
    }


    private Book rs2Book(ResultSet rs) throws SQLException {

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(rs.getDate("release_date").getTime());


        return new Book(
                rs.getInt("ID"), rs.getString("name"),
                rs.getString("writer"), rs.getInt("page_count"), calendar,
                rs.getString("image_url"), rs.getString("description")
        );
    }
}
