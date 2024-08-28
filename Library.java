import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private static final String CSV_FILE = "library.csv";

    public Library() {
        loadBooks();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        saveBooks();
    }

    public Book searchByIsbn(String isbn) {
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public List<Book> listBooks() {
        return new ArrayList<>(books);
    }

    public void borrowBook(String isbn) {
        Book book = searchByIsbn(isbn);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            saveBooks();
        }
    }

    public void returnBook(String isbn) {
        Book book = searchByIsbn(isbn);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            saveBooks();
        }
    }

    private void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(Book.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Could not load books.");
        }
    }

    private void saveBooks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Book book : books) {
                writer.write(book.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save books.");
        }
    }
}


     