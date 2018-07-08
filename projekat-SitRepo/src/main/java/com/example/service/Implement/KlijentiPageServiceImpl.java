package com.example.service.Implement;

import com.example.model.Klijenti;
import com.example.model.User;
import com.example.repository.KlijentiPageRepositroy;
import com.example.repository.UserPageRepositroy;
import com.example.service.KlijentiPageService;
import com.example.service.UserPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("klijentipageservice")
public class KlijentiPageServiceImpl implements KlijentiPageService {

    @Autowired
    KlijentiPageRepositroy klijentiPageRepositroy;


    @Override
    public Page<Klijenti> findAllPageable(Pageable pageable) {
        return klijentiPageRepositroy.findAll(pageable);
    }
}
