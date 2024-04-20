package com.tpe.service;

import com.tpe.domain.Address;
import com.tpe.domain.Guest;
import com.tpe.exceptions.GuestNotFoundException;
import com.tpe.repository.GuestRepository;

import java.util.List;
import java.util.Scanner;

public class GuestService {

    private final Scanner scanner = new Scanner(System.in);
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
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

    public void deleteGuestById(Long id) {
        Guest foundGuest = findGuestById(id);
        //guestin rezervasyonları varsa orphanremoval attribute ile bunlar otomatik silinsin
        if(foundGuest != null){
            guestRepository.delete(foundGuest);
            System.out.println("Guest is removed successfully..Guest id : "+foundGuest.getId());
        }
    }

    //9-b
    public void saveGuest() {
        Guest guest = new Guest();
        System.out.println("Enter guest name : ");
        guest.setName(scanner.nextLine());

        Address address = new Address();

        System.out.println("Enter street : ");
        address.setStreet(scanner.nextLine());

        System.out.println("Enter city : ");
        address.setCity(scanner.nextLine());

        System.out.println("Enter country : ");
        address.setCountry(scanner.nextLine());

        System.out.println("Enter zipcode : ");
        address.setZipcode(scanner.nextInt());
        guest.setAddress(address);

        guestRepository.save(guest);
        System.out.println("Guest is saved successfully...");
    }
}
