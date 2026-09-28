package com.joysistvi.RecordingApp.repo;

import com.joysistvi.RecordingApp.model.Album;
import com.joysistvi.RecordingApp.model.Artist;
import com.joysistvi.RecordingApp.model.Song;

import java.util.List;

public interface SongRepo {
    // CRUD
    boolean createSong(Song song, Album album);
    List<Song> getAllSongs();
    Song getSongById(int id);
    List<Song> getSongByKeyword(String keyword);
    List<Song> getSongByArtistId(Artist artist);
    List<Song> getSongByArtistKeyword(Artist keyword);
    boolean updateSong(Song song);
    boolean archiveSong(Song song);
    List<Song> readAllArchivedSongs();
    boolean restoreArchivedSong(Song song);
    boolean deleteSong(Song song);
}
