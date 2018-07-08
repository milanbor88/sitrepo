package com.example.repository;

import com.example.model.Klijenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KlijentiRepository  extends JpaRepository<Klijenti,Long>,
        PagingAndSortingRepository<Klijenti, Long> {

    public Klijenti save(Klijenti klijent);
    public Klijenti findByNameAndDrzava(String name, String drzava);
    public List<Klijenti> findAll();
    public List<Klijenti> findKlijentiByTipKlijenta(String tip);
    void deleteById(Long id);
    public Klijenti findByName(String name);
    Klijenti findByDrzava(String drzava);
    @Transactional(readOnly =true)
    @Query(value = "SELECT adresa, (" +
            "      6371 * acos (" +
            "      cos( radians(?1) ) " +
            "      * cos( radians( latitude ) )" +
            "      * cos( radians( longitude ) - radians(?2) ) " +
            "      + sin ( radians(?1)) " +
            "      * sin( radians( latitude ) ) " +
            "    ) " +
            "  ) AS distance " +
            "FROM klijenti " +
            "HAVING distance < 30 " +
            "ORDER BY distance " +
            "LIMIT 0 , 20;", nativeQuery = true)
    List<Object> geoKlijent(Double ltd, Double lng);

    Klijenti findByPromoKod(String promoKod);

}
