/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhafidzar.tokomobil;

import com.mhafidzar.tokomobil.model.Mobil;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author natar
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokomobilApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnitTest {
    final String merk = "toyota";
    final String tipe = "supra";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCarGetAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/mobil",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testCarGetByBrand() {        
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/mobil/" + this.merk,
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }
    
    @Test
    public void testCarGetByBrandAndType() {        
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/mobil/" + this.merk + "/" + this.tipe,
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testCarAdd() {
        Mobil mobil = new Mobil();
        mobil.setNoKerangka("7788990012");
        mobil.setNoPolisi("N 2886 CA");
        mobil.setTahun(2019);

        ResponseEntity<Mobil> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/mobil/" + this.merk + "/" + this.tipe, mobil, Mobil.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testCarEdit() {
        int id = 3;
        
        Mobil mobil = restTemplate.getForObject(getRootUrl() + "/api/mobil/" + id, Mobil.class);
        mobil.setTahun(2017);

        restTemplate.put(getRootUrl() + "/api/mobil/" + merk + "/" + tipe, mobil);

        Mobil updatedMobil = restTemplate.getForObject(getRootUrl() + "/api/mobil/" + id, Mobil.class);
        assertNotNull(updatedMobil);
    }

    @Test
    public void testCarDelete() {        
        int id = 3;
        Mobil mobil = restTemplate.getForObject(getRootUrl() + "/api/mobil/" + id, Mobil.class);
        assertNotNull(mobil);

        restTemplate.delete(getRootUrl() + "/api/mobil/" + merk + "/" + tipe);
    }
}
