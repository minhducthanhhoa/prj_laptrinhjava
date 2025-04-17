package ra.edu.business.dao;
import ra.edu.business.model.Book;
import ra.edu.business.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookDao {
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String query = "SELECT * FROM book";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("categoty"),
                        rs.getInt("quantity_book"),
                        rs.getInt("publish_year")
                );
                list.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addBook(Book book) {
        String sql = "INSERT INTO book (title, author, categoty, quantity_book, publish_year) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategoty());
            ps.setInt(4, book.getQuantityBook());
            ps.setInt(5, book.getPublishYear());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
