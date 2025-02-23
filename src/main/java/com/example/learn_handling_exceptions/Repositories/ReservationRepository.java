package com.example.learn_handling_exceptions.Repositories;

import com.example.learn_handling_exceptions.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r.room.id from Reservation r where (r.debut  between :startDate and :endDate) and (r.fin  between :startDate and :endDate)")
    List<Integer> getReservationBetweenAPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select r.id from Room r where r.id not in :ids")
    List<Integer> getAvailableRooms(@Param("ids") List<Integer> ids);
}
