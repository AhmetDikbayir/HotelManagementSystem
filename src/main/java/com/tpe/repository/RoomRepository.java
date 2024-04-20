package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Hotel;
import com.tpe.domain.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomRepository {

    private Session session;

    //4-c
    public void save(Room room){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(room);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public Room findById(Long id){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Room.class, id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public List<Room> findAll(){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Room> roomList = session.createQuery("FROM Room", Room.class).getResultList();
            return roomList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public void delete(Room room) {
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(room);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}
