/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhafidzar.tokomobil.controller;

import com.mhafidzar.tokomobil.model.Mobil;
import com.mhafidzar.tokomobil.repository.MobilRepo;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author natar
 */
@RestController
@RequestMapping("/api")
public class MobilEndpoint {
    
    @Autowired
    MobilRepo mobilRepo;
    
    @GetMapping("/mobil")
    public Iterable<Mobil> carGetAll() {
        return mobilRepo.findAll();
    }
    
    @GetMapping("/mobil/{merk}")
    public Iterable<Mobil> carGetByBrand(@PathVariable(value = "merk") String merk) {
        return mobilRepo.findByBrand(merk);
    }
    
    @GetMapping("/mobil/{id}")
    public Mobil carGetById(@PathVariable(value = "id") int id) {
        return mobilRepo.findById(id).orElse(null);
    }
    
    @GetMapping("/mobil/{merk}/{tipe}")
    public Iterable<Mobil> carGetByBrandAndType(@PathVariable(value = "merk") String merk, @PathVariable(value = "tipe") String tipe) {
        return mobilRepo.findByBrandAndType(merk, tipe);
    }
    
    @PostMapping("/mobil/{merk}/{tipe}")
    public ResponseEntity carAdd(@PathVariable(value = "merk") String merk, @PathVariable(value = "tipe") String tipe,
            @Valid Mobil mobil) {
        mobil.setMerk(merk);
        mobil.setTipe(tipe);
        return ResponseEntity.ok(mobilRepo.save(mobil));
    }
    
    @PutMapping("/mobil/{merk}/{tipe}")
    public ResponseEntity carEdit(@PathVariable(value = "merk") String merk, @PathVariable(value = "tipe") String tipe,
            @Valid Mobil mobil) {
        mobilRepo.editByBrandAndType(merk, tipe, mobil.getNoKerangka(), mobil.getNoPolisi(), mobil.getTahun());
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/mobil/{merk}/{tipe}")
    public ResponseEntity carDelete(@PathVariable(value = "merk") String merk, @PathVariable(value = "tipe") String tipe) {
        mobilRepo.deleteByBrandAndType(merk, tipe);
        return ResponseEntity.noContent().build();
    }
    
}
