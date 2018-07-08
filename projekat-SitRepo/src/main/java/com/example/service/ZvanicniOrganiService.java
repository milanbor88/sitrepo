package com.example.service;

import com.example.model.User;
import com.example.model.VozacNezgodaManja;
import com.example.model.ZvanicniOrgani;

public interface ZvanicniOrganiService {

    public void saveZvanicniOrgani (ZvanicniOrgani zvanicniOrgani);
    ZvanicniOrgani findZvanicniOrganiById(Long id);
    public void updateZvanicniOrgani(ZvanicniOrgani zvanicniOrgani);
    public ZvanicniOrgani findByVozacNezgodaManja(VozacNezgodaManja manja);

}
