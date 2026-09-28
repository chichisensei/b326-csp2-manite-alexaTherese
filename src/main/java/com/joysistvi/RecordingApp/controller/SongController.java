package com.joysistvi.RecordingApp.controller;

import com.joysistvi.RecordingApp.model.Album;
import com.joysistvi.RecordingApp.model.Artist;
import com.joysistvi.RecordingApp.model.Song;
import com.joysistvi.RecordingApp.service.SongService;

import java.util.List;

public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    public boolean handleCreateSong(Song song, Album album) {
        return songService.createSong(song, album);
    }

    public List<Song> handleGetAllSongs() {
        return songService.getAllSongs();
    }


    public Song handleGetSongById(int id) {
        return songService.getSongById(id);
    }

    public List<Song> handleGetSongByKeyword(String keyword) {
        return songService.getSongByKeyword(keyword);
    }

    public List<Song> handleGetSongByArtistId(Artist artist) {
        return songService.getSongByArtistId(artist);
    }

    public List<Song> handleGetSongByArtistKeyword(String keyword) {
        return songService.getSongByArtistKeyword(keyword);
    }

    public boolean handleUpdateSong(Song song) {
        return songService.updateSong(song);
    }

    public boolean handleArchiveSong(Song song) {
        return songService.archiveSong(song);
    }

    public List<Song> handleReadAllArchivedSongs() {
        return songService.readAllArchivedSongs();
    }

    public boolean handleRestoreArchivedSong(Song song) {
        return songService.restoreArchivedSong(song);
    }

    public boolean handleDeleteSong(Song song) {
        return songService.deleteSong(song);
    }
}
