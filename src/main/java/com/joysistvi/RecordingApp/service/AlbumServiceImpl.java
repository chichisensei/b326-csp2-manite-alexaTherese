package com.joysistvi.RecordingApp.service;

import com.joysistvi.RecordingApp.model.Album;
import com.joysistvi.RecordingApp.model.Artist;
import com.joysistvi.RecordingApp.repo.AlbumRepo;

import java.util.List;

public class AlbumServiceImpl implements AlbumService{

    private final AlbumRepo albumRepo;

    public AlbumServiceImpl(AlbumRepo albumRepo) {
        this.albumRepo = albumRepo;
    }

    @Override
    public boolean createAlbum(Album album) {
        if (album.getName() == null || album.getName().trim().isEmpty()) {
            System.out.println("Album name is required!");
            return false;
        }

        return albumRepo.createAlbum(album);
    }

    @Override
    public List<Album> getAllAlbum() {
        return albumRepo.getAllAlbum();
    }

    @Override
    public Album getAlbumByArtistId(Artist artist) { // fix
        if (artist.getId() <= 0) {
            System.out.println("Invalid input!");
            return null;
        }

        Album album = albumRepo.getAlbumByArtistId(artist);

        if (album == null) {
            System.out.println("Album not found!");
        }

        return album;
    }

    @Override
    public List<Album> searchAlbum(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Album name is required!");
            return List.of();
        }

        return albumRepo.searchAlbum(keyword);
    }

    @Override
    public List<Album> searchAlbumByArtistKeyword(Artist artist) {
        if (artist.getName() == null || artist.getName().trim().isEmpty()) {
            System.out.println("Artist name is required!");
            return List.of();
        }

        return albumRepo.searchAlbumByArtistKeyword(artist);


    }

    @Override
    public boolean updateAlbum(Album album) {
        if (album.getName() == null || album.getName().trim().isEmpty()) {
            System.out.println("Album name is required");
            return false;
        } else if (album.getId() <= 0) {
            System.out.println("Invalid input");
            return false;
        }

        return albumRepo.updateAlbum(album);
    }

    @Override
    public boolean archiveAlbum(Album album) {
        if (album.getId() <= 0) {
            System.out.println("Invalid input!");
            return false;
        }

        return albumRepo.archiveAlbum(album);
    }

    @Override
    public boolean deleteAlbum(Album album) {
        if (album.getId() <= 0) {
            System.out.println("Invalid input!");
            return false;
        }

        return albumRepo.deleteAlbum(album);
    }
}
