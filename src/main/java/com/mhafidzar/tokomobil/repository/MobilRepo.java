/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhafidzar.tokomobil.repository;

import com.mhafidzar.tokomobil.model.Mobil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author natar
 */
@Repository
public interface MobilRepo extends CrudRepository<Mobil, Integer> {
}
