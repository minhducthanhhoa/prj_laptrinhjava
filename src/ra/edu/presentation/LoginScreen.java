package ra.edu.presentation;

import ra.edu.business.service.AdminService;
import java.util.Scanner;

public class LoginScreen {
    private static final AdminService adminService = new AdminService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void showLogin() {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Tên đăng nhập: ");
            String username = scanner.nextLine();
            System.out.print("Mật khẩu: ");
            String password = scanner.nextLine();

            if (adminService.login(username, password)) {
                System.out.println("Đăng nhập thành công! Xin chào " + username);
                loggedIn = true;
            } else {
                System.out.println("Sai tên đăng nhập hoặc mật khẩu. Vui lòng thử lại.\n");
            }
        }
    }
}