package com.example.rest.Core;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public interface AlbumsInterface {

    void addAlbum(String isrc, String title, String description, int year, String fName, String lName, String nick, String bio) throws SQLException;
    Integer updateAlbum(String isrc, String title, String description, int year, String fName, String lName, String nick, String bio) throws SQLException;
    Integer deleteAlbum(String isrc) throws SQLException;
    Album getAlbumInfo(String isrc);
    List<Album> getAlbumsList();

    Integer updateAlbumCover(String isrc, String name, String path, String type);
    Integer deleteAlbumCover(String isrc) throws SQLException;
    String getAlbumCover(String isrc);

    void getChangeLogs(String from, String to, String type);
    void clearLogs() throws RepException;
}
