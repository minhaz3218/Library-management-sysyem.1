import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandler.java
 * Responsible for all file input/output operations.
 * Saves and loads book records from a local CSV file.
 */
public class FileHandler {

    private static final String DATA_FOLDER = "data";
    private static final String DATA_FILE = "data/books.csv";

    /**
     * Makes sure the data folder and CSV file exist.
     * Creates them if they are missing.
     */
    public void ensureFileExists() {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(DATA_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating data file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads all books from the CSV file into a list.
     * Returns an empty list if the file is empty or does not exist yet.
     */
    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        ensureFileExists();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    // Skip malformed lines instead of crashing
                    continue;
                }
                try {
                    int bookId = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    String category = parts[3].trim();
                    int year = Integer.parseInt(parts[4].trim());
                    boolean available = Boolean.parseBoolean(parts[5].trim());

                    books.add(new Book(bookId, title, author, category, year, available));
                } catch (NumberFormatException e) {
                    // Skip line if numeric fields are corrupted
                    System.out.println("Skipping corrupted record: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading data file: " + e.getMessage());
        }

        return books;
    }

    /**
     * Saves the entire list of books to the CSV file, overwriting old content.
     */
    public void saveBooks(List<Book> books) {
        ensureFileExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Book book : books) {
                writer.write(book.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data file: " + e.getMessage());
        }
    }
}
