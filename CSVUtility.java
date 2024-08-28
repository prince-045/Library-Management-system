import java.io.*;
import java.util.*;

public class CSVUtility {

    public static List<Book> readBooksFromCSV(String filePath) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(Book.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Could not load books from CSV.");
        }
        return books;
    }

    public static void writeBooksToCSV(String filePath, List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(book.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save books to CSV.");
        }
    }
}
