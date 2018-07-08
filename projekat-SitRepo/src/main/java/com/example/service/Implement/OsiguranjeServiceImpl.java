package com.example.service.Implement;

import com.example.model.Osiguranje;
import com.example.model.User;
import com.example.repository.OsiguranjeRepository;
import com.example.service.OsiguranjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("osiguranjeService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class OsiguranjeServiceImpl implements OsiguranjeService {

    @Autowired
    OsiguranjeRepository osiguranjeRepository;

    @Override
    public void saveOsiguranje(Osiguranje osiguranje) {
        osiguranjeRepository.save(osiguranje);
    }

    @Override
    public void updateOsiguranje(Osiguranje osiguranje) {
        osiguranjeRepository.save(osiguranje);
    }

    @Override
    public Osiguranje findOsiguranjeById(Long id) {
        return osiguranjeRepository.findById(id);
    }

    @Override
    public void deleteOsiguranjeById(Long id) {
        osiguranjeRepository.deleteById(id);
    }

    @Override
    public Osiguranje findOsiguranjeByUser(User user) {
        return osiguranjeRepository.findOsiguranjeByUser(user);
    }


}
