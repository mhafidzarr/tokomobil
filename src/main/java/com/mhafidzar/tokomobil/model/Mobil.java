/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhafidzar.tokomobil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 *
 * @author natar
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mobil")
public class Mobil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mobil_id")
    private int mobilId;
    @Column(name = "no_kerangka")
    private String noKerangka;
    @Column(name = "no_polisi")
    private String noPolisi;
    @Column(name = "merk")
    private String merk;
    @Column(name = "tipe")
    private String tipe;
    @Column(name = "tahun")
    private int tahun;

}
