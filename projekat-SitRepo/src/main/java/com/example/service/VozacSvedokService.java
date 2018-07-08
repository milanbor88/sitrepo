package com.example.service;

import com.example.model.User;
import com.example.model.VozacSvedok;

import java.util.List;

public interface VozacSvedokService {

    VozacSvedok saveVozacSvedok(VozacSvedok vozacSvedok);
    public VozacSvedok findVozacSvedokById(Long id);
    public void deleteVozacSvedok(Long id);
    public VozacSvedok countVozacSvedokById(Long id);

    List<VozacSvedok> findAllByVozacNezgodaIzvestajId(Long id);

}
