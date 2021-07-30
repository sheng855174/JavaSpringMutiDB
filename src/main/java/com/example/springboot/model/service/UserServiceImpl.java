package com.example.springboot.model.service;

import com.example.springboot.model.first.entity.First;
import com.example.springboot.model.first.repository.FirstRepository;
import com.example.springboot.model.second.entity.Second;
import com.example.springboot.model.second.repository.SecondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private FirstRepository firstRepository;

    @Autowired
    private SecondRepository secondRepository;

    @Override
    public void save(){
        First first = new First("hello first");
        Second second = new Second("hello second");
        firstRepository.save(first);
        secondRepository.save(second);
    }

}
