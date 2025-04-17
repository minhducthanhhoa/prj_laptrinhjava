package ra.edu.presentation;
import java.util.Scanner;

public class ReaderMenu {
    public static void displayReaderMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐỘC GIẢ ---");
            System.out.println("1. Thêm độc giả");
            System.out.println("2. Sửa độc giả");
            System.out.println("3. Xóa độc giả");
            System.out.println("4. Tìm kiếm độc giả");
            System.out.println("5. Hiển thị danh sách độc giả");
            System.out.println("0. Quay về menu chính");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
