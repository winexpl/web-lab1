package com.lab1.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project {
    private int id;
    private String name;
    private String descr;
    private LocalDate beginDate;
    private LocalDate endDate;
}