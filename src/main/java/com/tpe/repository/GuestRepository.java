package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Guest;
import com.tpe.service.GuestService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class GuestRepository {

    private Session session;

    public Guest findById(Long guestId){
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            session.get(GuestService.class, guestId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public List<Guest> findAll() {
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            String allGuestHQL = "FROM Guest";
            List<Guest> guestList = session.createQuery(allGuestHQL, Guest.class).getResultList();
            return guestList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public void delete(Guest guest) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(guest);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}
