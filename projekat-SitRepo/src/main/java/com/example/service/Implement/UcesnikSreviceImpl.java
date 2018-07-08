package com.example.service.Implement;

import com.example.model.Ucesnik;
import com.example.model.VozacNezgodaManja;
import com.example.repository.UcesnikRepository;
import com.example.service.UcesnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("ucesnik")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UcesnikSreviceImpl implements UcesnikService {

    @Autowired
    UcesnikRepository ucesnikRepository;


    @Override
    public void saveUcesnik(Ucesnik ucesnik) {
        ucesnikRepository.save(ucesnik);
    }

    @Override
    public void deleteUcesnikById(Long id) {
        ucesnikRepository.deleteById(id);
    }

    @Override
    public Ucesnik findUcesnikById(Long id) {
        return ucesnikRepository.findById(id);
    }

    @Override
    public Ucesnik findUcesnikByVozacNezgodaManjaId(Long id) {
        return ucesnikRepository.findUcesnikByVozacNezgodaManjaId(id);
    }

    @Override
    public Ucesnik findByVozacNezgodaManja(VozacNezgodaManja manja) {
        return ucesnikRepository.findByVozacNezgodaManja(manja);
    }
}
