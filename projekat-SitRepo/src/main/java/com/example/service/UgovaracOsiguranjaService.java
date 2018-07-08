package com.example.service;

import com.example.model.*;

public interface UgovaracOsiguranjaService {

    public void saveUgovaracOsigruranja(UgovaracOsiguranja ugovaracOsiguranja);
    public void updateUgovarac(UgovaracOsiguranja ugovaracOsiguranja);
    public UgovaracOsiguranja findUgovaracOsiguranjaById(Long id);
    UgovaracOsiguranja findUgovaracOsiguranjaByUser_Id(Long id);
    void deleteUgovaracOsiguranjaById(Long id);

    UgovaracOsiguranja findUgovaracOsiguranjaByUser(User user);
}
