package com.example.learn_handling_exceptions.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.learn_handling_exceptions.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
