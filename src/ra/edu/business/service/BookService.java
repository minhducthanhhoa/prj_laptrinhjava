package ra.edu.business.service;

import ra.edu.business.dao.BookDao;
import ra.edu.business.model.Book;

import java.util.List;
public class BookService {
    private final BookDao bookDao = new BookDao();

    public void displayAllBooks() {
        List<Book> books = bookDao.getAllBooks();
        books.forEach(book -> {
            System.out.printf("ID: %d | Title: %s | Author: %s | Category: %s | Qty: %d | Year: %d\n",
                    book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategoty(),
                    book.getQuantityBook(), book.getPublishYear());
        });
    }

    public void addBook(Book book) {
        if (bookDao.addBook(book)) {
            System.out.println("Thêm sách thành công!");
        } else {
            System.out.println("Thêm sách thất bại!");
        }
    }

}
