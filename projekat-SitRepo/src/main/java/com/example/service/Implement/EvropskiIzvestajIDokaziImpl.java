package com.example.service.Implement;

import com.example.model.EvropskiIzvestajIDokazi;
import com.example.repository.EvropskiIzvestajiIDokaziRepository;
import com.example.service.EvropskiIzvestajIDokaziService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("evropskiIzvestajIDokazi")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class EvropskiIzvestajIDokaziImpl implements EvropskiIzvestajIDokaziService{

    @Autowired
    EvropskiIzvestajiIDokaziRepository evropskiIzvestajiIDokaziRepository;

    @Override
    public EvropskiIzvestajIDokazi saveEvropskiIzvestajIDokazi(EvropskiIzvestajIDokazi evropskiIzvestajIDokazi) {
       return evropskiIzvestajiIDokaziRepository.save(evropskiIzvestajIDokazi);
    }

    @Override
    public EvropskiIzvestajIDokazi findEvropskiIzvestajIDokazikById(Long id) {
        return evropskiIzvestajiIDokaziRepository.findById(id);
    }

    @Override
    public List<EvropskiIzvestajIDokazi> findEvrDokaziByStatusAndVozacNezgodaManjaId(String status, Long id) {
        return evropskiIzvestajiIDokaziRepository.findAllByStatusAndVozacNezgodaManjaId(status,id);
    }


}
