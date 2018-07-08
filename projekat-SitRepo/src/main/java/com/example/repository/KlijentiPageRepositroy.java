package com.example.repository;

import com.example.model.Klijenti;
import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlijentiPageRepositroy extends PagingAndSortingRepository<Klijenti, Long> {

}
