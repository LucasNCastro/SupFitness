package com.supfitness.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.supfitness.entity.Race;
import com.supfitness.entity.Track;
import com.supfitness.entity.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramneek
 */
@Local
public interface RaceDaoLocal{
    
    void Create(List<Track> tracks, User user);

    List<Race> FindAll(String username, String password);

    List<Race> FindByDate(int year, int month, int date, String username, String password);
    
    Race FindLast(String username, String password);
    
    Race Find(int id, String username, String password);

    
}
