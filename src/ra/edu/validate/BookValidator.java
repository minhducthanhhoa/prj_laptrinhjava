package ra.edu.validate;
import ra.edu.business.model.Book;

import java.util.List;
import java.util.Scanner;
public class BookValidator {
    public static String validateNotEmpty(String fieldName, Scanner scanner) {
        String value;
        do {
            System.out.printf("Nhập %s: ", fieldName);
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println(fieldName + " không được để trống. Vui lòng nhập lại!");
            }
        } while (value.isEmpty());
        return value;
    }

    // Kiểm tra độ dài hợp lệ
    public static String validateLength(String fieldName, Scanner scanner, int minLength, int maxLength) {
        String value;
        do {
            value = validateNotEmpty(fieldName, scanner);
            if (value.length() < minLength || value.length() > maxLength) {
                System.out.printf("%s phải có độ dài từ %d đến %d ký tự. Vui lòng nhập lại!\n", fieldName, minLength, maxLength);
            }
        } while (value.length() < minLength || value.length() > maxLength);
        return value;
    }

    // Kiểm tra ID không trùng
    public static String validateUniqueId(Scanner scanner, List<Book> bookList) {
        String id;
        boolean isDuplicate;
        do {
            id = validateNotEmpty("Book ID", scanner);
            isDuplicate = bookList.stream().anyMatch(book -> book.getBookId().equalsIgnoreCase(id));
            if (isDuplicate) {
                System.out.println("Mã sách đã tồn tại. Vui lòng nhập mã khác!");
            }
        } while (isDuplicate);
        return id;
    }

    // Kiểm tra tên sách không trùng
    public static String validateUniqueTitle(Scanner scanner, List<Book> bookList) {
        String title;
        boolean isDuplicate;
        do {
            title = validateLength("Tên sách", scanner, 5, 100);
            isDuplicate = bookList.stream().anyMatch(book -> book.getTitle().equalsIgnoreCase(title));
            if (isDuplicate) {
                System.out.println("Tên sách đã tồn tại. Vui lòng nhập tên khác!");
            }
        } while (isDuplicate);
        return title;
    }
}
