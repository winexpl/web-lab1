package com.lab1.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
    private int t_key;
    private int t_p_key;
    private String t_name;
    private LocalDate t_end_date;
    private boolean t_is_completed;
}