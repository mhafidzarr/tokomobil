/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhafidzar.tokomobil.seeder;

import com.mhafidzar.tokomobil.model.Mobil;
import com.mhafidzar.tokomobil.repository.MobilRepo;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author natar
 */
@Component
public class DatabaseSeeder {
    
    private MobilRepo mobilRepo;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(MobilRepo mobilRepo, JdbcTemplate jdbcTemplate) {
        this.mobilRepo = mobilRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedDataMobil();
    }

    private void seedDataMobil() {
        String sql = "SELECT mobil_id FROM mobil";
        List<Mobil> result = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (result == null || result.size() <= 0) {
            Mobil mobil1 = new Mobil();
            mobil1.setNoKerangka("1234567890");
            mobil1.setNoPolisi("B 1234 CDE");
            mobil1.setMerk("Toyota");
            mobil1.setTipe("Avanza");
            mobil1.setTahun(2013);
            
            Mobil mobil2 = new Mobil();
            mobil2.setNoKerangka("0987654321");
            mobil2.setNoPolisi("B 5678 FGH");
            mobil2.setMerk("Honda");
            mobil2.setTipe("Mobilio");
            mobil2.setTahun(2014);
            
            mobilRepo.saveAll(Arrays.asList(mobil1, mobil2));
        }
    }

}
