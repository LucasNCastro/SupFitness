/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supfitness.dao;

import com.supfitness.entity.User;
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
public class UserDaoTest {
    
    public UserDaoTest() {
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
     * Test of Create method, of class UserDao.
     */
    @Ignore
    @Test
    public void testCreate() throws Exception {
        System.out.println("Create");
        User user = new User();
        user.setUsername("lucas");
        user.setFirstname("Lucas");
        user.setLastname("Negro Castro");
        user.setEmail("lucas@supinfo.com");
        user.setPassword("@zertY123");
        user.setZipcode(75019);
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserDaoLocal instance = (UserDaoLocal)container.getContext().lookup("java:global/classes/UserDao");
        instance.Create(user);
        container.close();
    }

    /**
     * Test of Edit method, of class UserDao.
     */
    @Ignore
    @Test
    public void testEdit() throws Exception {
        System.out.println("Edit");
        User user = new User();
        user.setEmail("lucas.negro@supinfo.com");
        String username = "lucas";
        String password = "@zertY123";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserDaoLocal instance = (UserDaoLocal)container.getContext().lookup("java:global/classes/UserDao");
        boolean expResult = true;
        boolean result = instance.Edit(user, username, password);
        assertEquals(expResult, result);
        container.close();
    }

    /**
     * Test of FindOne method, of class UserDao.
     */
    @Test
    public void testFindOne() throws Exception {
        System.out.println("FindOne");
        String username = "lucas";
        String password = "@zertY123";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserDaoLocal instance = (UserDaoLocal)container.getContext().lookup("java:global/classes/UserDao");
        
        User result = instance.FindOne(username, password);
        System.out.println(result.getEmail());
        container.close();
    }
    
}
