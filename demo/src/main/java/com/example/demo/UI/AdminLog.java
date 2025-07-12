package com.example.demo.UI;

import com.example.demo.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AdminLog {

    private final SpaceService spaceService;

    @Autowired
    public AdminLog(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    public void adminLogin() {
        System.out.println("Admin Login Successful...");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("Options: 1 - Add a new Coworking Space / 2 - Remove a Coworking Space / 3 - View Spaces / 4 - Exit");
            System.out.print("Your Choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    spaceService.addSpace();
                    System.out.println("New space added.");
                    break;
                case 2:
                    System.out.print("Enter space ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    spaceService.deleteSpace(idToDelete);
                    System.out.println("Space deleted if it existed.");
                    break;
                case 3:
                    spaceService.getAllSpaces().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Exiting Admin menu.");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
