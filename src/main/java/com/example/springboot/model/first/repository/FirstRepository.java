package com.example.springboot.model.first.repository;

import com.example.springboot.model.first.entity.First;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstRepository extends JpaRepository<First, Long> {

}