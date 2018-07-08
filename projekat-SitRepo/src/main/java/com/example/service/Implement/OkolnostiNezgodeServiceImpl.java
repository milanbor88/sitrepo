package com.example.service.Implement;

import com.example.model.OkolnostiNezgode;
import com.example.model.User;
import com.example.model.VozacNezgodaManja;
import com.example.repository.OkolnostiNezgodeRepository;
import com.example.service.OkolnostiNezgodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("okolnostiNezgode")
public class OkolnostiNezgodeServiceImpl implements OkolnostiNezgodeService{

    @Autowired
    OkolnostiNezgodeRepository okolnostiNezgodeRepository;

    @Override
    public void saveOkolnostiNezgode(OkolnostiNezgode okolnostiNezgode) {
        okolnostiNezgodeRepository.save(okolnostiNezgode);
    }

    @Override
    public OkolnostiNezgode findOkolnostiNezgodeById(Long id) {
        return okolnostiNezgodeRepository.findById(id);
    }

    @Override
    public void updateOkolnostiNezgode(OkolnostiNezgode okolnostiNezgode) {
        okolnostiNezgodeRepository.save(okolnostiNezgode);
    }

    @Override
    public OkolnostiNezgode findNezgodaByManja(VozacNezgodaManja manja) {
        return okolnostiNezgodeRepository.findByVozacNezgodaManja(manja);
    }
}
