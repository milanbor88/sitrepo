package com.example.service.Implement;

import com.example.model.TipKlijenta;
import com.example.repository.TipKlijentaRepository;
import com.example.service.TipKlijentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("tipklijenta")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class TipKlijentaServiceImpl implements TipKlijentaService {

    final
    TipKlijentaRepository tipKlijentaRepository;

    @Autowired
    public TipKlijentaServiceImpl(TipKlijentaRepository tipKlijentaRepository) {
        this.tipKlijentaRepository = tipKlijentaRepository;
    }

    @Override
    public void save(TipKlijenta tipKlijenta) {
        tipKlijentaRepository.save(tipKlijenta);
    }

    @Override
    public List<TipKlijenta> findAll() {
        return tipKlijentaRepository.findAll();
    }
}
