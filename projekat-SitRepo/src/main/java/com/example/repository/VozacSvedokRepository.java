package com.example.repository;

import com.example.model.VozacSvedok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

public interface VozacSvedokRepository extends JpaRepository<VozacSvedok, Long>, PagingAndSortingRepository<VozacSvedok, Long> {

    VozacSvedok save(VozacSvedok vozacSvedok);
    VozacSvedok findById(Long id);
    void deleteById(Long id);
    VozacSvedok countAllById(Long id);
    List<VozacSvedok> findAllByVozacNezgodaIzvestajId(Long id);
}
