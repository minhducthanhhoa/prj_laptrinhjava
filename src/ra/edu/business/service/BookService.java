package ra.edu.business.service;

import ra.edu.business.dao.BookDao;
import ra.edu.business.model.Book;

import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import ra.edu.validate.BookValidator;

public class BookService {
    private final BookDao bookDao = new BookDao();
    private final Scanner scanner = new Scanner(System.in);

    public void displayAllBooks() {
        List<Book> books = bookDao.getAllBooks();

        System.out.printf("%-5s | %-30s | %-20s | %-15s | %-5s | %-10s\n",
                "ID", "Title", "Author", "Category", "Qty", "Year");
        System.out.println("----------------------------------------------------------------------------------------------");

        books.forEach(book -> {
            System.out.printf("%-5d | %-30s | %-20s | %-15s | %-5d | %-10d\n",
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategoty(),
                    book.getQuantityBook(),
                    book.getPublishYear());
        });
    }


    public void addBook(Book book) {
        if (bookDao.addBook(book)) {
            System.out.println("Thêm sách thành công!");
        } else {
            System.out.println("Thêm sách thất bại!");
        }
    }

    public void updateBook() {
        List<Book> bookList = bookDao.getAllBooks();

        System.out.println("=== CẬP NHẬT THÔNG TIN SÁCH ===");
        System.out.print("Nhập ID sách cần cập nhật: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ. Vui lòng nhập số nguyên!");
            return;
        }

        Book existingBook = bookDao.getBookById(id);
        if (existingBook == null) {
            System.out.println("Không tìm thấy sách với ID: " + id);
            return;
        }

        System.out.println("Thông tin sách hiện tại:");
        System.out.printf("Tiêu đề: %s | Tác giả: %s | Thể loại: %s | Số lượng: %d | Năm XB: %d\n",
                existingBook.getTitle(), existingBook.getAuthor(), existingBook.getCategoty(),
                existingBook.getQuantityBook(), existingBook.getPublishYear());

        System.out.println("Nhập thông tin mới:");

        String newTitle = BookValidator.validateUniqueTitle(scanner, bookList);
        String newAuthor = BookValidator.validateLength("Tác giả", scanner, 3, 50);
        String newCategory = BookValidator.validateLength("Thể loại", scanner, 3, 30);
        int newQty = BookValidator.validateInt("Số lượng", scanner, 0, Integer.MAX_VALUE);
        int newYear = BookValidator.validateInt("Năm xuất bản", scanner, 1900, java.time.LocalDate.now().getYear());

        existingBook.setTitle(newTitle);
        existingBook.setAuthor(newAuthor);
        existingBook.setCategoty(newCategory);
        existingBook.setQuantityBook(newQty);
        existingBook.setPublishYear(newYear);

        boolean result = bookDao.updateBook(existingBook);
        if (result) {
            System.out.println("Cập nhật sách thành công!");
        } else {
            System.out.println("Cập nhật sách thất bại!");
        }
    }

    public void deleteBookInteractive() {
        System.out.print("Nhập ID sách muốn xóa: ");
        int bookId;
        try {
            bookId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số nguyên!");
            return;
        }

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Không tìm thấy sách với ID: " + bookId);
            return;
        }

        System.out.println("Tìm thấy sách: " + book.getTitle());
        System.out.print("Bạn có chắc muốn xóa sách này không? (Y/N): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("Y")) {
            boolean deleted = bookDao.deleteBookById(bookId);
            if (deleted) {
                System.out.println("Đã xóa sách thành công!");
            } else {
                System.out.println("Xóa sách thất bại!");
            }
        } else {
            System.out.println("Hủy thao tác xóa sách.");
        }
    }

    public Book findBookById(int bookId) {
        List<Book> books = bookDao.getAllBooks();
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                return b;
            }
        }
        return null;
    }

    public void searchBooksByTitle() {
        System.out.print("Nhập từ khóa tiêu đề sách cần tìm: ");
        String keyword = scanner.nextLine().trim();

        List<Book> results = bookDao.searchBooksByTitle(keyword);

        if (results.isEmpty()) {
            System.out.println("Không tìm thấy sách nào phù hợp với từ khóa \"" + keyword + "\".");
        } else {
            System.out.println("Tìm thấy " + results.size() + " sách:");
            System.out.printf("%-5s | %-30s | %-20s | %-15s | %-8s | %-10s\n",
                    "ID", "Tiêu đề", "Tác giả", "Thể loại", "Số lượng", "Năm XB");
            System.out.println("--------------------------------------------------------------------------------------------");

            for (Book book : results) {
                System.out.printf("%-5d | %-30s | %-20s | %-15s | %-8d | %-10d\n",
                        book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategoty(),
                        book.getQuantityBook(), book.getPublishYear());
            }
        }
    }

    public void sortBooksByTitle() {
        List<Book> books = bookDao.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("Không có sách nào trong danh sách để sắp xếp.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int choice;

        // Hiển thị menu con
        do {
            System.out.println("\nCHỌN KIỂU SẮP XẾP:");
            System.out.println("1. Tăng dần (A → Z)");
            System.out.println("2. Giảm dần (Z → A)");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        books.sort(Comparator.comparing(Book::getTitle));
                        displayBooks(books);
                        break;
                    case 2:
                        books.sort(Comparator.comparing(Book::getTitle).reversed());
                        displayBooks(books);
                        break;
                    case 0:
                        System.out.println("Quay lại menu chính.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ.");
                choice = -1;
            }

        } while (choice != 0);
    }

    private void displayBooks(List<Book> books) {
        System.out.println("Danh sách sách sau khi sắp xếp:");
        System.out.printf("%-5s | %-30s | %-20s | %-15s | %-8s | %-10s\n",
                "ID", "Tiêu đề", "Tác giả", "Thể loại", "Số lượng", "Năm XB");
        System.out.println("--------------------------------------------------------------------------------------------");

        for (Book book : books) {
            System.out.printf("%-5d | %-30s | %-20s | %-15s | %-8d | %-10d\n",
                    book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategoty(),
                    book.getQuantityBook(), book.getPublishYear());
        }
    }


}
