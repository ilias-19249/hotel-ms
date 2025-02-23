package com.example.learn_handling_exceptions.Services;

import com.example.learn_handling_exceptions.Dtos.RoomDto;
import com.example.learn_handling_exceptions.Repositories.RoomRepository;
import com.example.learn_handling_exceptions.entities.Room;
import com.example.learn_handling_exceptions.entities.RoomSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {


    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository, RoomRepository roomRepository1) {
        this.roomRepository = roomRepository1;
    }

    public RoomDto RoomToRoomDto(Room room) throws ArithmeticException {
            RoomDto roomDto = new RoomDto();
            roomDto.setNumber(room.getNumber());
            roomDto.setPrice(room.getPrice());
            roomDto.setStatus(room.isStatus());
            return roomDto;

    }

    public List<Room> searchRooms(Float price){
        Specification<Room> spec = Specification.where(RoomSpecification.hasPriceGreaterThan(price))
         .and(RoomSpecification.getAvailableRooms())
         .and(RoomSpecification.getRoomWithSpecificNumber());

         return  roomRepository.findAll(spec);
    }

}
