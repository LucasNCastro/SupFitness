package com.supfitness.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.supfitness.entity.Race;
import com.supfitness.entity.Race_;
import com.supfitness.entity.Track;
import com.supfitness.entity.User;
import com.supfitness.entity.User_;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

/**
 * @author Ramneek
 */

@Stateless
public class RaceDao implements RaceDaoLocal {
    
    @PersistenceContext(unitName = "SupFitnessEjbPU")
    private EntityManager em;

    @Override
    public void Create(List<Track> tracks, User user) {
        Race race = new Race();

        //DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

        Date todayDateTime = new java.util.Date();
        race.setDate(todayDateTime);
        race.setUserId(user);


        // Setting user
        List<Race> races = new ArrayList<Race>(1);
        races.add(race);
        user.setRaceCollection(races);

        // setting races
        race.setTrackCollection(tracks);
        for (Track track : tracks) {
            track.setRaceId(race);
        }

        em.persist(race);
        em.flush();
    }

    @Override
    public List<Race> FindAll(String username, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaQuery<Race> criteriaQuery = criteriaBuilder.createQuery(Race.class);
        
        Root<Race> rootRace = criteriaQuery.from(Race.class);
        
        Join<Race, User> user = rootRace.join(Race_.userId);
        
        
        Predicate[] conditions = {
            criteriaBuilder.equal(rootRace.get(Race_.userId).get(User_.username), username),
            criteriaBuilder.equal(rootRace.get(Race_.userId).get(User_.password), password)
        };
        
        criteriaQuery.where(conditions);
        
        TypedQuery<Race> typedQuery = em.createQuery(criteriaQuery);
        
        try {
            return em.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            return new ArrayList();
        }
        
    }

    @Override
    public List<Race> FindByDate(int year, int month, int date, String username, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaQuery<Race> criteriaQuery = criteriaBuilder.createQuery(Race.class);
        
        Root<Race> rootRace = criteriaQuery.from(Race.class);
        
        Join<Race, User> user = rootRace.join(Race_.userId);
        
        
        Predicate[] conditions = {
            criteriaBuilder.equal(rootRace.get(Race_.userId).get(User_.username), username),
            criteriaBuilder.equal(rootRace.get(Race_.userId).get(User_.password), password),
            criteriaBuilder.equal(rootRace.get(Race_.date), new Date(year-1900, month - 1, date))
        };
        
        criteriaQuery.where(conditions);
        
        TypedQuery<Race> typedQuery = em.createQuery(criteriaQuery);
        
        try {
            return em.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            return new ArrayList();
        }
    }
    
    @Override
    public Race FindLast(String username, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaQuery<Race> criteriaQuery = criteriaBuilder.createQuery(Race.class);
        
        Root<Race> rootRace = criteriaQuery.from(Race.class);
        
        Join<Race, User> user = rootRace.join(Race_.userId);
        
        
        Predicate[] conditions = {
            criteriaBuilder.equal(rootRace.get(Race_.userId).get(User_.username), username),
            criteriaBuilder.equal(rootRace.get(Race_.userId).get(User_.password), password)
        };
        
        criteriaQuery.where(conditions).orderBy(criteriaBuilder.desc(rootRace.get(Race_.id)));
        
        TypedQuery<Race> typedQuery = em.createQuery(criteriaQuery);
        
        try {
            return em.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Race Find(int id, String username, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaQuery<Race> criteriaQuery = criteriaBuilder.createQuery(Race.class);
        
        Root<Race> rootRace = criteriaQuery.from(Race.class);
        
        Join<Race, User> user = rootRace.join(Race_.userId);
        CollectionJoin<Race, Track> tracks = rootRace.join(Race_.trackCollection);
        
        Predicate[] conditions = {
            criteriaBuilder.equal(rootRace.get(Race_.userId).get(User_.username), username),
            criteriaBuilder.equal(rootRace.get(Race_.userId).get(User_.password), password),
            criteriaBuilder.equal(rootRace.get(Race_.id), id)
        };
        
        criteriaQuery.where(conditions);
        
        
        TypedQuery<Race> typedQuery = em.createQuery(criteriaQuery);
        
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
