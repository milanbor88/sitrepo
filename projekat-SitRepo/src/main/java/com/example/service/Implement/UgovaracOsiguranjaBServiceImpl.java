package com.example.service.Implement;

import com.example.model.UgovaracOsiguranjaB;
import com.example.model.VozacNezgodaManja;
import com.example.repository.UgovaracOsiguranjaBRepository;
import com.example.service.UgovaracOsiguranjaBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("ugovaracOsiguranjaBService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UgovaracOsiguranjaBServiceImpl implements UgovaracOsiguranjaBService {

    @Autowired
    UgovaracOsiguranjaBRepository ugovaracOsiguranjaBRepository;


    @Override
    public void saveUgovaracOsigruranjaB(UgovaracOsiguranjaB ugovaracOsiguranjaB) {
        ugovaracOsiguranjaBRepository.save(ugovaracOsiguranjaB);
    }

    @Override
    public void updateUgovaracB(UgovaracOsiguranjaB ugovaracOsiguranjaB) {
            ugovaracOsiguranjaBRepository.save(ugovaracOsiguranjaB);
    }

    @Override
    public UgovaracOsiguranjaB findUgovaracOsiguranjaBById(Long id) {
        return ugovaracOsiguranjaBRepository.findById(id);
    }

    @Override
    public void deleteUgovaracOsiguranjaBById(Long id) {
        ugovaracOsiguranjaBRepository.deleteById(id);
    }

    @Override
    public UgovaracOsiguranjaB findUOBByVozacNezgodaManjaId(Long id) {
        return ugovaracOsiguranjaBRepository.findAllByVozacNezgodaManjaId(id);
    }

    @Override
    public UgovaracOsiguranjaB findByVozacNezgodaManja(VozacNezgodaManja manja) {
        return ugovaracOsiguranjaBRepository.findByVozacNezgodaManja(manja);
    }

}
