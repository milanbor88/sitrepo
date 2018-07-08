package com.example.service.Implement;

import com.example.model.PutnikIzvestaj;
import com.example.model.User;
import com.example.repository.PutnikIzvestajRepository;
import com.example.service.PutnikIzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("putnikIzvService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class PutnikIzvestajServiceImpl implements PutnikIzvestajService {

    @Autowired
    PutnikIzvestajRepository putnikIzvestajRepository;

    @Override
    public PutnikIzvestaj save(PutnikIzvestaj putnikIzvestaj) {
        return putnikIzvestajRepository.save(putnikIzvestaj);
    }

    @Override
    public List<PutnikIzvestaj> findAll() {
        return putnikIzvestajRepository.findAll();
    }

    @Override
    public PutnikIzvestaj findById(Long id) {
        return putnikIzvestajRepository.findOne(id);
    }

    @Override
    public PutnikIzvestaj topPutnikIzvestaj(User user) {
        return putnikIzvestajRepository.findTopByUserOrderByIdDesc(user);
    }

    @Override
    public List<PutnikIzvestaj> findByUser(User user) {
        return putnikIzvestajRepository.findByUser(user);
    }

    @Override
    public List<PutnikIzvestaj> findByPoslatPutnikIzv(boolean poslat) {
        return putnikIzvestajRepository.findByPoslat(poslat);
    }


    @Override
    public Page<PutnikIzvestaj> findByPoslatPagePutnikIzv(boolean poslat, Pageable page) {
        return putnikIzvestajRepository.findByPoslat(poslat, page);
    }
}
