/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supfitness.service;

import com.google.gson.Gson;
import com.supfitness.dao.RaceDaoLocal;
import com.supfitness.dao.UserDaoLocal;
import com.supfitness.entity.Race;
import com.supfitness.entity.Track;
import com.supfitness.entity.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ramneek
 */
@Stateless
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {
    @PersistenceContext(unitName = "SupFitnessWebAppPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }
    
    @EJB
    private UserDaoLocal userDao;
    
    
    
    @POST
    @Consumes({"application/json"})
    @Path("create/{user}")
    public void create(@PathParam("user") String jsonuser) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonuser, User.class);
        userDao.Create(user);
    }
    
    @PUT
    @Path("edit/{username}/{password}/user")
    @Produces({"application/json"})
    public boolean edit(@PathParam("username") String username, @PathParam("password") String password, @PathParam("user") String jsonuser) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonuser, User.class);
        return userDao.Edit(user, username, password);
    }
    
    @GET
    @Path("find/{username}/{password}")
    @Produces({"application/json"})
    public User findOne(@PathParam("username") String username, @PathParam("password") String password) {
        return userDao.FindOne(username, password);
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
