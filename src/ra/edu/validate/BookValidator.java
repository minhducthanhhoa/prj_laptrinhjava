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
    public static int validateUniqueId(Scanner scanner, List<Book> bookList) {
        int id;
        boolean isDuplicate;
        do {
            System.out.print("Nhập Book ID: ");
            String input = scanner.nextLine().trim();
            try {
                id = Integer.parseInt(input);
                final int finalId = id;
                isDuplicate = bookList.stream()
                        .anyMatch(book -> book.getBookId() == finalId);
                if (isDuplicate) {
                    System.out.println("Mã sách đã tồn tại. Vui lòng nhập mã khác!");
                }
            } catch (NumberFormatException e) {
                System.out.println("ID phải là số nguyên. Vui lòng nhập lại!");
                isDuplicate = true;
                id = -1;
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
            final String finalTitle = title;
            isDuplicate = bookList.stream()
                    .anyMatch(book -> book.getTitle().equalsIgnoreCase(finalTitle));
            if (isDuplicate) {
                System.out.println("Tên sách đã tồn tại. Vui lòng nhập tên khác!");
            }
        } while (isDuplicate);
        return title;
    }

    public static int validateInt(String fieldName, Scanner scanner, int min, int max) {
        int value;
        while (true) {
            try {
                System.out.printf("Nhập %s: ", fieldName);
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.printf("%s phải nằm trong khoảng từ %d đến %d.\n", fieldName, min, max);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.printf("%s phải là số nguyên. Vui lòng nhập lại!\n", fieldName);
            }
        }
        return value;
    }

}
