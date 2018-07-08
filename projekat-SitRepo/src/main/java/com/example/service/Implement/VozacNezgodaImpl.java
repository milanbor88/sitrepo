package com.example.service.Implement;

import com.example.model.VozacNezgoda;
import com.example.model.VozacSvedok;
import com.example.repository.VozacNezgodaRepository;
import com.example.service.VozacNezgodaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("vozacNezgoda")
public class VozacNezgodaImpl implements VozacNezgodaService {

    @Autowired
    VozacNezgodaRepository vozacNezgodaRepository;

    @Override
    public VozacNezgoda saveVozacNezgoda(VozacNezgoda vozacNezgoda) {

        return vozacNezgodaRepository.save(vozacNezgoda);
    }

    @Override
    public List<VozacNezgoda> findVozacNezgodaAll() {
        return vozacNezgodaRepository.findAll();
    }
/*
    @Override
    public VozacNezgoda findVozacNezgodaByVozacNezgodaIzvId(Long id) {
        return vozacNezgodaRepository.findByVozacNezgodaIzvestajId(id);
    }*/
}
