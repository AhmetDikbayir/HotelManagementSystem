package com.tpe.service;

import com.tpe.domain.Guest;
import com.tpe.exceptions.GuestNotFoundException;
import com.tpe.repository.GuestRepository;

import java.util.List;
import java.util.Scanner;

public class GuestService {

    private final Scanner scanner = new Scanner(System.in);
    private final HotelService hotelService;
    private final RoomService roomService;
    private final GuestRepository guestRepository;

    public GuestService(HotelService hotelService, RoomService roomService, GuestRepository guestRepository) {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.guestRepository = guestRepository;
    }

    public Guest findGuestById(Long guestId) {
        Guest foundGuest = guestRepository.findById(guestId);
        try {
            if(foundGuest != null){
                System.out.println("--------------------------------------");
                System.out.println(foundGuest);
                System.out.println("--------------------------------------");
                return foundGuest;
            }else {
                throw new GuestNotFoundException("Guest not found by ID : " + guestId);
            }
        }catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }
        return  null;

    }

    public void getAllGuests() {
        List<Guest> allGuests = guestRepository.findAll();
        if(allGuests!=null){
            System.out.println("--------------- ALL GUESTS ----------------");
            for(Guest guest : allGuests){
                System.out.println(guest);
            }
        }else {
            System.out.println("No guest found");
        }
    }

    public void deleteGuests(Long idOfGuest) {
        Guest foundGuest = findGuestById(idOfGuest);

        if(foundGuest != null){
            System.out.println(foundGuest);
            System.out.println("Are sure to delete : ");
            System.out.println("Please answer with Y or N");
            String select = scanner.nextLine();

            if(select.equalsIgnoreCase("Y")){
                guestRepository.delete(foundGuest);
                System.out.println("Guest is deleted successfully!");
            }else {
                System.out.println("Delete operation is cancelled!!");
            }
        }
    }
}
