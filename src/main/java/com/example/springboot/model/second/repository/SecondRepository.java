package com.example.springboot.model.second.repository;

import com.example.springboot.model.second.entity.Second;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondRepository extends JpaRepository<Second, Long> {

}
