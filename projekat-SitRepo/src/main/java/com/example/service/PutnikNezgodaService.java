package com.example.service;

import com.example.model.PutnikNezgoda;

import java.util.List;

public interface PutnikNezgodaService {

    public PutnikNezgoda savePutnikNezgoda (PutnikNezgoda putnikNezgoda);

    public PutnikNezgoda findPutnikNezgodaById(Long id);

    List<PutnikNezgoda> findAll();
}
