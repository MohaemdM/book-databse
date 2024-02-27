/*
Mohamed Mustafa
2/25/2024
comp 167 section 3
Description:The Author.java file serves the purpose of defining the Author class, encapsulating the attributes and
behaviors of an author in the context of a book database. It contains fields for storing the first and last names of
the author, along with default and parameterized constructors for creating instances of the Author class.
Accessor (getter) methods provide access to the stored names, while mutator (setter) methods facilitate
updating the names. Additionally, the toString method is implemented to offer a string
representation of the Author object.
 */


public class Author {
    private String firstName;
    private String lastName;

    public Author() {
        this.firstName = "";
        this.lastName = "";
    }

    // Parameterized constructor sets the first and last name based on input values
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Returns the full name by concatenating the first and last name
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Checks if both first and last name are empty
    public boolean isFullNameEmpty() {
        return firstName.isEmpty() && lastName.isEmpty();
    }

    // Overrides the equals method to compare Author objects based on first and last name
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Author author = (Author) obj;
        return firstName.equalsIgnoreCase(author.firstName) &&
                lastName.equalsIgnoreCase(author.lastName);
    }

    // Overrides the toString method to provide a formatted string representation of the Author
    @Override
    public String toString() {
        return "Author: " +
                "First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'';
    }
}