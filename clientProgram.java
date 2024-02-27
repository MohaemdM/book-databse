/*
Mohamed Mustafa
2/25/2024
comp 167 section 3
Description: The clientProgram.java file serves as the main program for interacting with the book database. It begins by
creating a BookDatabase object and populating it by reading data from an external file (CSV). The program then presents
a menu-driven interface to the user, allowing operations such as adding, removing, and searching for books within the
database. User input is managed using a Scanner object, enabling interaction with the book database
through the command-line interface.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class clientProgram {
    public static void main(String[] args) {
        // Create a BookDatabase object
        BookDatabase db = new BookDatabase();

        // Read from "dataset.csv" and store the data in the BookDatabase object
        try {
            Scanner fileScanner = new Scanner(new File("dataset.csv"));
            // Skip the header line
            fileScanner.nextLine();
            // Loop through each line of the file
            while (fileScanner.hasNextLine()) {
                try {
                    // Split the line by comma
                    String[] data = fileScanner.nextLine().split(",");

                    // for self (Print the data for viewing all enter data to insure all inputted)
                    //System.out.println("Data: " + Arrays.toString(data));

                    if (data.length >= 8) {
                        // Create an Author object from the first and second column
                        Author author = new Author(data[0], data[1]);
                        // Create a Book object from the rest of the columns
                        int year = parseInteger(data[3]);
                        double rating = parseDouble(data[6]);
                        double price = parsePriceWithDollarSign(data[7]);

                        Book book = new Book(author, data[2], year, data[4], data[5], rating, price);
                        // Add the Book object to the BookDatabase object
                        db.addBook(book);
                    } else {
                        System.out.println("Invalid data format in the csv file, Skipping the line.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing numeric values in the csv file, Skipping the line.");
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return; // Exit the program if file not found
        }
        db.setBooks(db.getBooks());
        // Menu-driven user interface
        Scanner inputScanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add a Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. Search Books by Author");
            System.out.println("4. Search Books by Year Range");
            System.out.println("5. Search Books by Genre");
            System.out.println("6. Display All Books");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            // Ensure the user inputs a valid integer
            while (!inputScanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                inputScanner.next(); // consume the invalid input
            }


            choice = inputScanner.nextInt();
            inputScanner.nextLine(); // Consume the newline character after reading the choice

            switch (choice) {
                case 1:
                    // Add a Book
                    System.out.println("Enter details for the new book:");
                    Author newAuthor = getAuthorFromUser(inputScanner);
                    System.out.println("Author's Full Name: " + newAuthor.getFullName());
                    System.out.print("Enter title: ");
                    String newTitle = inputScanner.nextLine();
                    System.out.print("Enter year: ");
                    int newYear = inputScanner.nextInt();
                    inputScanner.nextLine(); // Consume the newline character
                    System.out.print("Enter publisher: ");
                    String newPublisher = inputScanner.nextLine();
                    System.out.print("Enter genre: ");
                    String newGenre = inputScanner.nextLine();
                    System.out.print("Enter rating: ");
                    double newRating = inputScanner.nextDouble();
                    System.out.print("Enter price: ");
                    String priceInput = inputScanner.next();
                    double newPrice = parsePriceWithDollarSign(priceInput);
                    Book newBook = new Book(newAuthor, newTitle, newYear, newPublisher, newGenre, newRating, newPrice);
                    db.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    // Remove a Book
                    System.out.println("Enter details for the book to remove:");
                    Author removeAuthor = getAuthorFromUser(inputScanner);
                    System.out.println("Is Full Name Empty: " + removeAuthor.isFullNameEmpty());
                    // You can also use removeAuthor.getFirstName() and removeAuthor.getLastName() as needed
                    System.out.print("Enter title: ");
                    String removeTitle = inputScanner.nextLine().trim(); // Trim leading/trailing whitespaces
                    System.out.print("Enter year: ");
                    int removeYear = inputScanner.nextInt();
                    inputScanner.nextLine(); // Consume the newline character after reading the year
                    Book removeBook = new Book(removeAuthor, removeTitle, removeYear, "", "", 0.0, 0.0);
                    db.removeBook(removeBook);
                    break;
                case 3:
                    // Search Books by Author
                    Author searchAuthor = getAuthorFromUser(inputScanner);
                    ArrayList<Book> booksByAuthor = db.search(searchAuthor);
                    displaySearchResults("Books by " + searchAuthor, booksByAuthor);
                    break;
                case 4:
                    // Search Books by Year Range
                    System.out.print("Enter start year: ");
                    int startYear = inputScanner.nextInt();
                    System.out.print("Enter end year: ");
                    int endYear = inputScanner.nextInt();
                    ArrayList<Book> booksByYear = db.search(startYear, endYear);
                    displaySearchResults("Books published between " + startYear + " and " + endYear, booksByYear);
                    break;
                case 5:
                    // Search Books by Genre
                    System.out.print("Enter genre: ");
                    String searchGenre = inputScanner.nextLine();
                    ArrayList<Book> booksByGenre = db.search(searchGenre);
                    displaySearchResults("Books in the genre " + searchGenre, booksByGenre);
                    break;
                case 6:
                    // Display All Books
                    ArrayList<Book> allBooks = db.getBooks();
                    displaySearchResults("All Books", allBooks);
                    break;
                case 0:
                    // Exit the program
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        inputScanner.close();
    }


    // Helper method to get author details from the user
    private static Author getAuthorFromUser(Scanner scanner) {
        System.out.print("Enter author's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter author's last name: ");
        String lastName = scanner.nextLine();
        return new Author(firstName, lastName);
    }

    // Helper method to display search results
    private static void displaySearchResults(String message, ArrayList<Book> books) {
        System.out.println(message + ":");
        for (Book b : books) {
            System.out.println(b);
        }
    }
    // Helper method to Extract the numeric part after removing the dollar sign
    private static double parsePriceWithDollarSign(String input) {
        //
        String numericPart = input.replaceAll("[^\\d.]", "");
        try {
            return Double.parseDouble(numericPart);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format. Please enter a valid numeric value.");
            return 0.0;  // Or handle the error in a way appropriate for your application
        }
    }
    //Helper method for avoiding data entry missed match
    private static int parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer value: " + input);
            return 0;
        }
    }


    // Helper method sting to double with error checking
    private static double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing double value: " + input);
            return 0.0;
        }
    }



}