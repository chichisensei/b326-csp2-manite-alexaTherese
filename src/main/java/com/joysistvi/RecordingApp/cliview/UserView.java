package com.joysistvi.RecordingApp.cliview;

import com.joysistvi.RecordingApp.controller.UserController;
import com.joysistvi.RecordingApp.model.User;


import java.util.Scanner;

public class UserView {

    private final UserController userController;
    private final Scanner input;

    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GOLD = "\033[33m";
    private static final String DIM = "\033[2m";

    public UserView(UserController userController, Scanner input) {
        this.userController = userController;
        this.input = input;
    }

    public User run() {
        int choice;

        do {
            clearScreen();

            // menubanner
            String loginBanner = """
                    【１】 Sign Up
                    【２】 Log In
                    【０】 Exit
                    """;

            displayMenu("Chipotify", loginBanner);

            choice = readInt(input, "Enter choice");

            switch(choice) {
                case 1 -> signUpUser();
                case 2 -> {
                    User user = loginUser();

                    if (user != null) return user;
                }
                case 0 -> System.out.println("Thank you for using Chipotify!");
                default -> System.out.println("Invalid input! Please try again!");
            }




        } while(choice != 0);

        return null;
    }

    private void signUpUser() {
        User user = new User();
        user.setUserName(promptInput(input,"Enter username"));
        user.setPassword(promptInput(input,"Enter password"));

        boolean signUpSuccess = userController.handleCreateUser(user);

        System.out.println(signUpSuccess ? "Account created successfully!" : "Failed to create account!");


    }

    private User loginUser() {
        User credentials = new User();
        credentials.setUserName(promptInput(input,"Enter username"));
        credentials.setPassword(promptInput(input,"Enter password"));

        User user = userController.handleLoginUser(credentials);
        if (user != null) {
            System.out.println("Login Successfully!");
            return user;
        }

        System.out.println("Invalid username or password!");
        return null;
    }





    private void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private int readInt(Scanner input, String label) {
        while(true) {
            try {
                return Integer.parseInt(promptInput(input, label));
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid input!");
            }
        }
    }

    private static String promptInput(Scanner input, String label) {
        String prompt = label + " ○ ";

        int width = prompt.length() + 10;

        String topBorder = createMusicBorder(width);
        String bottomBorder = "─".repeat(width);

        System.out.println();
        System.out.println(RED + topBorder + RESET);

        System.out.print(GOLD + prompt + RESET);

        String value = input.nextLine().trim();

        System.out.println(RED + bottomBorder + RESET);

        return value;

    }

    private static String createMusicBorder(int width) {
        int sideLength = (width - 3) / 2;

        return "─".repeat(sideLength) + " ♪ " + "─".repeat(width - sideLength - 3);
    }

    private static void displayMenu(String title, String content) {
        int width = Math.max(getVisualLength(title), getMaxLength(content.split("\n"))) + 10;

        String top = createMusicBorder(width);

        System.out.println();
        System.out.println(RED + top + RESET);
        System.out.println(GOLD + centerText("○ " + title + " ○", width) + RESET);

        System.out.println(RED + "─".repeat(width) + RESET);

        System.out.println(content);

    }

    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;

        if (padding <= 0) {
            return text;
        }

        return " ".repeat(padding) + text;
    }

    private static int getVisualLength(String text) {
        if (text == null || text.isEmpty()) return 0;

        String cleanText = text.replaceAll("\u001B\\[[;\\d]*m", "");

        int length = cleanText.length();

        for (char c : cleanText.toCharArray()) {
            if (c == '【' || c == '】' || c == '₱' || c == '[' || c == ']') {
                length += 1;
            }
        }
        return length;
    }

    private static int getMaxLength(String[] lines) {
        int max = 0;
        for (String line : lines) {
            int visuallen = getVisualLength(line);
            if (visuallen > max) {
                max = visuallen;
            }

//            if (line.length() > max) {
//                max = line.length();
//            }
        }
        return max;
    }
}
