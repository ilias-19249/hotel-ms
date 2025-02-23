package com.example.learn_handling_exceptions.Repositories;

import com.example.learn_handling_exceptions.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoomRepository extends JpaRepository<Room, Integer> , JpaSpecificationExecutor<Room> {
    Room findByNumber(int number);
}
