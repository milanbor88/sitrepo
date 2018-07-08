package com.example.service.Implement;

import com.example.model.Prikolica;
import com.example.model.User;
import com.example.repository.PrikolicaRepository;
import com.example.service.PrikolicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("prikolicaService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class PrikolicaServiceImpl implements PrikolicaService {

    @Autowired
    PrikolicaRepository prikolicaRepository;

    @Override
    public void savePrikolica(Prikolica prikolica) {
        prikolicaRepository.save(prikolica);
    }

    @Override
    public void updatePrikolica(Prikolica prikolica) {
        prikolicaRepository.save(prikolica);
    }

    @Override
    public Prikolica findPrikolicaById(Long id) {
        return prikolicaRepository.findById(id);
    }

    @Override
    public Prikolica findPrikolicaByUser(User user) {
        return prikolicaRepository.findPrikolicaByUser(user);
    }


    @Override
    public void deletePrikolicaById(Long id) {
        prikolicaRepository.deleteById(id);
    }

    @Override
    public List<Prikolica> findPrikolicaByStatus(String status) {
        return prikolicaRepository.findByStatus(status);
    }


}
