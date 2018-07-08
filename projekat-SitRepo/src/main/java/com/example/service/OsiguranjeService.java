package com.example.service;

import com.example.model.Osiguranje;
import com.example.model.User;
import com.example.model.Vozilo;

public interface OsiguranjeService {

    public void saveOsiguranje(Osiguranje osiguranje);
    public void updateOsiguranje(Osiguranje osiguranje);
    public Osiguranje findOsiguranjeById(Long id);
    void deleteOsiguranjeById (Long id);

    Osiguranje findOsiguranjeByUser(User user);
}
