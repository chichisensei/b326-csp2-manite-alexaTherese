package com.joysistvi.RecordingApp.cliview;

import com.joysistvi.RecordingApp.controller.AlbumController;

import java.util.Scanner;

public class AlbumView {

    private final AlbumController albumController;
    private final Scanner input;

    public AlbumView(AlbumController albumController, Scanner input) {
        this.albumController = albumController;
        this.input = input;
    }

    public void run() { // fix
        int choice;

        do {

            printMenu();

            choice = promptChoice();

            switch (choice) {
                case 1 -> // view all albums
                case 2 -> // search album
                case 3 -> // add album
                case 4 -> // update album
                case 5 -> // archive album
                case 6 -> // delete an album
                case 7 -> // restore an album
                default -> System.out.println("Invalid choice! Please enter a valid integer!");
            }

            if (choice != 0) {
                System.out.println("Press enter to continue...");
                input.nextLine();
            }


        } while(choice != 0);
    }

    public int readInt() {
        while(true) {
            String choice = input.nextLine();

            try {
                return Integer.parseInt(choice.trim());
            } catch (RuntimeException e) {
                System.err.println("Invalid input! Please enter a valid integer!");
            }

        }
    }

    public int promptChoice() {
        System.out.print("Enter a choice: ");
        return readInt();
    }

    private void printMenu() { // fix
        String menu = "1. View All Artists\n2. Search Artist\n3. Add Artist\n4. Update Artists\n5. Archive\n6. Restore\n7. Delete\n0. Back";
        System.out.println("\n=== Artist Management ===");


    }
}
