package com.tpe.service;


import com.tpe.domain.Reservation;
import com.tpe.exceptions.ReservationNotFoundException;
import com.tpe.repository.ReservationRepository;

import java.util.List;
import java.util.Scanner;

public class ReservationService {

    private final Scanner scanner = new Scanner(System.in);
    private final ReservationRepository reservationRepository = new ReservationRepository();

    public Reservation findReservationById(Long reservationId) {
        Reservation foundReservation = reservationRepository.findById(reservationId);
        try {
        if(foundReservation != null){
            System.out.println("--------------------------------------");
            System.out.println(foundReservation);
            System.out.println("--------------------------------------");
            return foundReservation;
        }else {
            throw new ReservationNotFoundException("Reservation not found by ID : " + reservationId);
        }
    }catch (ReservationNotFoundException e){
        System.out.println(e.getMessage());
    }
        return null;
    }

    public void getAllReservations() {
        List<Reservation> allReservation = reservationRepository.findAll();
        if(!allReservation.isEmpty()){
            System.out.println("----------- ALL RESERVATIONS --------------");
            for(Reservation reservation : allReservation){
                System.out.println(reservation);
            }
        }else {
            System.out.println("No reservations found!");
        }
    }

    public void deleteReservation(Long reservationOfId) {
        Reservation foundReservation = reservationRepository.findById(reservationOfId);
        if(foundReservation!=null){
            System.out.println(foundReservation);
            System.out.println("Are sure to delete : ");
            System.out.println("Please answer with Y or N");
            String select = scanner.nextLine();

            if(select.equalsIgnoreCase("Y")){
                reservationRepository.delete(foundReservation);
                System.out.println("Reservation is deleted successfully!");
            }else {
                System.out.println("Delete operation is CANCELLED!");
            }
        }

    }
}
