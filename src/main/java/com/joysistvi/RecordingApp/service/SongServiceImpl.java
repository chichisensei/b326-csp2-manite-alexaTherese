package com.joysistvi.RecordingApp.service;

import com.joysistvi.RecordingApp.model.Album;
import com.joysistvi.RecordingApp.model.Artist;
import com.joysistvi.RecordingApp.model.Song;
import com.joysistvi.RecordingApp.repo.SongRepo;
import java.util.List;

public class SongServiceImpl implements SongService {

    private final SongRepo songRepo;

    public SongServiceImpl(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    @Override
    public boolean createSong(Song song, Album album) {
        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            System.out.println("Song title is required!");
            return false;
        } else if(song.getLength() == null || song.getLength().trim().isEmpty()) {
            System.out.println("Length is required!");
            return false;
        } else if (song.getGenre() == null || song.getGenre().trim().isEmpty()) {
            System.out.println("Genre is required!");
            return false;
        } else if (album.getId() <= 0) {
            System.out.println("Invalid input! Please enter a valid integer!");
            return false;
        }
        return songRepo.createSong(song, album);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepo.getAllSongs();
    }

    @Override
    public Song getSongById(int id) {
        if (id <= 0) {
            System.out.println("Invalid input! Please enter a valid integer!");
            return null;
        }
        Song song = songRepo.getSongById(id);
        if (song == null) {
            System.out.println("Song not found!");
        }

        return song;
    }

    @Override
    public List<Song> getSongByKeyword(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Song title is required!");
            return List.of();
        }

        return songRepo.getSongByKeyword(keyword.trim());

    }

    @Override
    public List<Song> getSongByArtistId(Artist artist) {
        if (artist.getId() <= 0) {
            System.out.println("Invalid input! Please enter a valid integer!");
            return List.of();
        }

        return songRepo.getSongByArtistId(artist);

    }

    @Override
    public List<Song> getSongByArtistKeyword(Artist keyword) {
        if (keyword == null) {
            System.out.println("Artist name is required");
            return List.of();
        }

        return songRepo.getSongByArtistKeyword(keyword);

    }

    @Override
    public boolean updateSong(Song song) {
        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            System.out.println("Song title is required!");
            return false;
        } else if(song.getLength() == null || song.getLength().trim().isEmpty()) {
            System.out.println("Length is required!");
            return false;
        } else if (song.getGenre() == null || song.getGenre().trim().isEmpty()) {
            System.out.println("Genre is required!");
            return false;
        } else if (song.getId() <= 0) {
            System.out.println("Invalid input! Please enter a valid integer!");
            return false;
        }
        return songRepo.updateSong(song);
    }

    @Override
    public boolean archiveSong(Song song) {
        if (song.getId() <= 0) {
            System.out.println("Invalid input! Please enter a valid integer!");
            return false;
        }
        return songRepo.archiveSong(song);
    }

    @Override
    public List<Song> readAllArchivedSongs() {
        if (songRepo.readAllArchivedSongs().isEmpty()) {
            return List.of();
        }

        return songRepo.readAllArchivedSongs();
    }

    @Override
    public boolean restoreArchivedSong(Song song) {
        if (song.getId() <= 0) {
            System.out.println("Invalid input! Please enter a valid integer!");
            return false;
        }

        return songRepo.restoreArchivedSong(song);
    }

    @Override
    public boolean deleteSong(Song song) {
        if (song.getId() <= 0) {
            System.out.println("Invalid input! Please enter a valid integer!");
            return false;
        }

        return songRepo.deleteSong(song);
    }
}
