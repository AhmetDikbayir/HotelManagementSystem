package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.exceptions.HotelNotFoundException;
import com.tpe.repository.HotelRepository;

import java.util.List;
import java.util.Scanner;

public class HotelService {

    private Scanner scanner = new Scanner(System.in);

    private final HotelRepository hotelRepository;

    //paramlı constr
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    //1-c
    public void saveHotel(){

        Hotel hotel = new Hotel();

        System.out.println("Enter hotel ID :");
        hotel.setId(scanner.nextLong());
        scanner.nextLine();
        System.out.println("Enter hotel name :");
        hotel.setName(scanner.nextLine());
        System.out.println("Enter hotel location :");
        hotel.setLocation(scanner.nextLine());

        hotelRepository.save(hotel);

        System.out.println("Hotel is saved successfully. Hotel ID : " + hotel.getId());
    }

    //2-c : idsi verilen hoteli consolda görüntüleme
    public Hotel findHotelById(Long id){
        Hotel foundHotel = hotelRepository.findById(id);
        try {
            if(foundHotel != null){
                System.out.println("--------------------------------------");
                System.out.println(foundHotel);
                System.out.println("--------------------------------------");
                return foundHotel;
            }else {
                throw new HotelNotFoundException("Hotel not found by ID : " + id);
            }
        }catch (HotelNotFoundException e){
            System.out.println(e.getMessage());
        }
        return  null;
    }

    //3-c : tüm otelleri listeleme
    public void getAllHotels(){
        List<Hotel> allHotels = hotelRepository.findAll();
        if(!allHotels.isEmpty()){
            System.out.println("----------- ALL HOTELS --------------");
            for(Hotel hotel : allHotels){
                System.out.println(hotel);
            }
        }else {
            System.out.println("Hotel list is empty!");
        }
    }

    //7-b
    public void deleteHotel(Long hotelid) {
        //idsi verilen hotel var mı?
        Hotel foundHotel = findHotelById(hotelid);

        if(foundHotel!=null){
            System.out.println(foundHotel);
            System.out.println("Are sure to delete : ");
            System.out.println("Please answer with Y or N");
            String select = scanner.nextLine();

            if(select.equalsIgnoreCase("Y")){
                hotelRepository.delete(foundHotel);
                System.out.println("Hotel is deleted successfully...");
            }else {
                System.out.println("Delete operation is CANCELLED!!!");
            }
        }
    }

    public void updateHotelById(Long hotelId) {
        Hotel existingHotel = findHotelById(hotelId);
        if(existingHotel!=null){
            System.out.println("Enter the new hotel name : ");
            existingHotel.setName(scanner.nextLine());
            System.out.println("Enter the new hotel location : ");
            existingHotel.setLocation(scanner.nextLine());
            hotelRepository.updateHotel(existingHotel);
            System.out.println("Hotel is updated successfully");
        }
    }
}
