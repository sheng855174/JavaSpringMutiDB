package com.example.springboot.model.first.entity;

import javax.persistence.*;

@Entity
@Table
public class First {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String columnOne;

    public First() {}

    public First(String columnOne) {
        this.columnOne = columnOne;
    }

    public void setId(Long id){
        this.Id = id;
    }
    public void setColumnOne(String columnOne){
        this.columnOne = columnOne;
    }

}