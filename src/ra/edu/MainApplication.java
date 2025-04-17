package ra.edu;
import ra.edu.presentation.LoginScreen;
import ra.edu.presentation.MainMenu;
import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        LoginScreen.showLogin();

        Scanner scanner = new Scanner(System.in);
        MainMenu.display(scanner);
    }
}
