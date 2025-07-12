package com.example.demo.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final AdminLog adminLog;
    private final UserLog userLog;

    @Autowired
    public Menu(AdminLog adminLog, UserLog userLog) {
        this.adminLog = adminLog;
        this.userLog = userLog;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            System.out.println("Welcome To Coworking Space Reservation System");
            System.out.println("Options: 1 - Admin Login  /  2 - User Login  /  3 - Exit");
            System.out.print("Your Choice : ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminLog.adminLogin();
                    break;
                case 2:
                    userLog.userLogin();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
