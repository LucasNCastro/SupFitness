/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supfitness.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfitness.dao.RaceDaoLocal;
import com.supfitness.entity.Race;
import com.supfitness.entity.Track;
import com.supfitness.entity.User;
import java.lang.reflect.Type;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ramneek
 */
@Stateless
@Path("race")
public class RaceFacadeREST extends AbstractFacade<Race> {
    @PersistenceContext(unitName = "SupFitnessWebAppPU")
    private EntityManager em;
    
    @EJB
    private RaceDaoLocal raceDao;
    
    
    public RaceFacadeREST() {
        super(Race.class);
    }
    
    
    @POST
    @Path("create/{tracks}/{user}")
    @Consumes({"application/json"})
    public void create(@PathParam("tracks")String jsontracks, @PathParam("user")String jsonuser) {
        Gson gson = new Gson();
        Type tracksType = new TypeToken<List<Track>>() {}.getType();        
        List<Track> tracks = gson.fromJson(jsontracks, tracksType);
        
        User user = gson.fromJson(jsonuser, User.class);
        raceDao.Create(tracks, user);
    }
    
    
    @GET
    @Path("findbyid/{username}/{password}/{id}")
    @Produces({"application/json"})
    public Race findById(@PathParam("username") String username, @PathParam("password") String password, @PathParam("id") int id) {
        return raceDao.Find(id, username, password);
    }
    
    @GET
    @Path("findlast/{username}/{password}")
    @Produces({"application/json"})
    public Race findLast(@PathParam("username") String username, @PathParam("password") String password, @PathParam("id") int id) {
        return raceDao.FindLast(username, password);
    }
    
    @GET
    @Path("findbydate/{username}/{password}/{year}/{month}/{date}")
    @Produces({"application/json"})
    public List<Race> findByDate(@PathParam("username") String username, @PathParam("password") String password, @PathParam("year") int year,@PathParam("month") int month,@PathParam("date") int date) {
        return raceDao.FindByDate(year, month, date, username, password);
    }
    
    @GET
    @Path("findall/{username}/{password}")
    @Produces({"application/json"})
    public List<Race> findAll(@PathParam("username") String username, @PathParam("password") String password) {
        return raceDao.FindAll(username, password);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
