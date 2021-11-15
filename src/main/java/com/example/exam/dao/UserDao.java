package com.example.exam.dao;

import com.example.exam.models.Product;
import com.example.exam.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao {
    public static boolean login(String username, String password){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default_exam");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<User> query  = em.createQuery("select u from User u WHERE u.username = ?1 and u.password = ?2", User.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            return false;
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
        return user != null;
    }
}
