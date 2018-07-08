package com.example.service.Implement;

import com.example.model.Automobili;
import com.example.repository.AutomobiliRepository;
import com.example.service.AutomobiliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AutomobiliServiceImpl implements AutomobiliService {

    @Autowired
    AutomobiliRepository automobiliRepository;


    @Override
    public Automobili findAutomobiliByMarka(String marka) {
        return automobiliRepository.findByMarka(marka);
    }
}
