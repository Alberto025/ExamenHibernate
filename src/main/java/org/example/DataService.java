package org.example;
import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class DataService {

    // Registrar una nueva película
    public void registrarPelicula(Pelicula pelicula) {
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

    // Obtener opiniones de un usuario específico
    public List<Opinion> obtenerOpinionesPorUsuario(String usuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Opinion WHERE usuario = :usuario", Opinion.class)
                    .setParameter("usuario", usuario)
                    .list();
        }
    }

    // Añadir una opinión a una película existente
    public void añadirOpinionAPelicula(Long peliculaId, Opinion opinion) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Pelicula pelicula = session.get(Pelicula.class, peliculaId);
            if (pelicula != null) {
                pelicula.añadirOpinion(opinion);
                session.save(opinion);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Listar películas con baja puntuación
    public List<Pelicula> obtenerPeliculasConBajaPuntuacion() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT DISTINCT p FROM Pelicula p JOIN p.opiniones o WHERE o.puntuacion <= 3", Pelicula.class
            ).list();
        }
    }
}