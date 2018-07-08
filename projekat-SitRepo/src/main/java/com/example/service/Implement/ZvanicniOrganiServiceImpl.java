package com.example.service.Implement;

import com.example.model.User;
import com.example.model.VozacNezgodaManja;
import com.example.model.ZvanicniOrgani;
import com.example.repository.ZvanicniOrganiRepository;
import com.example.service.ZvanicniOrganiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("ZvanicniOrgani")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class ZvanicniOrganiServiceImpl implements ZvanicniOrganiService{

    @Autowired
    ZvanicniOrganiRepository zvanicniOrganiRepository;


    @Override
    public void saveZvanicniOrgani(ZvanicniOrgani zvanicniOrgani) {
        zvanicniOrganiRepository.save(zvanicniOrgani);
    }


    @Override
    public ZvanicniOrgani findZvanicniOrganiById(Long id) {
        return zvanicniOrganiRepository.findById(id);
    }

    @Override
    public void updateZvanicniOrgani(ZvanicniOrgani zvanicniOrgani) {
        zvanicniOrganiRepository.save(zvanicniOrgani);
    }

    @Override
    public ZvanicniOrgani findByVozacNezgodaManja(VozacNezgodaManja manja) {
        return zvanicniOrganiRepository.findByVozacNezgodaManja(manja);
    }
}
