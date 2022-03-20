package com.example.rest.Service;

import com.example.rest.Core.*;
import com.example.rest.Repository.AlbumsRepository;
import org.apache.http.HttpException;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPart;


import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


@Path("albums")
public class AlbumREST {

    private static AlbumsRepository repo = new AlbumsRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Album> getAlbums() throws RepException {
        try {
            return repo.getAlbumsList();
        } catch (Exception ex) {
            throw new RepException("Problem fetching albums list.");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isrc}")
    public Album getAlbum(@PathParam("isrc") String isrc) throws RepException{
        Album al = repo.getAlbumInfo(isrc);
        if (al != null) {
            return al;
        } else {
            throw new RepException("Album does not exist.");
        }
    }

    @POST
    @Path("{isrc}/{title}/{desc}/{year}/{fname}/{lname}/{nick}/{bio}")
    public String createAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("desc") String desc, @PathParam("year") int year, @PathParam("fname") String fname, @PathParam("lname") String lname, @PathParam("nick") String nick, @PathParam("bio") String bio) throws RepException {
        try {
            repo.addAlbum(isrc, title, desc, year, fname, lname, nick, bio);
            return "Album was created successfully.";
        } catch (Exception ex) {
            throw new RepException("Error creating album.");
        }
    }

    @PUT
    @Path("{isrc}/{title}/{desc}/{year}/{fname}/{lname}/{nick}/{bio}")
    public String modifyAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("desc") String desc, @PathParam("year") int year, @PathParam("fname") String fname, @PathParam("lname") String lname, @PathParam("nick") String nick, @PathParam("bio") String bio) throws RepException, SQLException {

            Integer rows = repo.updateAlbum(isrc, title, desc, year, fname, lname, nick, bio);
            if (rows == 0) {
                throw new RepException("Album does not exist and/or was not able to be modified.");
            } else {
                return "Album was modified successfully.";
            }
    }

    @DELETE
    @Path("{isrc}")
    public String deleteAlbum(@PathParam("isrc") String isrc) throws RepException, SQLException {

            Integer rows = repo.deleteAlbum(isrc);
            if (rows == 0) {
                throw new RepException("Album does not exist and/or was not able to be deleted.");
            } else {
                return "Album was deleted successfully.";
            }
    }

    @POST
    @Path("/cover/{isrc}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(@FormDataParam("file") BufferedImage image, @PathParam("isrc") String isrc) throws IOException {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String fileLocation = "D:\\User\\OneDrive - Concordia University - Canada\\School\\Concordia\\Semester_7\\SOEN487\\Assignments\\A2\\A2\\images\\";
        //System.out.println(image.getType());
        ImageIO.write(image, "jpg", new File(fileLocation+generatedString+".jpg"));
        if (image.getType() == 5) {
            repo.updateAlbumCover(isrc,generatedString, fileLocation, "image/jpeg");
        }
    }

    @DELETE
    @Path("/cover/{isrc}")
    public String deleteCover(@PathParam("isrc") String isrc) throws RepException, SQLException {

        Integer rows = repo.deleteAlbumCover(isrc);
        if (rows == 0) {
            throw new RepException("Album cover does not exist and/or was not able to be deleted.");
        } else {
            return "Album cover was deleted successfully.";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cover/{isrc}")
    public String getAlbumCover(@PathParam("isrc") String isrc) throws RepException{
        String cover = repo.getAlbumCover(isrc);
        if (cover != null) {
            return cover;
        } else {
            throw new RepException("Album does not have a cover.");
        }
    }
}
