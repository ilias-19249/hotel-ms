package com.example.learn_handling_exceptions.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    @NotNull(message = "Name cannot be null")
    private String name;

    @Email(message = "email should be valid")
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",
        message = "Le format de la date doit être YYYY-MM-DD")
    private String dateNaissance;

    @Min(value = 0, message = "Price must not be negative")
    private float solde;

    @OneToOne(mappedBy = "guest", cascade = CascadeType.ALL)
    private Reservation reservation;

    @ManyToMany
    @JoinTable(
        name = "guest_service",
        joinColumns = @JoinColumn(name = "guest_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services = new ArrayList<>();

}
