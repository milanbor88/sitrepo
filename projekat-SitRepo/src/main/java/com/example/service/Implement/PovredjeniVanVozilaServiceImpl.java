package com.example.service.Implement;

import com.example.model.PovredjeniVanVozila;
import com.example.repository.PovredjeniVanVozilaRepository;
import com.example.service.PovredjeniVanVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("povredjeniVanVozila")
public class PovredjeniVanVozilaServiceImpl implements PovredjeniVanVozilaService {

    @Autowired
    PovredjeniVanVozilaRepository povredjeniVanVozilaRepository;

    @Override
    public void savePovredjeniVanVozila(PovredjeniVanVozila povredjeniVanVozila) {
        povredjeniVanVozilaRepository.save(povredjeniVanVozila);
    }

    @Override
    public List<PovredjeniVanVozila> findPvanByVozacNezgodaIzvestajId(Long id) {
        return povredjeniVanVozilaRepository.findAllByVozacNezgodaIzvestajId(id);
    }
}
