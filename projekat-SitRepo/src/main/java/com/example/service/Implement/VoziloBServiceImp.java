package com.example.service.Implement;

import com.example.model.VozacNezgodaManja;
import com.example.model.Vozilo;
import com.example.model.VoziloB;
import com.example.repository.VoziloBRepository;
import com.example.repository.VoziloRepository;
import com.example.service.VoziloBService;
import com.example.service.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("voziloBService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class VoziloBServiceImp implements VoziloBService {

    @Autowired
    private VoziloBRepository voziloBRepository;


    @Override
    public void saveVoziloB(VoziloB voziloB) {
        voziloBRepository.save(voziloB);
    }

    @Override
    public void updateVoziloB(VoziloB voziloB) {
        voziloBRepository.save(voziloB);
    }

    @Override
    public VoziloB findVoziloBById(Long id) {
        return voziloBRepository.findById(id);
    }

    @Override
    public void deleteVoziloBById(Long id) {
        voziloBRepository.deleteById(id);
    }

    @Override
    public VoziloB findUOBByVozacNezgodaManjaId(Long id) {
        return voziloBRepository.findAllByVozacNezgodaManjaId(id);
    }

    @Override
    public VoziloB findByVozacNezgodaManja(VozacNezgodaManja manja) {
        return voziloBRepository.findByVozacNezgodaManja(manja);
    }

}
