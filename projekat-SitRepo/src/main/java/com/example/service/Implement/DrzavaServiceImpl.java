package com.example.service.Implement;

import com.example.model.Drzave;
import com.example.repository.DrzavaRepository;
import com.example.service.DrzavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("drzava")
public class DrzavaServiceImpl implements DrzavaService {

    @Autowired
    DrzavaRepository drzavaRepository;

    @Override
    public List<Drzave> findAll() {
        return drzavaRepository.findAll();
    }
}
