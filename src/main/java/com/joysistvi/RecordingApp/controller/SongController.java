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

    public boolean createSong(Song song, Album album) {
        return songService.createSong(song, album);
    }

    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }


    public Song getSongById(int id) {
        return songService.getSongById(id);
    }

    public List<Song> getSongByKeyword(String keyword) {
        return songService.getSongByKeyword(keyword);
    }

    public List<Song> getSongByArtistId(Artist artist) {
        return songService.getSongByArtistId(artist);
    }

    public List<Song> getSongByArtistKeyword(Artist keyword) {
        return songService.getSongByArtistKeyword(keyword);
    }

    public boolean updateSong(Song song) {
        return songService.updateSong(song);
    }

    public boolean archiveSong(Song song) {
        return songService.archiveSong(song);
    }

    public List<Song> readAllArchivedSongs() {
        return songService.readAllArchivedSongs();
    }

    public boolean restoreArchivedSong(Song song) {
        return songService.restoreArchivedSong(song);
    }

    public boolean deleteSong(Song song) {
        return songService.deleteSong(song);
    }
}
