package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HotelRepository {

    private Session session;
    //1-b
    public void saveHotel(Hotel hotel){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(hotel);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);

        }


    }
}
