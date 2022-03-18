package com.example.rest.Repository;

import com.example.rest.Core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AlbumsRepository implements AlbumsInterface {

    Connection conn = DBConnection.getConnection();
    LogEntry log = new LogEntry();

    public void addAlbum(String isrc, String title, String description, int year, String fName, String lName, String nick, String bio, int cover) throws SQLException {

        // Album Creation
        try {
            String insert_album_sql = "INSERT INTO albums(isrc,title,description,releaseYear,artistF,artistL,artistNick,artistBio,coverId) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement insert_album = conn.prepareStatement(insert_album_sql);
            insert_album.setString(1, isrc);
            insert_album.setString(2, title);
            insert_album.setString(3, description);
            insert_album.setInt(4, year);
            insert_album.setString(5, fName);
            insert_album.setString(6, lName);
            insert_album.setString(7, nick);
            insert_album.setString(8, bio);
            insert_album.setInt(9, cover);
            insert_album.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Log Entry
        log.addLogEntry(ChangeType.CREATE,isrc);
    }

    @Override
    public void updateAlbum(String isrc, String title, String description, int year, String fName, String lName, String nick, String bio, int cover) throws SQLException {

        // Album Update
        try {
            String update_album_sql = "UPDATE albums SET title = ?, description = ?, releaseYear = ?, artistF = ?, artistL = ?, artistNick = ?, artistBio = ?, coverId = ? WHERE isrc = ?";
            PreparedStatement update_album = conn.prepareStatement(update_album_sql);
            update_album.setString(1, title);
            update_album.setString(2, description);
            update_album.setInt(3, year);
            update_album.setString(4, fName);
            update_album.setString(5, lName);
            update_album.setString(6, nick);
            update_album.setString(7, bio);
            update_album.setInt(8, cover);
            update_album.setString(9, isrc);
            update_album.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Log Entry
        log.addLogEntry(ChangeType.UPDATE,isrc);
    }

    @Override
    public void deleteAlbum(String isrc) throws SQLException {

        // Album Deletion
        try {
            String delete_album_sql = "DELETE FROM albums WHERE isrc = ?";
            PreparedStatement del_album = conn.prepareStatement(delete_album_sql);
            del_album.setString(1, isrc);
            del_album.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Log Entry
        log.addLogEntry(ChangeType.DELETE,isrc);

    }

    @Override
    public Album getAlbumInfo(String isrc) {

        return null;
    }

    @Override
    public List<Album> getAlbumsList() {

        return null;
    }

    @Override
    public void updateAlbumCover() {

    }

    @Override
    public void deleteAlbumCover() {

    }

    @Override
    public void getAlbumCover() {

    }

    @Override
    public void getChangeLogs() {

    }

    @Override
    public void clearLogs() {

    }

}
