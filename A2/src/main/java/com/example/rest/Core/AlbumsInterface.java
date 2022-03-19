package com.example.rest.Core;

import java.sql.SQLException;
import java.util.List;

public interface AlbumsInterface {

    void addAlbum(String isrc, String title, String description, int year, String fName, String lName, String nick, String bio, int cover) throws SQLException;
    void updateAlbum(String isrc, String title, String description, int year, String fName, String lName, String nick, String bio, int cover) throws SQLException;
    void deleteAlbum(String isrc) throws SQLException;
    Album getAlbumInfo(String isrc);
    List<Album> getAlbumsList();

    void updateAlbumCover();
    void deleteAlbumCover();
    void getAlbumCover();

    void getChangeLogs(String from, String to, String type);
    void clearLogs();
}
