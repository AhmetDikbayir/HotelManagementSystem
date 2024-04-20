package com.tpe.controller;

import com.tpe.config.HibernateUtils;
import com.tpe.repository.GuestRepository;
import com.tpe.repository.HotelRepository;
import com.tpe.repository.ReservationRepository;
import com.tpe.repository.RoomRepository;
import com.tpe.service.GuestService;
import com.tpe.service.HotelService;
import com.tpe.service.ReservationService;
import com.tpe.service.RoomService;

import java.util.Scanner;

public class HotelManagementSystem {

    private static Scanner scanner = new Scanner(System.in);

    //ana menu
    public static void displayHotelManagementSystemMenu(){

        HotelRepository hotelRepository = new HotelRepository();
        HotelService hotelService = new HotelService(hotelRepository);

        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(hotelService,roomRepository);

        GuestRepository guestRepository = new GuestRepository();
        GuestService guestService = new GuestService(hotelService, roomService, guestRepository);

        ReservationRepository reservationRepository = new ReservationRepository();
        ReservationService reservationService = new ReservationService();

        boolean exit = false;
        while(!exit){
            System.out.println("========= Hotel Management System =========");
            System.out.println("1.Hotel Operations");
            System.out.println("2.Room Operations");
            System.out.println("3.Guest Operations");
            System.out.println("4.Reservation Operations");
            System.out.println("0. Exit");
            System.out.println("Enter your choice : ");

            int choice= scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    displayHotelOperationsMenu(hotelService);
                    break;
                case 2:
                    displayRoomOperationsMenu(roomService);
                    break;
                case 3:
                    displayGuestOperationsMenu(guestService);
                    break;
                case 4:
                    displayReservationOperationsMenu(reservationService);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Good Bye...");
                    HibernateUtils.shutDown();
                    break;
                default:
                    System.out.println("Invalid choice, please try again ");
                    break;
            }
        }
    }

    //hotel operations
    private static void displayHotelOperationsMenu(HotelService hotelService) {

        //HotelService hotelService = new HotelService();

        System.out.println("Hotel Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Hotel Operations ====");
            System.out.println("1. Add a new hotel");
            System.out.println("2. Find Hotel By ID");
            System.out.println("3. Delete Hotel By ID");
            System.out.println("4. Find All Hotels");
            System.out.println("5. Update Hotel By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //1-a : save
                    hotelService.saveHotel();
                    break;
                case 2:
                    //2-a : hotel bulma
                    System.out.println("Enter hotel ID : ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    hotelService.findHotelById(id);
                    break;
                case 3:
                    //7-a: hotel silme
                    System.out.println("Enter hotel ID : ");
                    Long hotelid= scanner.nextLong();
                    scanner.nextLine();
                    hotelService.deleteHotel(hotelid);
                    break;
                case 4:
                    //3 a : tüm otelleri listeleme
                    hotelService.getAllHotels();
                    break;
                case 5:
                    //8-a: hotel güncelleme
                    System.out.println("Enter hotel ID : ");
                    Long updatedHotelId = scanner.nextLong();
                    scanner.nextLine();
                    hotelService.updateHotelById(updatedHotelId);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    //room operations
    private static void displayRoomOperationsMenu(RoomService roomService) {

        System.out.println("Room Operation Menu");
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Room Operations ====");
            System.out.println("1. Add a new room");
            System.out.println("2. Find Room By ID");
            System.out.println("3. Delete Room By ID");
            System.out.println("4. Find All Rooms");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //4-a : Oda ekleme
                    roomService.saveRoom();
                    break;
                case 2:
                    //5-a
                    System.out.println("Enter room ID : ");
                    Long roomId = scanner.nextLong();
                    scanner.nextLine();
                    roomService.findRoomById(roomId);
                    break;
                case 3:
                    System.out.println("Enter room ID : ");
                    Long roomOfId = scanner.nextLong();
                    scanner.nextLine();
                    roomService.deleteRoom(roomOfId);
                    break;
                case 4:
                    roomService.getAllRooms();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    //guest operations
    private static void displayGuestOperationsMenu(GuestService guestService) {
        System.out.println("Guest Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Guest Operations ====");
            System.out.println("1. Add a new guest");
            System.out.println("2. Find Guest By ID");
            System.out.println("3. Delete Guest By ID");
            System.out.println("4. Find All Guests");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    break;
                case 2:
                    System.out.println("Enter guest ID : ");
                    Long guestId = scanner.nextLong();
                    scanner.nextLine();
                    guestService.findGuestById(guestId);
                    break;
                case 3:
                    System.out.println("Enter guest ID : ");
                    Long idOfGuest = scanner.nextLong();
                    scanner.nextLine();
                    guestService.deleteGuests(idOfGuest);
                    break;
                case 4:
                    guestService.getAllGuests();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    break;
            }
        }
    }

    //reservation operations
    private static void displayReservationOperationsMenu(ReservationService reservationService) {
        System.out.println("Reservation Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Reservation Operations ====");
            System.out.println("1. Add a new reservation");
            System.out.println("2. Find Reservation By ID");
            System.out.println("3. Find All Reservations");
            System.out.println("4. Delete Reservation By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    break;
                case 2:
                    System.out.println("Enter reservation ID : ");
                    Long reservationId = scanner.nextLong();
                    reservationService.findReservationById(reservationId);
                    break;
                case 3:
                    reservationService.getAllReservations();
                    break;
                case 4:
                    System.out.println("Enter reservation ID : ");
                    Long reservationOfId = scanner.nextLong();
                    reservationService.deleteReservation(reservationOfId);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
