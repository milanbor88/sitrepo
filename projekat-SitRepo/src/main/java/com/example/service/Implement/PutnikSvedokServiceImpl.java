package com.example.service.Implement;

import com.example.model.PutnikSvedok;
import com.example.model.User;
import com.example.repository.PutnikSvedokRepository;
import com.example.repository.UserRepository;
import com.example.service.PutnikSvedokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("putnikSvedok")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class PutnikSvedokServiceImpl implements PutnikSvedokService{


    @Autowired
    PutnikSvedokRepository putnikSvedokRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void savePutnikSvedok(PutnikSvedok putnikSvedok) {
      /*  userRepository.save(user);
        putnikSvedok.setUser(user);*/
        putnikSvedokRepository.save(putnikSvedok);
    }

    @Override
    public void deletePutnikSvedok(Long id) {
        putnikSvedokRepository.deleteById(id);
    }

    @Override
    public PutnikSvedok findPutnikSvedokById(Long id) {
        return putnikSvedokRepository.findById(id);
    }

    @Override
    public List<PutnikSvedok> findPutnikSvedokAll() {
        return putnikSvedokRepository.findAll();
    }

    @Override
    public List<PutnikSvedok> findPutnikSvedokByPutnikIzvestajId(Long id) {
        return putnikSvedokRepository.findAllByPutnikIzvestajId(id);
    }
}
