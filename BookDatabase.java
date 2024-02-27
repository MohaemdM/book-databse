/*
Mohamed Mustafa
2/25/2024
comp 167 section 3
Description: In the BookDatabase.java file, the BookDatabase class is defined to manage a collection of books and offer
various operations for manipulation and search. The class features an ArrayList<Book> to store books, and it includes
default and parameterized constructors for creating instances of the database. Methods such as addBook and removeBook
handle the addition and removal of books, while a private findBook method aids in locating specific books. Search
functionality is provided through methods like search for authors, year ranges, and genres. Getter and setter methods
facilitate access to and modification of the book collection, and the toString method offers a string representation
of the BookDatabase object.
 */

import java.util.ArrayList;

public class BookDatabase {
    private ArrayList<Book> books;
    // Default constructor initializes an empty ArrayList of books
    public BookDatabase() {
        books = new ArrayList<>();
    }
    // Constructor that takes an initial list of books
    public BookDatabase(ArrayList<Book> initialBooks) {
        books = new ArrayList<>(initialBooks);
    }
    // Adds a book to the database
    public void addBook(Book book) {
        books.add(book);
    }
    // Removes a book from the database based on author, title, and year
    public void removeBook(Book book) {
        Book bookToRemove = findBook(book.getAuthor(), book.getTitle(), book.getYear());

        if (bookToRemove != null && books.remove(bookToRemove)) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found in the database. No action taken.");
        }
    }
    // Helper method to find a book in the database
    private Book findBook(Author author, String title, int year) {
        for (Book book : books) {
            if (book.getAuthor().equals(author) && book.getTitle().equals(title) && book.getYear() == year) {
                return book;
            }
        }
        return null;
    }
    // Searches for books by author and returns a list
    public ArrayList<Book> search(Author author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }
    // Searches for books within a specified year range and returns a list
    public ArrayList<Book> search(int startYear, int endYear) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() >= startYear && book.getYear() <= endYear) {
                result.add(book);
            }
        }
        return result;
    }
    // Searches for books by genre and returns a list
    public ArrayList<Book> search(String genre) {
        ArrayList<Book> result = new ArrayList<>();
        String trimmedGenre = genre.trim(); // Trim leading and trailing whitespaces
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(trimmedGenre)) {
                result.add(book);
            }
        }
        return result;
    }
    // Returns a copy of the list of book
    public ArrayList<Book> getBooks() {
        return new ArrayList<>(books);
    }
    // Sets the list of books in the database
    public void setBooks(ArrayList<Book> initialBooks) {
        books = new ArrayList<>(initialBooks);
    }

    // Overrides the toString method to provide a formatted string representation of the BookDatabase
    @Override
    public String toString() {
        return "BookDatabase{" +
                "books=" + books +
                '}';
    }
}
