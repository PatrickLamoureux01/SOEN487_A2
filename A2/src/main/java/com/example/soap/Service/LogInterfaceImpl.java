package com.example.soap.Service;

import com.example.rest.Core.ChangeType;
import com.example.rest.Core.DBConnection;
import com.example.rest.Core.LogEntry;
import com.example.rest.Core.RepException;

import javax.jws.WebService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebService(endpointInterface = "com.example.soap.Service.LogInterface")
public class LogInterfaceImpl implements LogInterface {

    Connection conn = DBConnection.getConnection();

    @Override
    public ArrayList<LogEntry> getChangelog(ChangeType type,String from_date,String to_date){
        ArrayList<LogEntry> logs = new ArrayList<>();


        System.out.println(type);
        System.out.println(from_date);
        System.out.println(to_date);

        boolean first_flag = true;

        try{
            String select_changelog_sql = "SELECT * FROM changelog";

            if(type != null){
                select_changelog_sql += " WHERE type = ?";
                first_flag = false;
            }
            if(!from_date.equals("?")){
                if(first_flag){
                    select_changelog_sql += " WHERE timestamp >= TIMESTAMP(?)";
                    first_flag = false;
                }
                else{
                    select_changelog_sql += " AND timestamp >= TIMESTAMP(?)";
                }
            }
            if(!to_date.equals("?")){
                if(first_flag){
                    select_changelog_sql += " WHERE timestamp <= TIMESTAMP(?)";
                }
                else{
                    select_changelog_sql += " AND timestamp <= TIMESTAMP(?)";
                }
            }

            System.out.println(select_changelog_sql);

            PreparedStatement select_stmt = conn.prepareStatement(select_changelog_sql);
            int index = 0;
            if(type != null){
                select_stmt.setString(++index,type.toString());
            }
            if(!from_date.equals("?")){
                select_stmt.setString(++index,from_date);
            }
            if(!to_date.equals("?")){
                select_stmt.setString(++index,to_date);
            }



            ResultSet rs = select_stmt.executeQuery();

            if(!rs.next()){
                return null;
            }else{
                do{
                    LogEntry log = new LogEntry();
                    log.setIsrc(rs.getString("isrc"));
                    log.setTimestamp(rs.getString("timestamp"));
                    log.setType(ChangeType.valueOf(rs.getString("type")));

                    logs.add(log);
                }while(rs.next());
                return logs;
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void clearLogs(String isrc) throws RepException{
        throw new RepException("The method is not yet supported");
    }
}
