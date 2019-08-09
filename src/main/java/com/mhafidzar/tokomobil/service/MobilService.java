/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhafidzar.tokomobil.service;

import com.mhafidzar.tokomobil.model.Mobil;
import com.mhafidzar.tokomobil.repository.MobilRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author natar
 */
@Service("mobilService")
public class MobilService {
    
    private MobilRepo mobilRepo;
    
    @Autowired
    public MobilService(MobilRepo mobilRepo) {
        this.mobilRepo = mobilRepo;
    }
    
    public Iterable<Mobil> findAll() {
        return mobilRepo.findAll();
    }
    
    public Mobil carAddSave(Mobil mobil) {
        return mobilRepo.save(mobil);
    }
    
    public Optional<Mobil> getCarById(int id) {
        return mobilRepo.findById(id);
    }
    
    public Mobil carEditSave(Mobil mobil) {
        return mobilRepo.save(mobil);
    }
    
    public void carDelete(Mobil mobil) {
        mobilRepo.delete(mobil);
    }
    
}
