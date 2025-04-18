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

    public boolean updateBook(Book book) {
        String sql = "UPDATE book SET title = ?, author = ?, categoty = ?, quantity_book = ?, publish_year = ? WHERE book_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategoty());
            ps.setInt(4, book.getQuantityBook());
            ps.setInt(5, book.getPublishYear());
            ps.setInt(6, book.getBookId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM book WHERE book_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("categoty"),
                        rs.getInt("quantity_book"),
                        rs.getInt("publish_year")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteBookById(int bookId) {
        String sql = "DELETE FROM book WHERE book_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Book> searchBooksByTitle(String keyword) {
        List<Book> result = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE LOWER(title) LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("categoty"),
                        rs.getInt("quantity_book"),
                        rs.getInt("publish_year")
                );
                result.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
