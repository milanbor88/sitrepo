package com.example.service.Implement;

import com.example.model.UgovaracOsiguranja;
import com.example.model.User;
import com.example.repository.UgovaracOsiguranjaRepository;
import com.example.service.UgovaracOsiguranjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("ugovaracOsiguranjaService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UgovaracOsiguranjaServiceImpl implements UgovaracOsiguranjaService {

    @Autowired
    UgovaracOsiguranjaRepository ugovaracOsiguranjaRepository;

    @Override
    public void saveUgovaracOsigruranja(UgovaracOsiguranja ugovaracOsiguranja) {
        ugovaracOsiguranjaRepository.save(ugovaracOsiguranja);
    }

    @Override
    public void updateUgovarac(UgovaracOsiguranja ugovaracOsiguranja) {
        ugovaracOsiguranjaRepository.save(ugovaracOsiguranja);
    }

    @Override
    public UgovaracOsiguranja findUgovaracOsiguranjaById(Long id) {
        return ugovaracOsiguranjaRepository.findById(id);
    }

    @Override
    public UgovaracOsiguranja findUgovaracOsiguranjaByUser_Id(Long id) {
        return ugovaracOsiguranjaRepository.findUgovaracOsiguranjaByUser_Id(id);
    }

    @Override
    public void deleteUgovaracOsiguranjaById(Long id) {
        ugovaracOsiguranjaRepository.deleteById(id);
    }

    @Override
    public UgovaracOsiguranja findUgovaracOsiguranjaByUser(User user) {
        return ugovaracOsiguranjaRepository.findUgovaracOsiguranjaByUser(user);
    }


}
