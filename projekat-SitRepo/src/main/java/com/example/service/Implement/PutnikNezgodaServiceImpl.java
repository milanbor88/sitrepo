package com.example.service.Implement;

import com.example.model.PutnikNezgoda;
import com.example.repository.PutnikNezgodaRepository;
import com.example.service.PutnikNezgodaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("putnikNezgoda")
public class PutnikNezgodaServiceImpl implements PutnikNezgodaService {

    @Autowired
    PutnikNezgodaRepository putnikNezgodaRepository;

    @Override
    public PutnikNezgoda savePutnikNezgoda(PutnikNezgoda putnikNezgoda) {
        return putnikNezgodaRepository.save(putnikNezgoda);
    }

    @Override
    public PutnikNezgoda findPutnikNezgodaById(Long id) {
        return putnikNezgodaRepository.findById(id);
    }

    @Override
    public List<PutnikNezgoda> findAll() {
        return putnikNezgodaRepository.findAll();
    }
}
