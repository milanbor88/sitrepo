package com.example.service.Implement;

import com.example.model.Prikolica;
import com.example.model.PrikolicaB;
import com.example.model.VozacNezgodaManja;
import com.example.repository.PrikolicaBRepository;
import com.example.repository.PrikolicaRepository;
import com.example.service.PrikolicaBService;
import com.example.service.PrikolicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Service("prikolicaBService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class PrikolicaBServiceImpl implements PrikolicaBService {

    @Autowired
    PrikolicaBRepository prikolicaBRepository;

    @Override
    public void savePrikolicaB(PrikolicaB prikolicaB) {
        prikolicaBRepository.save(prikolicaB);
    }

    @Override
    public void updatePrikolicaB(PrikolicaB prikolicaB) {
        prikolicaBRepository.save(prikolicaB);
    }

    @Override
    public PrikolicaB findPrikolicaBById(Long id) {
        return prikolicaBRepository.findById(id);
    }

    @Override
    public void deletePrikolicaBById(Long id) {
        prikolicaBRepository.deleteById(id);
    }

    @Override
    public PrikolicaB findUOBByVozacNezgodaManjaId(Long id) {
        return prikolicaBRepository.findAllByVozacNezgodaManjaId(id);
    }

    @Override
    public PrikolicaB findByVozacNezgodaManja(VozacNezgodaManja manja) {
        return prikolicaBRepository.findByVozacNezgodaManja(manja);
    }
}
