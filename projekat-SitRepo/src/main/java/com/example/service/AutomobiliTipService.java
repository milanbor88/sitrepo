package com.example.service;

import com.example.model.AutomobiliTip;

import java.util.List;

public interface AutomobiliTipService {

    List<AutomobiliTip> findByAutomobili_Marka(String marka);
}
