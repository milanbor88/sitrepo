package com.example.service.Implement;

import com.example.model.OkolnostiNezgode;
import com.example.model.OkolnostiNezgodeB;
import com.example.model.VozacNezgodaManja;
import com.example.repository.OkolnostiNezgodeBRepository;
import com.example.repository.OkolnostiNezgodeRepository;
import com.example.service.OkolnostiNezgodeBService;
import com.example.service.OkolnostiNezgodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("okolnostiNezgodeB")
public class OkolnostiNezgodeBServiceImpl implements OkolnostiNezgodeBService {

    @Autowired
    OkolnostiNezgodeBRepository okolnostiNezgodeBRepository;


    @Override
    public void saveOkolnostiNezgodeB(OkolnostiNezgodeB okolnostiNezgodeB) {
        okolnostiNezgodeBRepository.save(okolnostiNezgodeB);
    }

    @Override
    public OkolnostiNezgodeB findOkolnostiNezgodeBById(Long id) {
        return okolnostiNezgodeBRepository.findByIdB(id);
    }

    @Override
    public void updateOkolnostiNezgodeB(OkolnostiNezgodeB okolnostiNezgodeB) {
        okolnostiNezgodeBRepository.save(okolnostiNezgodeB);
    }

    @Override
    public OkolnostiNezgodeB findNezgodaBByManja(VozacNezgodaManja manja) {
        return okolnostiNezgodeBRepository.findByVozacNezgodaManja(manja);
    }
}
