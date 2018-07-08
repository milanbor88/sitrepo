package com.example.service.Implement;

import com.example.model.Automobili;
import com.example.model.AutomobiliTip;
import com.example.repository.AutomobiliTipRepository;
import com.example.service.AutomobiliService;
import com.example.service.AutomobiliTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomobiliTipServiceImpl implements AutomobiliTipService {

    @Autowired
    AutomobiliTipRepository automobiliTipRepository;


    @Override
    public List<AutomobiliTip> findByAutomobili_Marka(String marka) {
        return automobiliTipRepository.findByAutomobili_Marka(marka);
    }
}
