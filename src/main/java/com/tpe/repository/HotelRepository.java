package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HotelRepository {

    private Session session;
    //1-b
    public void save(Hotel hotel){
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

    //2-b
    public Hotel findById(Long id){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            //select * from t_hotel where id=p_Id
            return session.get(Hotel.class, id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    //3-b
    public List<Hotel> findAll(){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Hotel> hotelList = session.createQuery("FROM Hotel", Hotel.class).getResultList();
            return hotelList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public void delete(Hotel hotel) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(hotel);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    //todo:
    //8-c : update t_hotel set name = ? , location = ? where id = ?
    public void updateHotel(Hotel existingHotel) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(existingHotel);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}
