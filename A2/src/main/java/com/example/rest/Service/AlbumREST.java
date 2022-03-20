package com.example.rest.Service;

import com.example.rest.Core.*;
import com.example.rest.Repository.AlbumsRepository;
import org.apache.http.HttpException;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;



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
    @Path("{isrc}/{title}/{desc}/{year}/{fname}/{lname}/{nick}/{bio}/{cover}")
    public String createAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("desc") String desc, @PathParam("year") int year, @PathParam("fname") String fname, @PathParam("lname") String lname, @PathParam("nick") String nick, @PathParam("bio") String bio, @PathParam("cover") int cover) throws RepException {
        try {
            repo.addAlbum(isrc, title, desc, year, fname, lname, nick, bio, cover);
            return "Album was created successfully.";
        } catch (Exception ex) {
            throw new RepException("Error creating album.");
        }
    }

    @PUT
    @Path("{isrc}/{title}/{desc}/{year}/{fname}/{lname}/{nick}/{bio}/{cover}")
    public String modifyAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("desc") String desc, @PathParam("year") int year, @PathParam("fname") String fname, @PathParam("lname") String lname, @PathParam("nick") String nick, @PathParam("bio") String bio, @PathParam("cover") int cover) throws RepException, SQLException {

            Integer rows = repo.updateAlbum(isrc, title, desc, year, fname, lname, nick, bio, cover);
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
}
