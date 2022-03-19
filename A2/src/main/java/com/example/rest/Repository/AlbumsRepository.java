package com.example.rest.Repository;

import com.example.rest.Core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

        try {
            String select_album_sql = "SELECT * FROM albums WHERE isrc = ?";
            PreparedStatement select_stmt = conn.prepareStatement(select_album_sql);
            select_stmt.setString(1, isrc);
            ResultSet rs = select_stmt.executeQuery();

            if (!rs.next()) {
                return null;
            } else {
                do {
                    String title = rs.getString("title");
                    String desc = rs.getString("description");
                    String year = rs.getString("releaseYear");
                    String fname = rs.getString("artistF");
                    String lname = rs.getString("artistL");
                    String nick = rs.getString("artistNick");
                    String bio = rs.getString("artistBio");
                    return new Album(isrc,title,desc,year,new Artist(fname,lname,nick,bio));
                } while (rs.next());
            }
        }
            catch (Exception ex){
                ex.printStackTrace();
            }
        return null;
    }

    @Override
    public ArrayList<Album> getAlbumsList() {

        ArrayList<Album> albums = new ArrayList<Album>();

        try {
            String select_album_sql = "SELECT * FROM albums";
            PreparedStatement select_stmt = conn.prepareStatement(select_album_sql);
            ResultSet rs2 = select_stmt.executeQuery();

            if (!rs2.next()) {
                return null;
            } else {
                do {
                    String isrc = rs2.getString("isrc");
                    String title = rs2.getString("title");
                    String desc = rs2.getString("description");
                    String year = rs2.getString("releaseYear");
                    String fname = rs2.getString("artistF");
                    String lname = rs2.getString("artistL");
                    String nick = rs2.getString("artistNick");
                    String bio = rs2.getString("artistBio");
                    albums.add(new Album(isrc,title,desc,year,new Artist(fname,lname,nick,bio)));
                } while (rs2.next());
                return albums;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
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
    public void getChangeLogs(String from, String to, String type) {

    }

    @Override
    public void clearLogs() {

    }

}
