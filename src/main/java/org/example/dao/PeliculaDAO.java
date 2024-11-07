package org.example.dao;

import org.example.HibernateUtil;
import org.example.models.Pelicula;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class PeliculaDAO implements DAO<Pelicula> {

    @Override
    public List<Pelicula> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pelicula", Pelicula.class).list();
        }
    }

    @Override
    public Pelicula findByID(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pelicula.class, id);
        }
    }

    @Override
    public void insert(Pelicula pelicula) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(pelicula);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Pelicula pelicula) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(pelicula);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Pelicula pelicula) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(pelicula);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}