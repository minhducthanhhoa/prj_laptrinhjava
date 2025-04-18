package ra.edu.presentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ra.edu.business.service.BookService;
import ra.edu.business.model.Book;
import ra.edu.validate.BookValidator;

public class BookMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookService bookService = new BookService();


    public static void displayBookMenu(Scanner scanner) {
        List<Book> bookList = new ArrayList<>();
        while (true) {
            System.out.println("\n--- QUẢN LÝ SÁCH ---");
            System.out.println("1. Thêm sách");
            System.out.println("2. Sửa sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Sắp xếp sách");
            System.out.println("6. Hiển thị danh sách sách");
            System.out.println("0. Quay về menu chính");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Book newBook = inputBookInfo(bookList);
                    bookService.addBook(newBook);
                    break;
                case 2:
                    bookService.updateBook();
                    break;
                case 3:
                    bookService.deleteBookInteractive();
                    break;
                case 4:
                    bookService.searchBooksByTitle();
                    break;
                case 5:
                    bookService.sortBooksByTitle();
                    break;
                case 6:
                    bookService.displayAllBooks();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static Book inputBookInfo(List<Book> bookList) {
        Book book = new Book();

        System.out.println("=== Nhập thông tin sách mới ===");
        book.setTitle(BookValidator.validateUniqueTitle(scanner, bookList));

        book.setAuthor(BookValidator.validateLength("Tác giả", scanner, 3, 50));

        book.setCategoty(BookValidator.validateLength("Thể loại", scanner, 3, 30));

        book.setQuantityBook(BookValidator.validateInt("Số lượng", scanner, 0, Integer.MAX_VALUE));

        book.setPublishYear(BookValidator.validateInt("Năm xuất bản", scanner, 1900, java.time.LocalDate.now().getYear()));

        return book;
    }

}
