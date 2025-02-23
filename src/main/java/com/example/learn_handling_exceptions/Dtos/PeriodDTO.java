package com.example.learn_handling_exceptions.Dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PeriodDTO {

    private LocalDate debut;
    private LocalDate fin;

}
