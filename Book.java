/*
Mohamed Mustafa
2/25/2024
comp 167 section 3
Description: The Book.java file represents the Book class, designed to model individual books within the book database.
It includes fields for an Author object, title, publication year, publisher, genre, rating, and price.
Constructors, including both default and parameterized versions, enable the creation of Book instances.
Accessor methods are provided for retrieving each attribute's value, and a toString method generates a
string representation of the Book object. Depending on the design, mutator methods may also be implemented
for updating the book's attributes.
 */

public class Book {
    private Author author;
    private String title;
    private int year;
    private String publisher;
    private String genre;
    private double rating;
    private double price;

    // Default constructor initializes the first and last name to empty strings

    public Book() {
        author = new Author();
        title = "";
        year = 0;
        publisher = "";
        genre = "";
        rating = 0.0;
        price = 0.0;
    }

    // Parameterized constructor
    public Book(Author author, String title, int year, String genre,String publisher, double rating, double price) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.genre = genre;
        this.rating = rating;
        this.price = price;
    }

    // Getters
    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

/*    additions to self
    public String getPublisher() {
        return publisher;
    }
    public double getRating() {
        return rating;
    }
    public double getPrice() {
        return price;
    }
    // Setters
    public void setAuthor(Author author) {
        this.author = author;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public void setPrice(double price) {
        this.price = price;
    }
*/
    // to sting output override
    @Override
    public String toString() {
        return "Book: [ " +
                 author +
                ", title: '" + title + '\'' +
                ", year: " + year +
                ", publisher: '" + publisher + '\'' +
                ", genre: '" + genre + '\'' +
                ", rating: " + rating +
                ", price: $" + price + ']';
    }
}

