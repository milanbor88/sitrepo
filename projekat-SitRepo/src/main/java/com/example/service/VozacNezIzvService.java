package com.example.service;

import com.example.model.User;
import com.example.model.VozacNezgodaIzvestaj;
import com.example.model.VozacNezgodaManja;
import com.example.model.VozacNezgodaVeca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VozacNezIzvService {

    public List<VozacNezgodaVeca> findAllVeca();
    public List<VozacNezgodaManja> findAllManja();
    public VozacNezgodaVeca saveVeca(VozacNezgodaVeca vozacNezgodaVeca);
    public VozacNezgodaManja saveManja(VozacNezgodaManja vozacNezgodaManja);
    public VozacNezgodaIzvestaj save(VozacNezgodaIzvestaj izvestaj);
    public VozacNezgodaIzvestaj findbyId(Long id);
    public VozacNezgodaVeca findVecaById(Long id);
    public VozacNezgodaManja findManjaById(Long id);
    public VozacNezgodaIzvestaj findTopIzvestakByUser(User user);
    public VozacNezgodaVeca topveca(User user);
    public VozacNezgodaManja topmanja(User user);
    public List<VozacNezgodaManja> findVozacNezgodaManjaByUser(User user);
    public List<VozacNezgodaVeca> findVozacNezgodaVecaByUser(User user);
    public  List<VozacNezgodaManja> findByPoslatManja(boolean poslat);
    public  List<VozacNezgodaVeca> findByPoslatVeca(boolean poslat);
    public Page<VozacNezgodaVeca> findByPoslatPagebleVeca(boolean poslat, Pageable pageble);
    public Page<VozacNezgodaManja> findByPoslatPagebleManja(boolean poslat, Pageable pageble);


}
