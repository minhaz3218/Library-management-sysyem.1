/**
 * Book.java
 * Represents a single book record in the library system.
 * Demonstrates encapsulation using private fields with public getters/setters.
 */
public class Book {

    private int bookId;
    private String title;
    private String author;
    private String category;
    private int publicationYear;
    private boolean available;

    // Constructor to create a new Book object
    public Book(int bookId, String title, String author, String category,
                int publicationYear, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publicationYear = publicationYear;
        this.available = available;
    }

    // ----- Getters -----
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return available;
    }

    // ----- Setters -----
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Converts this Book object into a single CSV line.
     * Format: bookId,title,author,category,publicationYear,available
     */
    public String toCSV() {
        return bookId + "," + title + "," + author + "," + category + ","
                + publicationYear + "," + available;
    }

    /**
     * Returns a nicely formatted row for table-style display in the console.
     */
    @Override
    public String toString() {
        String status = available ? "Available" : "Issued";
        return String.format("%-6d %-25s %-20s %-15s %-6d %-10s",
                bookId, title, author, category, publicationYear, status);
    }
}
