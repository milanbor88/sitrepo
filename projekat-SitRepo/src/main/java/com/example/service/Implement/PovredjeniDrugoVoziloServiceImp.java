package com.example.service.Implement;

import com.example.model.PovredjeniDrugoVozilo;
import com.example.repository.PovredjeniDrugoVozilosRepository;
import com.example.service.PovredjeniDrugoVoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("povredjeniDrugoVozilo")
public class PovredjeniDrugoVoziloServiceImp implements PovredjeniDrugoVoziloService {

    @Autowired
    PovredjeniDrugoVozilosRepository povredjeniDrugoVozilosRepository;

    @Override
    public void savePovredjeniDrugoVozilo(PovredjeniDrugoVozilo povredjeniDrugoVozilo) {
        povredjeniDrugoVozilosRepository.save(povredjeniDrugoVozilo);
    }

    @Override
    public List<PovredjeniDrugoVozilo> findPdvByVozacNezgodaIzvestajId(Long id) {
        return povredjeniDrugoVozilosRepository.findAllByVozacNezgodaIzvestajId(id);
    }
}
