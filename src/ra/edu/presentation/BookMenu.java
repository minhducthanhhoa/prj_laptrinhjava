package ra.edu.presentation;
import java.util.Scanner;
import ra.edu.business.service.BookService;
import ra.edu.business.model.Book;

public class BookMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookService bookService = new BookService();
    public static void displayBookMenu(Scanner scanner) {
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
                    Book newBook = inputBookInfo();
                    bookService.addBook(newBook);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

    private static Book inputBookInfo() {
        Book book = new Book();
        System.out.print("Tiêu đề: ");
        book.setTitle(scanner.nextLine());
        System.out.print("Tác giả: ");
        book.setAuthor(scanner.nextLine());
        System.out.print("Thể loại: ");
        book.setCategoty(scanner.nextLine());
        System.out.print("Số lượng: ");
        book.setQuantityBook(Integer.parseInt(scanner.nextLine()));
        System.out.print("Năm xuất bản: ");
        book.setPublishYear(Integer.parseInt(scanner.nextLine()));
        return book;
    }
}
