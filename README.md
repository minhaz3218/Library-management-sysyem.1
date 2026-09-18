# Smart Library Book Manager

## Project Overview

Smart Library Book Manager is a console-based (command-line) Java application
that helps a college librarian manage a small collection of books. It supports
adding, viewing, searching, issuing, returning, and deleting books, along with
displaying library-wide statistics. All data is stored locally in a plain CSV
file — no database or external library is required.

This project is built entirely using core Java (standard library only) and is
designed to be simple enough for a second-year BTech student to understand,
explain, and extend.

## Problem Statement

Small college libraries or departmental reading rooms often rely on manual
registers to track which books are available and which are issued to
students. This is slow, error-prone, and hard to search. Smart Library Book
Manager solves this by providing a simple digital system that:

* Keeps track of every book's availability status.
* Lets the librarian search books instantly by title, author, or category.
* Prevents common mistakes (like issuing an already-issued book or deleting a
book that is currently issued).
* Persists all data automatically to a file, so nothing is lost when the
program closes.

## Features

1. Add a new book (with validation).
2. View all books in a clean tabular format.
3. Search books by title, author, or category.
4. Issue a book (marks it as unavailable).
5. Return a book (marks it as available again).
6. View library statistics (total, available, issued, category-wise counts).
7. Delete a book (issued books cannot be deleted).
8. Automatic saving of data to a CSV file after every change.
9. Automatic loading of existing data when the program starts.
10. Automatic creation of the data file/folder if they don't already exist.

## Java Concepts Demonstrated

* **Classes and Objects** – `Book`, `LibraryManager`, `FileHandler`, etc.
* **Encapsulation** – private fields in `Book` with public getters/setters.
* **Constructors** – used to initialize `Book` objects.
* **Methods** – for every operation (add, search, issue, return, delete).
* **Collections (ArrayList)** – `LibraryManager` stores books in an `ArrayList<Book>`.
* **File Handling** – `FileHandler` reads/writes the CSV file using
`BufferedReader` and `BufferedWriter`.
* **Exception Handling** – `try/catch` blocks around file I/O and number
parsing; a custom `InvalidBookException` for invalid book data.
* **Loops and Conditional Statements** – used throughout for menus, searching,
and validation.
* **String Handling** – parsing CSV lines, case-insensitive keyword search.
* **Basic OOP Design** – each class has a single, clear responsibility.
* **Searching and Filtering** – linear search implementations for title,
author, and category.
* **Basic Data Processing** – statistics calculation using a `Map` for
category-wise counts.

## Project Structure

```
SmartLibraryBookManager/
├── src/
│   ├── Main.java
│   ├── Book.java
│   ├── LibraryManager.java
│   ├── FileHandler.java
│   └── InvalidBookException.java
├── data/
│   └── books.csv
├── README.md
└── .gitignore
```

## Prerequisites

* Java Development Kit (JDK) 8 or higher installed.
* A terminal / Command Prompt / PowerShell.
* No external libraries, database, or internet connection required.

## Exact Windows Terminal Commands to Compile and Run

Open Command Prompt (or PowerShell) inside the project's parent folder, then:

```
cd SmartLibraryBookManager
javac -d out src\\\*.java
java -cp out Main
```

* `javac -d out src\\\*.java` compiles all Java files from `src\\` and places the
compiled `.class` files into an `out\\` folder.
* `java -cp out Main` runs the compiled program using `out\\` as the classpath.

To run it again later (after the first compile), you only need:

```
java -cp out Main
```

## How Data Storage Works

* All book records are stored in `data\\books.csv`.
* When the program starts, `FileHandler` checks if the `data` folder and
`books.csv` file exist. If not, it creates them automatically — so the
program works even on a completely fresh checkout of the project.
* Every time a book is added, issued, returned, or deleted, the entire book
list is re-saved to `books.csv`, keeping the file always in sync with the
in-memory data.

## CSV Format

Each line in `books.csv` represents one book:

```
bookId,title,author,category,publicationYear,available
```

Example:

```
101,Java Programming,Herbert Schildt,Programming,2022,true
102,Data Structures,Mark Allen Weiss,Computer Science,2021,true
103,Computer Networks,Andrew Tanenbaum,Networking,2020,false
```

Here, `available` is `true` if the book is on the shelf, and `false` if it is
currently issued to someone.

## Example Usage

1. Run the program — it loads any existing books from `books.csv`.
2. Choose option `1` to add a new book (enter ID, title, author, category,
year).
3. Choose option `2` to view all books in a table.
4. Choose option `3` to search by title/author/category.
5. Choose option `4` to issue a book by its ID.
6. Choose option `5` to return a book by its ID.
7. Choose option `6` to see library statistics.
8. Choose option `7` to delete a book by its ID (only if it is not issued).
9. Choose option `8` to exit — all data is already saved after each action.

## Validation and Exception Handling

* Book ID must be unique — duplicate IDs are rejected.
* Title, author, and category cannot be empty.
* Publication year must be a positive, reasonable number.
* Issuing a non-existent book shows a clear error message instead of
crashing.
* Issuing an already-issued book is blocked with a message.
* Returning a book that is not currently issued is blocked with a message.
* Deleting an issued book is blocked until it is returned.
* Any invalid menu input (letters instead of numbers, out-of-range choices)
is handled gracefully and the program keeps running.
* File read/write errors are caught and reported without stopping the
program.
* A custom `InvalidBookException` is thrown for invalid book data during
the "Add Book" operation, keeping validation logic clean and readable.

## Future Enhancements

* Add due dates and fine calculation for late returns.
* Support multiple copies of the same book.
* Add sorting options (by year, title, or author).
* Export statistics to a separate report file.
* Add simple user authentication for librarian access.

