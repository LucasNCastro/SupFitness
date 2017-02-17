package com.supfitness.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.supfitness.entity.User;
import com.supfitness.entity.User_;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * @author Ramneek
 */
@Stateless
public class UserDao implements UserDaoLocal{
    @PersistenceContext(unitName = "SupFitnessEjbPU")
    private EntityManager em;
    
    
    public UserDao() {}


    @Override
    public void Create(User user) {
        em.persist(user);
        em.flush();
    }

    @Override
    public boolean Edit(User user, String username, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        
        Root<User> rootUser = criteriaUpdate.from(User.class);
        
        if(user.getPassword() != null && user.getPassword().isEmpty() == false)
            criteriaUpdate.set(User_.password, user.getPassword());
        
        if(user.getEmail() != null && user.getEmail().isEmpty() == false)
            criteriaUpdate.set(User_.email, user.getEmail());
        
        if(user.getFirstname() != null && user.getFirstname().isEmpty() == false)
            criteriaUpdate.set(User_.firstname, user.getFirstname());
            
        if(user.getLastname() != null && user.getLastname().isEmpty() == false)
            criteriaUpdate.set(User_.lastname, user.getLastname());
        
        if(user.getZipcode() != null && user.getZipcode().toString().isEmpty() == false)
            criteriaUpdate.set(User_.zipcode, user.getZipcode());
        
        Predicate[] conditions = {
            criteriaBuilder.equal(rootUser.get(User_.username), username),
            criteriaBuilder.equal(rootUser.get(User_.password), password)
        };
        
        criteriaUpdate.where(conditions);
        
        Query query = em.createQuery(criteriaUpdate);
        
        return query.executeUpdate() > 0;
    }

    @Ignore
    @Override
    public User FindOne(String username, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        
        Root<User> rootUser = criteriaQuery.from(User.class);
        
        Predicate[] conditions = {
            criteriaBuilder.equal(rootUser.get(User_.username), username),
            criteriaBuilder.equal(rootUser.get(User_.password), password)
        };
        
        criteriaQuery.where(conditions);
        TypedQuery<User> typedQuery = em.createQuery(criteriaQuery);
        
        return typedQuery.getSingleResult();
    }
    
}
