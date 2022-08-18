package menu;

import java.util.Scanner;

public class Menu {
    public static int main_menu() {
        Scanner in = new Scanner(System.in);
        System.out.println("1.Имена злодеев");
        System.out.println("2.Имена миньонов");
        System.out.println("3.Добавление миньонов");
        System.out.println("4.Удаление миньонов");
        System.out.println("5.Выход");

        return in.nextInt();
    }

    public static int villain_id() {
        Scanner in = new Scanner(System.in);
        System.out.println("Id злодея :: ");
        return in.nextInt();
    }

    public static String get_line() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
