package com.example.learn_handling_exceptions.entities;

import com.example.learn_handling_exceptions.enumeration.Services;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)  // Store values as Strings
    @Column(nullable = false)  // Ensures the field is required
    private Services name;

    @ManyToMany(mappedBy = "services")
    private List<Guest> guests = new ArrayList<>();

}
