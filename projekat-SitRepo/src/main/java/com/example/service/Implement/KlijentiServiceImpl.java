package com.example.service.Implement;

import com.example.dto.KlijentLtdLng;
import com.example.model.Klijenti;
import com.example.repository.KlijentiRepository;
import com.example.service.KlijentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("klijenti")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class KlijentiServiceImpl implements KlijentiService {

    @Autowired
    KlijentiRepository klijentiRepository;


    @Override
    public void save(Klijenti klijent) {
        klijentiRepository.save(klijent);
    }

    @Override
    public void delete(Klijenti klijent) {

    }

    @Override
    public void update(Klijenti klijent) {

    }

    @Override
    public void findAll() {

    }

    @Override
    public Klijenti findByNameDrz(String name, String drzava){
        return klijentiRepository.findByNameAndDrzava(name,drzava);
    }

    @Override
    public List<Klijenti> findKlijentiByTipKlijenta(String tip) {
        return klijentiRepository.findKlijentiByTipKlijenta(tip);
    }

    @Override
    public List<Klijenti> findKlientiAll() {
        return klijentiRepository.findAll();
    }

    @Override
    public void deleteKlientiById(Long id) {
        klijentiRepository.deleteById(id);
    }

    @Override
    public Klijenti findByName(String name) {
        return klijentiRepository.findByName(name);
    }

    @Override
    public Klijenti findByDrzava(String drzava) {
        return klijentiRepository.findByDrzava(drzava);
    }

    @Override
    public List<KlijentLtdLng> getDistance(Double ltd, Double lng) {
        List<Object> lista = klijentiRepository.geoKlijent(ltd, lng);
        int zz = lista.size();
        //Object[][] proba = new Object[zz][2];
        Object[][] proba = lista.toArray(new Object[zz][2]);
        List<KlijentLtdLng> returnList = new ArrayList<>();
        for (int i =0; i <zz; i++) {
            KlijentLtdLng pr = new KlijentLtdLng();
            pr.setAdresa((String) proba[i][0]);
            pr.setDistance((Double) proba[i][1]);
            returnList.add(pr);
        }

        return returnList;
    }

    @Override
    public Klijenti findByPromoKod(String promoKod) {
        return klijentiRepository.findByPromoKod(promoKod);
    }
}
