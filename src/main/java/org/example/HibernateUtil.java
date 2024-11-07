package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    //Implementamos un patron Singleton
    private static final SessionFactory sessionFactory;

    /*Creación del SessionFactory (si el usuario y contraseña se incluye en hibernate.cfg.xml n o es necesario esto,
    pero con esto podemos usar variable de entorno y evitar escribir la contraseña en el codigo)*/
    static {
        sessionFactory=new Configuration()
                .configure()
                .setProperty("hibernate.connection.password",
                        System.getenv("hibernate.password")) //Agregar esto a variable de entorno
                .setProperty("hibernate.connection.username",
                        System.getenv("hibernate_username")) //Agregar esto a variable de entorno
                .buildSessionFactory();
    }

    //Getter para el Singleton
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
