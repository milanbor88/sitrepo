package com.example.service;

import com.example.model.Klijenti;
import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface KlijentiPageService {

    Page<Klijenti> findAllPageable(Pageable pageable);
}
