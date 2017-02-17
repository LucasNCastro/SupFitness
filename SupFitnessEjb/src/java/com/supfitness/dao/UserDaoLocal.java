package com.supfitness.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.supfitness.entity.User;
import javax.ejb.Local;

/**
 *
 * @author Ramneek
 */
@Local
public interface UserDaoLocal{

    void Create(User user);

    boolean Edit(User user, String username, String password);

    User FindOne(String username, String password);
    
}
