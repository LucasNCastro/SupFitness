/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supfitness.dao;

import com.supfitness.entity.Race;
import com.supfitness.entity.Track;
import com.supfitness.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Ramneek
 */
public class RaceDaoTest {
    
    public RaceDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Create method, of class RaceDao.
     */
    @Ignore
    @Test
    public void testCreate() throws Exception {
        System.out.println("Create");
        
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RaceDaoLocal raceDao = (RaceDaoLocal)container.getContext().lookup("java:global/classes/RaceDao");
        UserDaoLocal userDao = (UserDaoLocal)container.getContext().lookup("java:global/classes/UserDao");
        
        User user = userDao.FindOne("lucas", "@zertY123");
        if(user != null){
            Track track1 = new Track();
            track1.setLatitude((long) 5.256895);
            track1.setLongitude((long) 5.256895);
            track1.setDistance((long)4.50);
            track1.setSpeed((long)15.5);

            Track track2 = new Track();
            track2.setLatitude((long) 8.256895);
            track2.setLongitude((long) 9.256895);
            track2.setDistance((long)7.50);
            track2.setSpeed((long)12.5);

            List<Track> tracks = new ArrayList<>(2);
            tracks.add(track1);
            tracks.add(track2);

            System.out.println("Hello");
            raceDao.Create(tracks, user);
        }else{
            fail("User not found");
        }
        container.close();
    }

    /**
     * Test of FindAll method, of class RaceDao.
     */
    @Ignore
    @Test
    public void testFindAll() throws Exception {
        System.out.println("FindAll");
        String username = "lucas";
        String password = "@zertY123";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RaceDaoLocal instance = (RaceDaoLocal)container.getContext().lookup("java:global/classes/RaceDao");
        List<Race> result = instance.FindAll(username, password);
        
        for (Race result1 : result) {
            for (Track track : result1.getTrackCollection()) {
                System.out.println(track.getLatitude() + " " + track.getLongitude());
            }
        }
        
        assertEquals(1, result.size());
        container.close();
    }

    /**
     * Test of FindByDate method, of class RaceDao.
     */
    @Test
    public void testFindByDate() throws Exception {
        System.out.println("FindByDate");
        String username = "lucas";
        String password = "@zertY123";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RaceDaoLocal instance = (RaceDaoLocal)container.getContext().lookup("java:global/classes/RaceDao");
        List<Race> expResult = null;
        List<Race> result = instance.FindByDate(2016, 03, 11, username, password);
        
        for (Race result1 : result) {
            for (Track track : result1.getTrackCollection()) {
                System.out.println(track.getLatitude() + " " + track.getLongitude());
            }
        }
        
        assertEquals(1, result.size());
        container.close();
    }
    @Ignore
    @Test
    public void testFindByLastDate() throws Exception {
        System.out.println("FindByDate");
        Date date = new Date(2016 - 1900, 03 - 1, 11);
        String username = "lucas";
        String password = "@zertY123";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RaceDaoLocal instance = (RaceDaoLocal)container.getContext().lookup("java:global/classes/RaceDao");
        
        Race result = instance.FindLast(username, password);
        
        for (Track track : result.getTrackCollection()) {
                System.out.println(track.getLatitude() + " " + track.getLongitude());
            }
        
        container.close();
    }
    @Ignore
    @Test
    public void testFind() throws Exception {
        
        int id = 4;
        String username = "lucas";
        String password = "@zertY123";
       
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RaceDaoLocal instance = (RaceDaoLocal)container.getContext().lookup("java:global/classes/RaceDao");
        
        Race result = instance.Find(id, username, password);
        
        
        for (Track track : result.getTrackCollection()) {
                System.out.println(track.getLatitude() + " " + track.getLongitude());
            }
        
        container.close();
    }
    
    
    
    
}
