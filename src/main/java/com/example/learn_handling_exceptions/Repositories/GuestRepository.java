package com.example.learn_handling_exceptions.Repositories;

import com.example.learn_handling_exceptions.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
}
