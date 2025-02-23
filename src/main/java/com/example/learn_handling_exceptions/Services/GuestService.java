package com.example.learn_handling_exceptions.Services;

import com.example.learn_handling_exceptions.Dtos.GuestDto;
import com.example.learn_handling_exceptions.entities.Guest;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    public GuestDto FromGuestToGuestDto(Guest guest) {
        GuestDto dto = new GuestDto();

        dto.setName(guest.getName());
        dto.setEmail(guest.getEmail());
        dto.setSolde(guest.getSolde());

        return dto;
    }
}
