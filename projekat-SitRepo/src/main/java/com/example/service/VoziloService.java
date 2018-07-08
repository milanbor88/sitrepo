package com.example.service;

import com.example.model.User;
import com.example.model.Vozilo;

import java.util.List;

public interface VoziloService {

    public void saveVozilo(Vozilo vozilo);
    //public void updateVozilo(Vozilo vozilo);
    public void updateVozilo(Vozilo vozilo);
    public Vozilo findVoziloById(Long id);
    Vozilo findVoziloByUser_Id(Long id);
    void deleteVoziloById(Long id);

    Vozilo findVoziloByUser(User user);
}
