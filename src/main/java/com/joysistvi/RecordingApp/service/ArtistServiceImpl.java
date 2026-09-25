package com.joysistvi.RecordingApp.service;

import com.joysistvi.RecordingApp.model.Artist;
import com.joysistvi.RecordingApp.repo.ArtistRepo;

import java.util.List;

public class ArtistServiceImpl implements ArtistService{

    private final ArtistRepo artistRepo;

    // Constructor Injection


    public ArtistServiceImpl(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }



    @Override
    public List<Artist> getAllArtists() {
        return artistRepo.getAllArtists();
    }

    @Override
    public Artist getArtistById(int id) {
        if (id <= 0) {
            System.out.println("Invalid artist ID!");
            return null;
        }

        Artist artist = artistRepo.getArtistById(id);
        if(artist == null) {
            System.out.println("Artist not found.");
        }

        return artist;
    }

    @Override
    public List<Artist> searchArtist(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Artist name cannot be empty!");
            return List.of(); // instead of returning null; this is empty
        }
        return artistRepo.searchArtist(keyword.trim());
    }

    @Override
    public boolean createArtist(Artist artist) {
        if (artist.getName() == null || artist.getName().trim().isEmpty()) {
            System.out.println("Artist name is required!");
            return false;
        }
        return true;
    }

    @Override
    public boolean updateArtist(Artist artist) {
        return false;
    }

    @Override
    public boolean archiveArtist(int id) {
        return false;
    }

    @Override
    public boolean restoreArtist(int id) {
        return false;
    }

    @Override
    public boolean deleteArtist(int id) {
        return false;
    }

    @Override
    public List<Artist> readAllArchivedArtist() {
        return List.of();
    }
}
