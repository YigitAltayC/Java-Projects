package project;

import project.models.Book;
import project.util.OrderByBookComparator;

import java.util.Set;
import java.util.TreeSet;

public class Program {

    public static void main(String[] args) {

        Set<Book> bookSet = new TreeSet<Book>(new OrderByBookComparator());

        
    }
}
