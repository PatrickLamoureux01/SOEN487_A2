package com.example.rest.Core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.rest.Core.DBConnection.conn;

public class LogEntry {

    private String timestamp;
    private ChangeType type;
    private String isrc;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ChangeType getType() {
        return type;
    }

    public void setType(ChangeType type) {
        this.type = type;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public void addLogEntry(ChangeType type, String isrc) throws SQLException {
        String insert_log_sql = "INSERT INTO changelog(type,isrc) VALUES(?,?)";
        PreparedStatement insert_log = conn.prepareStatement(insert_log_sql);
        insert_log.setString(1, String.valueOf(type));
        insert_log.setString(2, isrc);
        insert_log.executeUpdate();

    }
}
