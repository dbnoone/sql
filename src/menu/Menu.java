package menu;

import java.util.Scanner;

public class Menu {
    public static int main_menu() {
        Scanner in = new Scanner(System.in);
        System.out.println("1.����� �������");
        System.out.println("2.����� ��������");
        System.out.println("3.���������� ��������");
        System.out.println("4.�������� ��������");
        System.out.println("5.�����");

        return in.nextInt();
    }

    public static int villain_id() {
        Scanner in = new Scanner(System.in);
        System.out.println("Id ������ :: ");
        return in.nextInt();
    }

    public static String get_line() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
