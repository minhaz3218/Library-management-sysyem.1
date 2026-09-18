import java.util.List;
import java.util.Scanner;

/**
 * Main.java
 * Entry point of the Smart Library Book Manager application.
 * Displays the menu, reads user input, and delegates all real
 * work to LibraryManager. Handles invalid input gracefully so
 * the program never crashes on bad input.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static LibraryManager manager = new LibraryManager();

    public static void main(String[] args) {
        System.out.println("Welcome to Smart Library Book Manager!");
        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchMenu();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    manager.printStatistics();
                    break;
                case 7:
                    deleteBook();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using Smart Library Book Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("===== SMART LIBRARY BOOK MANAGER =====");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Books");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Library Statistics");
        System.out.println("7. Delete Book");
        System.out.println("8. Exit");
    }

    private static void addBook() {
        System.out.println("----- ADD BOOK -----");
        int id = readInt("Enter Book ID: ");
        String title = readLine("Enter Title: ");
        String author = readLine("Enter Author: ");
        String category = readLine("Enter Category: ");
        int year = readInt("Enter Publication Year: ");

        try {
            manager.addBook(id, title, author, category, year);
            System.out.println("Book added successfully.");
        } catch (InvalidBookException e) {
            System.out.println("Could not add book: " + e.getMessage());
        }
    }

    private static void viewAllBooks() {
        System.out.println("----- ALL BOOKS -----");
        List<Book> books = manager.getAllBooks();
        printBookTable(books);
    }

    private static void searchMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("===== SEARCH BOOKS =====");
            System.out.println("1. Search by Title");
            System.out.println("2. Search by Author");
            System.out.println("3. Search by Category");
            System.out.println("4. Back");
            int choice = readInt("Enter your choice: ");

            List<Book> result;
            switch (choice) {
                case 1:
                    result = manager.searchByTitle(readLine("Enter title keyword: "));
                    printBookTable(result);
                    break;
                case 2:
                    result = manager.searchByAuthor(readLine("Enter author keyword: "));
                    printBookTable(result);
                    break;
                case 3:
                    result = manager.searchByCategory(readLine("Enter category keyword: "));
                    printBookTable(result);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void issueBook() {
        System.out.println("----- ISSUE BOOK -----");
        int id = readInt("Enter Book ID to issue: ");
        System.out.println(manager.issueBook(id));
    }

    private static void returnBook() {
        System.out.println("----- RETURN BOOK -----");
        int id = readInt("Enter Book ID to return: ");
        System.out.println(manager.returnBook(id));
    }

    private static void deleteBook() {
        System.out.println("----- DELETE BOOK -----");
        int id = readInt("Enter Book ID to delete: ");
        System.out.println(manager.deleteBook(id));
    }

    /**
     * Prints a list of books as a formatted table.
     * Shows a friendly message if the list is empty.
     */
    private static void printBookTable(List<Book> books) {
        if (books == null || books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        System.out.printf("%-6s %-25s %-20s %-15s %-6s %-10s%n",
                "ID", "Title", "Author", "Category", "Year", "Status");
        System.out.println("----------------------------------------------------------------------------");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    /**
     * Safely reads an integer from the user.
     * Keeps asking until valid input is given, so the program never crashes.
     */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    /**
     * Reads a line of text input from the user.
     */
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
