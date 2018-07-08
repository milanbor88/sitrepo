package com.example.service.Implement;

import com.example.model.Role;
import com.example.model.User;
import com.example.model.Vozilo;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.repository.VoziloRepository;
import com.example.service.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Service("voziloService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class VoziloServiceImp implements VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

    @Override
    public void saveVozilo(Vozilo vozilo) {
        voziloRepository.save(vozilo);
    }

    @Override
    public void updateVozilo(Vozilo vozilo) {
        voziloRepository.save(vozilo);
    }

    @Override
    public Vozilo findVoziloById(Long id) {
        return voziloRepository.findById(id);
    }

    @Override
    public Vozilo findVoziloByUser_Id(Long id) {
        return voziloRepository.findVoziloByUser_Id(id);
    }

    @Override
    public void deleteVoziloById(Long id) {
        voziloRepository.deleteById(id);
    }

    @Override
    public Vozilo findVoziloByUser(User user) {
        return voziloRepository.findVoziloByUser(user);
    }


}
