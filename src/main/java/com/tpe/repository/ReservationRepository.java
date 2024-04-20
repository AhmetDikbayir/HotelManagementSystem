package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationRepository {

    private Session session;

    public Reservation findById(Long reservationId) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Reservation.class, reservationId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public List<Reservation> findAll() {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Reservation> allReservations = session.createQuery("FROM Reservation", Reservation.class).getResultList();
            return allReservations;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public void delete(Reservation reservation) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(reservation);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public void save(Reservation reservation) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(reservation);
            tx.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}
