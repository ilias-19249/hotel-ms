package com.example.learn_handling_exceptions.Dtos;

import com.example.learn_handling_exceptions.entities.Reservation;

public class ReservationDTO {
    private int id;
    private int number;

    private GuestDto guest;

    public ReservationDTO(int number, GuestDto guest) {
        this.number = number;
        this.guest = guest;
    }

    public GuestDto getGuest() {
        return guest;
    }

    public void setGuest(GuestDto guest) {
        this.guest = guest;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
