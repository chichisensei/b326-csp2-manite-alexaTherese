package com.joysistvi.RecordingApp.controller;

import com.joysistvi.RecordingApp.model.Album;
import com.joysistvi.RecordingApp.model.Artist;
import com.joysistvi.RecordingApp.service.AlbumService;

import java.util.List;

public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    public boolean handleCreateAlbum(Album album) {
        return albumService.createAlbum(album);
    }

    public List<Album> handleViewAllAlbum() {
        return albumService.getAllAlbum();
    }

    public Album handleAlbumByArtistId(Artist artist) {
        return albumService.getAlbumByArtistId(artist);
    }

    public List<Album> handleSearchAlbum(String keyword) {
        return albumService.searchAlbum(keyword);
    }

    public List<Album> handleSearchAlbumByArtist(Artist artist) {
        return albumService.searchAlbumByArtistKeyword(artist);
    }

    public boolean handleUpdateAlbum(Album album) {
        return albumService.updateAlbum(album);
    }

    public boolean handleArchiveAlbum(Album album) {
        return albumService.archiveAlbum(album);
    }

    public boolean handleDeleteAlbum(Album album) {
        return albumService.deleteAlbum(album);
    }
}
