package com.example.rest.Core;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;

@Path("/files")
public class FileService {

    private static final String FILE_PATH = "D:\\User\\OneDrive - Concordia University - Canada\\School\\Concordia\\Semester_7\\SOEN487\\Assignments\\A2\\A2\\test.txt";

    @GET
    @Path("/download")
    @Produces("text/plain")
    public File getFile() {
        return new File(FILE_PATH);
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(@FormDataParam("file") InputStream uploadedInputStream) {
        String fileLocation = "D:\\User\\OneDrive - Concordia University - Canada\\School\\Concordia\\Semester_7\\SOEN487\\Assignments\\A2\\A2\\test.txt";

        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            FileOutputStream out = new FileOutputStream(fileLocation);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
