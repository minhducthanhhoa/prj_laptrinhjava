package ra.edu.presentation;
import java.util.Scanner;
public class MainMenu {
    public static void display(Scanner scanner) {
        while (true) {
            System.out.println("\n========== MENU CHÍNH ==========");
            System.out.println("1. Quản lý Sách");
            System.out.println("2. Quản lý Độc giả");
            System.out.println("3. Quản lý Phiếu mượn");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    BookMenu.displayBookMenu(scanner);
                    break;
                case 2:
                    ReaderMenu.displayReaderMenu(scanner);
                    break;
                case 3:
                    BorrowMenu.displayBorrowMenu(scanner);
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        }
    }
}
