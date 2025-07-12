package com.example.demo.UI;

import com.example.demo.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserLog {

    private final SpaceService spaceService;

    @Autowired
    public UserLog(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    public void userLogin() {
        Scanner scanner = new Scanner(System.in);
        String username;

        do {
            System.out.print("Enter Username: ");
            username = scanner.nextLine();
            if (username.isEmpty()) {
                System.out.println("Username cannot be empty.");
            }
        } while (username.isEmpty());

        System.out.println("User Login Successful...");

        int choice = 0;
        while (choice != 5) {
            System.out.println("Options: 1 - Browse The Spaces / 2 - Make a Reservation / 3 - View My Reservations / 4 - Cancel Reservation / 5 - Exit");
            System.out.print("Your Choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    spaceService.getAvailableSpaces().forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Enter Table ID to reserve: ");
                    int reserveId = scanner.nextInt();
                    System.out.println(spaceService.reserveSpace(reserveId, username));
                    break;
                case 3:
                    spaceService.myReservedSpaces(username).forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter Table ID to cancel reservation: ");
                    int cancelId = scanner.nextInt();
                    System.out.println(spaceService.cancelReservation(cancelId, username));
                    break;
                case 5:
                    System.out.println("Exiting User menu.");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
            scanner.nextLine();
        }
    }
}
