import java.util.ArrayList;
import java.util.List;

/**
 * LibraryManager.java
 * Contains the core business logic of the library system:
 * adding, viewing, searching, issuing, returning, deleting books,
 * and calculating statistics. Keeps the in-memory list in sync
 * with the CSV file through FileHandler.
 */
public class LibraryManager {

    private List<Book> books;
    private FileHandler fileHandler;

    public LibraryManager() {
        fileHandler = new FileHandler();
        books = new ArrayList<>(fileHandler.loadBooks());
    }

    /**
     * Adds a new book after validating all fields.
     * Throws InvalidBookException if any validation rule fails.
     */
    public void addBook(int bookId, String title, String author, String category,
                         int publicationYear) throws InvalidBookException {

        if (title == null || title.trim().isEmpty()) {
            throw new InvalidBookException("Title cannot be empty.");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new InvalidBookException("Author cannot be empty.");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidBookException("Category cannot be empty.");
        }
        if (publicationYear <= 0 || publicationYear > 2100) {
            throw new InvalidBookException("Publication year is not valid.");
        }
        if (isBookIdTaken(bookId)) {
            throw new InvalidBookException("Book ID " + bookId + " already exists.");
        }

        Book newBook = new Book(bookId, title.trim(), author.trim(), category.trim(),
                publicationYear, true);
        books.add(newBook);
        fileHandler.saveBooks(books);
    }

    private boolean isBookIdTaken(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return true;
            }
        }
        return false;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    /**
     * Searches books by title (partial, case-insensitive match).
     */
    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Searches books by author (partial, case-insensitive match).
     */
    public List<Book> searchByAuthor(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Searches books by category (partial, case-insensitive match).
     */
    public List<Book> searchByCategory(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Marks a book as issued. Returns a status message describing the outcome.
     */
    public String issueBook(int bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            return "No book found with ID " + bookId + ".";
        }
        if (!book.isAvailable()) {
            return "Book \"" + book.getTitle() + "\" is already issued.";
        }
        book.setAvailable(false);
        fileHandler.saveBooks(books);
        return "Book \"" + book.getTitle() + "\" has been issued successfully.";
    }

    /**
     * Marks a book as returned (available). Returns a status message.
     */
    public String returnBook(int bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            return "No book found with ID " + bookId + ".";
        }
        if (book.isAvailable()) {
            return "Book \"" + book.getTitle() + "\" was not issued, so it cannot be returned.";
        }
        book.setAvailable(true);
        fileHandler.saveBooks(books);
        return "Book \"" + book.getTitle() + "\" has been returned successfully.";
    }

    /**
     * Deletes a book by ID. Issued books cannot be deleted.
     */
    public String deleteBook(int bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            return "No book found with ID " + bookId + ".";
        }
        if (!book.isAvailable()) {
            return "Book \"" + book.getTitle() + "\" is currently issued and cannot be deleted.";
        }
        books.remove(book);
        fileHandler.saveBooks(books);
        return "Book \"" + book.getTitle() + "\" has been deleted successfully.";
    }

    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    /**
     * Prints library-wide statistics: totals, availability counts,
     * and a category-wise breakdown.
     */
    public void printStatistics() {
        int total = books.size();
        int available = 0;
        int issued = 0;
        java.util.Map<String, Integer> categoryCount = new java.util.LinkedHashMap<>();

        for (Book book : books) {
            if (book.isAvailable()) {
                available++;
            } else {
                issued++;
            }
            categoryCount.merge(book.getCategory(), 1, Integer::sum);
        }

        System.out.println("===== LIBRARY STATISTICS =====");
        System.out.println("Total Books    : " + total);
        System.out.println("Available Books: " + available);
        System.out.println("Issued Books   : " + issued);
        System.out.println("-------------------------------");
        System.out.println("Category-wise Book Count:");
        if (categoryCount.isEmpty()) {
            System.out.println("  No books in the library yet.");
        } else {
            for (String category : categoryCount.keySet()) {
                System.out.println("  " + category + " : " + categoryCount.get(category));
            }
        }
    }
}
