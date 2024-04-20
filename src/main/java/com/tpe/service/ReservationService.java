package com.tpe.service;


import com.tpe.domain.Guest;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import com.tpe.exceptions.ReservationNotFoundException;
import com.tpe.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationService {

    private final Scanner scanner = new Scanner(System.in);
    private final ReservationRepository reservationRepository;
    private final GuestService guestService;
    private final RoomService roomService;

    public ReservationService(ReservationRepository reservationRepository, GuestService guestService, RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.guestService = guestService;
        this.roomService = roomService;
    }

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

    public void deleteReservationById(Long id) {
        Reservation reservation = findReservationById(id);
        if(reservation!=null){
            reservationRepository.delete(reservation);
            System.out.println("Reservation is cancelled successfully.. Reservation id: "+reservation.getId());
        }

    }

    public void createReservation() {
        Reservation reservation = new Reservation();

        //girilen tarihlerin uygunluğu kontrol edildi.
        System.out.println("Enter check-in date (yyyy-MM-dd : ");
        reservation.setCheckInDate(LocalDate.parse(scanner.nextLine()));

        System.out.println("Enter check-out date (yyyy-MM-dd : ");
        reservation.setCheckOutDate(LocalDate.parse(scanner.nextLine()));

        System.out.println("Enter room id : ");
        Long roomId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter guest id : ");
        Long guestId= scanner.nextLong();
        scanner.nextLine();

        Room room = roomService.findRoomById(roomId);
        Guest guest = guestService.findGuestById(guestId);

        reservation.setRoom(room);
        reservation.setGuest(guest);

        reservationRepository.save(reservation);
        System.out.println("Reservation is created successfully...");
    }
}
