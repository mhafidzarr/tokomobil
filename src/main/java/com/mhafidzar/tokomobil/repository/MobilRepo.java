/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhafidzar.tokomobil.repository;

import com.mhafidzar.tokomobil.model.Mobil;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author natar
 */
@Repository
public interface MobilRepo extends CrudRepository<Mobil, Integer> {
    
    @Query("from Mobil where merk=:merk")
    public Iterable<Mobil> findByBrand(@Param("merk") String merk);
    
    @Query("from Mobil where merk=:merk and tipe=:tipe")
    public Iterable<Mobil> findByBrandAndType(@Param("merk") String merk, @Param("tipe") String tipe);
    
    @Transactional
    @Modifying
    @Query("update Mobil set noKerangka=:noKerangka , noPolisi=:noPolisi, tahun=:tahun where merk=:merk and tipe=:tipe")
    public void editByBrandAndType(@Param("merk") String merk, @Param("tipe") String tipe, @Param("noKerangka") String noKerangka,
            @Param("noPolisi") String noPolisi, @Param("tahun") int tahun);
    
    @Transactional
    @Modifying
    @Query("delete from Mobil where merk=:merk and tipe=:tipe")
    public void deleteByBrandAndType(@Param("merk") String merk, @Param("tipe") String tipe);
    
}
