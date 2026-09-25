package com.joysistvi.RecordingApp.config;

import com.joysistvi.RecordingApp.dao.ArtistDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main extends DatabaseConnection{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DatabaseConnection db = new DatabaseConnection();
        ArtistDao artistDao = new ArtistDao(db);


        String banner = "\n[ 1 ] Create Data" + "\n[ 2 ] Read Data" + "\n[ 3 ] Read Artist By Id";



        while (true) {
            System.out.println(banner);
            System.out.print("\nChoose a number: ");
            int user = input.nextInt();
            input.nextLine();
            switch (user) {

                case 1 -> {
                    System.out.print("Type an artist: ");
                    String userArtist = input.nextLine();
                    artistDao.createArtist(userArtist);
                    return;
                }
                case 2 -> {
                    System.out.println();
//                    artistDao.readAllArtist();
                    System.out.println();
                    return;
                }
                case 3 -> {
                    System.out.print("Type an id: ");
                    int artistId = input.nextInt();
                    artistDao.readArtistById(artistId);
                    System.out.println();
                }
                default -> System.out.println("Invalid input!");
            }
        }



    }

    // CRUD OPERATIONS
    public void createArtist() {

        // Define Query
        // Exception Handling
        // tatawagin muna connect bago magexecute ng query
    }


}

/*
    //        try {
//            db.connect();
//            System.out.println("Connected Successfully!");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
 */
