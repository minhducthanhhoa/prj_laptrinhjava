package ra.edu.presentation;
import java.util.Scanner;

public class BorrowMenu {
    public static void displayBorrowMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ PHIẾU MƯỢN ---");
            System.out.println("1. Tạo phiếu mượn");
            System.out.println("2. Trả sách");
            System.out.println("3. Tìm kiếm phiếu mượn");
            System.out.println("4. Hiển thị danh sách phiếu mượn");
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
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
