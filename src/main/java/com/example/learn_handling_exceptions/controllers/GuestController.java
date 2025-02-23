package com.example.learn_handling_exceptions.controllers;

import com.example.learn_handling_exceptions.Dtos.GuestDto;
import com.example.learn_handling_exceptions.Dtos.ReservationDTO;
import com.example.learn_handling_exceptions.Repositories.GuestRepository;
import com.example.learn_handling_exceptions.Repositories.ReservationRepository;
import com.example.learn_handling_exceptions.Services.GuestService;
import com.example.learn_handling_exceptions.entities.Guest;
import com.example.learn_handling_exceptions.entities.Reservation;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/secure")
public class GuestController {

    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;
    private GuestService guestService;

    public GuestController(GuestRepository guestRepository, GuestService guestService,ReservationRepository reservationRepository) {
        this.guestRepository = guestRepository;
        this.guestService = guestService;
        this.reservationRepository = reservationRepository;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody Guest guest) {
        Guest savedGuest =  guestRepository.save(guest);

        GuestDto guestDto = guestService.FromGuestToGuestDto(savedGuest);
        return  new ResponseEntity<>(Collections.singletonList(guestDto),HttpStatus.CREATED);
    }

    @PostMapping("reserver")
    public ResponseEntity<?> reserver(@Valid @RequestBody Reservation reservation) {
        System.out.println(reservation.getDebut());
        Reservation savedReservation = reservationRepository.save(reservation);
        return  new ResponseEntity<>(Collections.singletonList(savedReservation),HttpStatus.CREATED);

    }

    @GetMapping("/getReservation")
    public ResponseEntity<?> getReservation() {
        Optional<Reservation> optionalReservation = reservationRepository.findById(1);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            GuestDto guestDto = guestService.FromGuestToGuestDto(reservation.getGuest());
            return new ResponseEntity<>(new ReservationDTO(reservation.getNumber(),guestDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }
    }





}
