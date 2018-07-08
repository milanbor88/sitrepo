package com.example.service.Implement;

import com.example.model.User;
import com.example.model.VozacSvedok;
import com.example.repository.VozacSvedokRepository;
import com.example.service.VozacSvedokService;
import io.swagger.annotations.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("vozacSvedok")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class VozacSvedokServiceImpl implements VozacSvedokService {
    @Autowired
    VozacSvedokRepository vozacSvedokRepository;

    @Override
    public VozacSvedok saveVozacSvedok(VozacSvedok vozacSvedok) {
        return vozacSvedokRepository.save(vozacSvedok);
    }

    @Override
    public VozacSvedok findVozacSvedokById(Long id) {
        return vozacSvedokRepository.findById(id);
    }

    @Override
    public void deleteVozacSvedok(Long id) {
        vozacSvedokRepository.deleteById(id);
    }

    @Override
    public VozacSvedok countVozacSvedokById(Long id) {
        return vozacSvedokRepository.countAllById(id);
    }

    @Override
    public List<VozacSvedok> findAllByVozacNezgodaIzvestajId(Long id) {
        return vozacSvedokRepository.findAllByVozacNezgodaIzvestajId(id);
    }
}
