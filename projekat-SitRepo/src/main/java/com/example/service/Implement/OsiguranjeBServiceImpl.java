package com.example.service.Implement;

import com.example.model.Osiguranje;
import com.example.model.OsiguranjeB;
import com.example.model.VozacNezgodaManja;
import com.example.repository.OsiguranjeBRepository;
import com.example.repository.OsiguranjeRepository;
import com.example.service.OsiguranjeBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("osiguranjeBService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class OsiguranjeBServiceImpl implements OsiguranjeBService {

    @Autowired
    OsiguranjeBRepository osiguranjeBRepository;


    @Override
    public void saveOsiguranjeB(OsiguranjeB osiguranjeB) {
        osiguranjeBRepository.save(osiguranjeB);
    }

    @Override
    public void updateOsiguranjeB(OsiguranjeB osiguranjeB) {
        osiguranjeBRepository.save(osiguranjeB);
    }

    @Override
    public OsiguranjeB findOsiguranjeBById(Long id) {
        return osiguranjeBRepository.findById(id);
    }

    @Override
    public void deleteOsiguranjeBById(Long id) {
        osiguranjeBRepository.deleteById(id);
    }

    @Override
    public List<OsiguranjeB> findUOBByVozacNezgodaManjaId(Long id) {
        return osiguranjeBRepository.findAllByVozacNezgodaManjaId(id);
    }

    @Override
    public OsiguranjeB findByVozacNezgodaManja(VozacNezgodaManja manja) {
        return osiguranjeBRepository.findByVozacNezgodaManja(manja);
    }
}
