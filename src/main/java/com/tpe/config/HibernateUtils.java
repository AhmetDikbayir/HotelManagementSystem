package com.tpe.config;

import com.tpe.domain.Guest;
import com.tpe.domain.Hotel;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Hotel.class)
                    .addAnnotatedClass(Reservation.class)
                    .addAnnotatedClass(Room.class)
                    .addAnnotatedClass(Guest.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e){
            System.err.println("Initialization of session factory is FAILED!!!");
        }
    }

    //getter SF
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    //SF kapatalım
    public  static void shutDown(){
        getSessionFactory().close();
    }

    //session kapatalım
    public static void closeSession(Session session){
        if(session != null && session.isOpen()){
            session.close();
        }
    }
}
