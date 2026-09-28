package com.joysistvi.RecordingApp.service;

import com.joysistvi.RecordingApp.model.Album;
import com.joysistvi.RecordingApp.model.Artist;

import java.util.List;

public interface AlbumService {

    boolean createAlbum(Album album);
    // Get all album
    List<Album> getAllAlbum();
    Album getAlbumByArtistId(Artist artist);
    // Search album
    List<Album> searchAlbum(String keyword);
    // search album by artist keyword
    List<Album> searchAlbumByArtistKeyword(Artist artist);
    // Update an album
    boolean updateAlbum(Album album);
    // archive album
    boolean archiveAlbum(Album album);
    // Delete an album
    boolean deleteAlbum(Album album);
}
