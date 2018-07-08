package com.example.service.Implement;

import com.example.model.PovredjeniVaseVozilo;
import com.example.repository.PovredjeniVaseVoziloRepository;
import com.example.service.PovredjeniVaseVoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("povredjeniVaseVozilo")
public class PovredjeniVaseVoziloImpl implements PovredjeniVaseVoziloService {
    @Autowired
    PovredjeniVaseVoziloRepository povredjeniVaseVoziloRepository;

    @Override
    public void savePovredjeniVaseVozilo(PovredjeniVaseVozilo povredjeniVaseVozilo) {
        povredjeniVaseVoziloRepository.save(povredjeniVaseVozilo);
    }

    @Override
    public List<PovredjeniVaseVozilo> findPvvByVozacNezgodaIzvestajId(Long id) {
        return povredjeniVaseVoziloRepository.findAllByVozacNezgodaIzvestajId(id);
    }
}
