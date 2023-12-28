package project.util;

import project.models.Book;

import java.util.Comparator;

public class OrderByBookComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getBookName().compareTo(o2.getBookName());
    }

}
