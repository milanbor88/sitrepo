package com.example.repository;

import com.example.model.AutomobiliTip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutomobiliTipRepository  extends JpaRepository<AutomobiliTip, Long>{

    List<AutomobiliTip> findByAutomobili_Marka(String marka);
}
