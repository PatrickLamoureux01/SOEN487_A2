package com.example.soap.Service;
import com.example.rest.Core.ChangeType;
import com.example.rest.Core.LogEntry;
import com.example.rest.Core.RepException;
import org.glassfish.grizzly.http.util.TimeStamp;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.ArrayList;

@WebService
public interface LogInterface {
    @WebMethod
    ArrayList<LogEntry> getChangelog(ChangeType type,String from_date,String to_date);
    @WebMethod
    void clearLogs(String isrc) throws RepException;
    // What this method does is not specified
    // So Method type and provided input could be incorrect
}