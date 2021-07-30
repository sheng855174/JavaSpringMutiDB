package com.example.springboot.model.second.entity;

import javax.persistence.*;

@Entity
@Table
public class Second {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "COLUMN_ONE")
    private String columnOne;

    public Second() {}

    public Second(String columnOne) {
        this.columnOne = columnOne;
    }
    public void setId(Long id){
        this.Id = id;
    }
    public void setColumnOne(String columnOne){
        this.columnOne = columnOne;
    }
}
