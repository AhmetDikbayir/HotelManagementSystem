package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.domain.Room;
import com.tpe.exceptions.HotelNotFoundException;
import com.tpe.exceptions.RoomNotFoundException;
import com.tpe.repository.RoomRepository;

import java.util.List;
import java.util.Scanner;

//serviceler serviceler ile veya kendi repoları ile iletişime geçer.

public class RoomService {
    private Scanner scanner = new Scanner(System.in);

    private final HotelService hotelService;

    private final RoomRepository roomRepository;


    //param constr
    public RoomService(HotelService hotelService, RoomRepository roomRepository) {
        this.hotelService = hotelService;
        this.roomRepository = roomRepository;
    }

    //4 -b
    public void saveRoom() {
        Room room = new Room();

        System.out.println("Enter room ID : ");
        room.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Enter room number : ");
        room.setNumber(scanner.nextLine());

        System.out.println("Enter room capacity : ");
        room.setCapacity(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter hotel ID : ");
        Long hotelId = scanner.nextLong();
        scanner.nextLine();

        //girilen id hangi otele ait
        Hotel foundHotel = hotelService.findHotelById(hotelId);
        if(foundHotel!=null) {

            room.setHotel(foundHotel); //oda hangi otele aitse set edildi.
            //t_room tablosunda hotel_id sütununa bulunan otlin sadece idsini ekler


//        //bu odayı otelin oda listesine ekleyelim
//        foundHotel.getRooms().add(room); --> bu işlem mappedBy tarafından yapılıyor...

            roomRepository.save(room); //tabloya eklendi

            System.out.println("Room is saved successfully. Room id : " + room.getId());
        }else {
            System.out.println("Room could not saved!!!");
        }

    }

    //5-b : Id si verilen odayıtablodan bulup yazdırma ve geri döndürme
    public Room findRoomById(Long roomId) {
        Room foundRoom = roomRepository.findById(roomId);
        try {
            if(foundRoom != null){
                System.out.println("--------------------------------------");
                System.out.println(foundRoom);
                System.out.println("--------------------------------------");
                return foundRoom;
            }else {
                throw new RoomNotFoundException("Room not found by ID : " + roomId);
            }
        }catch (RoomNotFoundException e){
            System.out.println(e.getMessage());
        }
        return  null;
    }

    public void getAllRooms(){

        List<Room> allRooms = roomRepository.findAll();
        if(!allRooms.isEmpty()){
            System.out.println("----------- ALL ROOMS --------------");
            for(Room room : allRooms){
                System.out.println(room);
            }
        }else {
            System.out.println("Room list is empty!");
        }

    }
}
