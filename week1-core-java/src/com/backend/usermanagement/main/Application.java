package com.backend.usermanagement.main;

import com.backend.usermanagement.exception.UserAlreadyExistsException;
import com.backend.usermanagement.model.User;
import com.backend.usermanagement.service.UserService;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== USER MANAGEMENT ===");
            System.out.println("1. Add User");
            System.out.println("2. View User");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter id: ");
                        Long id = Long.parseLong(scanner.nextLine());

                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        User user = new User(id, name, email);
                        userService.addUser(user);

                        System.out.println("User added successfully.");
                    } catch (UserAlreadyExistsException | IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid id format.");
                    }
                    break;

                case 2:
                    System.out.print("Enter user id: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        User user = userService.getUserById(id);
                        if (user == null) {
                            System.out.println("User not found.");
                        } else {
                            System.out.println(user);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid id format.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
