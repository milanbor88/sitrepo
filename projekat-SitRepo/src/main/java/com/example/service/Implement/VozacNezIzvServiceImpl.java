package com.example.service.Implement;


import com.example.dto.VozacNezgodaIzvestajDTO;
import com.example.model.User;
import com.example.model.VozacNezgodaIzvestaj;
import com.example.model.VozacNezgodaManja;
import com.example.model.VozacNezgodaVeca;
import com.example.repository.VozacNezgodaIzvestajRepository;
import com.example.repository.VozacNezgodaManjaRepository;
import com.example.repository.VozacNezgodaVecaRepository;
import com.example.service.VozacNezIzvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("vozacNezIzvService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class VozacNezIzvServiceImpl implements VozacNezIzvService {

    @Autowired
    private VozacNezgodaIzvestajRepository nezgodaIzvestajRepository;

    @Autowired
    private VozacNezgodaVecaRepository vecaRepository;

    @Autowired
    private VozacNezgodaManjaRepository manjaRepository;

    @Override
    public List<VozacNezgodaVeca> findAllVeca() {
        return vecaRepository.findAll();
    }

    @Override
    public List<VozacNezgodaManja> findAllManja() {
        return manjaRepository.findAll();
    }

    @Override
    public VozacNezgodaVeca saveVeca(VozacNezgodaVeca vozacNezgodaVeca) {
        return vecaRepository.save(vozacNezgodaVeca);
    }

    @Override
    public VozacNezgodaManja saveManja(VozacNezgodaManja vozacNezgodaManja) {
        return manjaRepository.save(vozacNezgodaManja);
    }

    @Override
    public VozacNezgodaIzvestaj save(VozacNezgodaIzvestaj vozacNezgoda) {
        return nezgodaIzvestajRepository.save(vozacNezgoda);
    }


    @Override
    public VozacNezgodaIzvestaj findbyId(Long id) {
        return nezgodaIzvestajRepository.findOne(id);
    }

    @Override
    public VozacNezgodaVeca findVecaById(Long id) {
        return vecaRepository.findOne(id);
    }

    @Override
    public VozacNezgodaManja findManjaById(Long id) {
        return manjaRepository.findOne(id);
    }

    @Override
    public VozacNezgodaVeca topveca(User user) {
        return vecaRepository.findTopByUserOrderByIdDesc(user);
    }

    @Override
    public VozacNezgodaManja topmanja(User user) {
        return manjaRepository.findTopByUserOrderByIdDesc(user);
    }

    @Override
    public List<VozacNezgodaManja> findVozacNezgodaManjaByUser(User user) {
        return manjaRepository.findByUser(user);
    }

    @Override
    public List<VozacNezgodaVeca> findVozacNezgodaVecaByUser(User user) {
        return vecaRepository.findByUser(user);
    }

    @Override
    public List<VozacNezgodaManja> findByPoslatManja(boolean poslat) {
        return manjaRepository.findByPoslat(poslat);
    }

    @Override
    public List<VozacNezgodaVeca> findByPoslatVeca(boolean poslat) {
        return vecaRepository.findByPoslat(poslat);
    }

   @Override
    public Page<VozacNezgodaVeca> findByPoslatPagebleVeca(boolean poslat, Pageable page) {
        return vecaRepository.findByPoslat(poslat, page);
    }

    @Override
    public Page<VozacNezgodaManja> findByPoslatPagebleManja(boolean poslat, Pageable page) {
        return manjaRepository.findByPoslat(poslat, page);
    }


    @Override
    public VozacNezgodaIzvestaj findTopIzvestakByUser(User user) {
        return nezgodaIzvestajRepository.test(user);
    }
}