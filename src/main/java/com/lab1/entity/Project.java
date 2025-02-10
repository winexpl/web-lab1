package com.lab1.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project {
    private int id;
    private String name;
    private String descr;
    private Date beginDate;
    private Date endDate;
}