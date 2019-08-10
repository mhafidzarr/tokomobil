/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhafidzar.tokomobil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhafidzar.tokomobil.model.Mobil;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private static final ObjectMapper om = new ObjectMapper();
    
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
    public void testCarEdit() throws JsonProcessingException {
        Mobil mobil = new Mobil();
        mobil.setNoKerangka("7788990012");
        mobil.setNoPolisi("N 2886 CA");
        mobil.setTahun(2018);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(mobil), headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/mobil/" + merk + "/" + tipe, HttpMethod.PUT, entity, String.class);
        assertNotNull(response.getStatusCode());
    }

    @Test
    public void testCarDelete() {
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/mobil/" + merk + "/" + tipe, HttpMethod.DELETE, entity, String.class);
        assertNotNull(response.getStatusCode());
    }
}
